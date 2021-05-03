package com.video.controller;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.sun.mail.smtp.DigestMD5;
import com.video.domain.User;
import com.video.dto.ResponseResult;
import com.video.dto.UserToken;
import com.video.exception.UserException;
import com.video.service.UserService;
import com.video.util.Constants;
import com.video.util.VideoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.security.DigestException;
import java.util.HashMap;

/**
 * 用户控制类
 */
@Controller
public class UserController {

    @Autowired
    UserService userService;


    /**
     * 检查邮箱用户是否存在
     * @param email
     * @return
     */
    @RequestMapping("/checkEmail")
    @ResponseBody
    public ResponseResult checkEmail(String email){
        ResponseResult result = new ResponseResult();
         Boolean res = userService.checkEmail(email);
         if (res){
             //用户可以被注册
             result.setCode(1);
             result.setMessage("ok");
         }else {
             //用户不可以被注册
             result.setCode(0);
             result.setMessage("fail");
         }
         return result;
    }


    /**
     * 注册功能
     * @param email
     * @param password
     * @param vcode
     * @param session
     * @return
     * @throws UserException
     */
    @RequestMapping(value = "/regist")
    public String regist(String email, String password, String vcode, HttpSession session) throws UserException {
        //注册失败，返回错误页面
        if(!ReUtil.isMatch("^([a-zA-Z]|[0-9])(\\w|\\-)+@[a-zA-Z0-9]+\\.([a-zA-Z]{2,4})$",email)){
           throw new UserException("账号格式不正确:"+email);
        }
        if (StrUtil.isEmpty(password) || password.length() < 6){
           throw new UserException(("密码为空"));
        }
        String code = (String) session.getAttribute(Constants.SESSION_VCODE);
        if (StrUtil.isEmpty(code)){
            throw new UserException("验证码为空");
        }
        if (!vcode.equalsIgnoreCase(code)){
            session.setAttribute("name","hdx");
            throw new UserException("验证码不正确");
        }

        //注册成功，返回主页面
        int result = userService.regist(email,password);
        if (result == 1){
            //注册成功
            return "redirect:/";
        }else {
            //注册失败
            throw new UserException("数据库插入数据异常");
        }
        
        
        
    }

    /**
     * 用户登录认证
     * @param email
     * @param password
     * @return
     */
    @RequestMapping("/checkLogin")
    @ResponseBody
    public ResponseResult checkLogin(String email,String password){
        System.out.println("email:"+email+",password:"+password);
        ResponseResult rr = new ResponseResult();
        User user = userService.login(email, password);
        if (user != null){
            //用户存在
            rr.setCode(1);
        }else {
            //用户不存在或账号密码错误
            rr.setCode(0);
            rr.setMessage("账户或密码不正确");
        }
        return rr;
    }


    /**
     * 登录功能
     * @param email
     * @param password
     * @param autoLogin
     * @param request
     * @param response
     * @param session
     * @return
     * @throws UserException
     */
    @RequestMapping(value = "/login")
    public String login(String email, String password, String autoLogin, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws UserException {
        if(!ReUtil.isMatch("^([a-zA-Z]|[0-9])(\\w|\\-)+@[a-zA-Z0-9]+\\.([a-zA-Z]{2,4})$",email)){
            throw new UserException("账号格式不正确:"+email);
        }
        if (StrUtil.isEmpty(password) || password.length() < 6){
            throw new UserException(("密码为空"));
        }
        User user = userService.login(email, password);
        if (user == null){
            throw new UserException("用户为空");
        }
        session.setAttribute(Constants.SESSION_LOGINUSER,user);

        //自动登录
        System.out.println("autoLogin:"+autoLogin);
        if (!StrUtil.isEmpty(autoLogin) && "1".equalsIgnoreCase(autoLogin)){
            UserToken userToken = new UserToken(user,request);
            String token = userToken.getToken();

           //将token存入cookie返回客户端
            Cookie cookie = new Cookie(Constants.COOKIE_LOGIN_TOKEN,token);
            cookie.setPath("/");
            cookie.setMaxAge(60*60*24);//单位为秒
            //将token返回客户端
            response.addCookie(cookie);

            //将token和user存入Servlet
            HashMap<String,UserToken> userTokenHashMap = new HashMap<>();
            userTokenHashMap.put(token,userToken);
            //将map集合存入servletContext中,servletContext中包含多个用户的登录验证信息
            ServletContext context = request.getServletContext();
            context.setAttribute(Constants.CONTEXT_TOKEN_USERTOKEN,userTokenHashMap);
        }
        return "redirect:/";
    }


    /**
     * 退出登录
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request,HttpServletResponse response){
        //设置session失效
        request.getSession().invalidate();
        //设置一个新cookie覆盖之前的cookie
        Cookie cookie = new Cookie(Constants.COOKIE_LOGIN_TOKEN,"invalid");
        cookie.setPath("/");
        cookie.setMaxAge(1);
        response.addCookie(cookie);
        //删除服务端的自动登录的token
        Cookie[] cks = request.getCookies();
        if (cks != null){
            for (Cookie ck : cks){
                //判断cookie是否为自动登录的cookie，如果是，则获取其token，删除map集合的自动登录信息
                if (Constants.COOKIE_LOGIN_TOKEN.equalsIgnoreCase(ck.getName())){
                    ServletContext context = request.getServletContext();
                    HashMap<String,UserToken> userTokenHashMap = (HashMap<String, UserToken>) context.getAttribute(Constants.CONTEXT_TOKEN_USERTOKEN);
                    userTokenHashMap.remove(ck.getValue()); //ck.getValue()为token，map集合以token为key
                }
            }
        }


        return "redirect:/";
    }



    /**
     * 发送重置密码url
     * @param email
     * @return
     */
    @RequestMapping("/sendUrl")
    @ResponseBody
    public String sendUrl(String email){
        //发送一个链接 里面包含token验证（别人无法伪造）设置链接在规定时间内有效
        //token 包含(当前时间 email 加盐)MD5加密
        //在token后面加上email、time的参数 重置密码功能需要对token进行验证
        //最后用base64编码（目的仅为了让用户看不懂具体的url）
        String resetUrl = "localhost:8080/resetPsdPage";
        StringBuilder builder = new StringBuilder();
        long time = System.currentTimeMillis();
        builder.append(time);
        builder.append(email);
        builder.append(Constants.TOKEN_SEC_KEY);
        String token = DigestUtil.md5Hex(builder.toString());
        String params = token + "_" + email + "_" + time;
        String p = Base64.encode(params);
        String url = resetUrl + "?p=" + p;
        return "send email:"+url;
    }


    /**
     * 重置密码页面
     * @param p
     * @param model
     * @return
     */
    @RequestMapping("/resetPsdPage")
    public String resetPsdPage(String p, Model model){
        //判断传过来的token是否合法 过期
        VideoUtil.verifyUrl(p);
        model.addAttribute("p",p);
        return "resetPsd";

    }


    /**
     * 重置密码功能
     * @param p
     * @param password
     * @return
     */
    @RequestMapping("/resetPsd")
    @ResponseBody
    public String resetPsd(String p,String password){
        //判断传过来的token是否合法 过期
        VideoUtil.verifyUrl(p);
        //链接合法 时间未过期 则修改密码
        String params = Base64.decodeStr(p);
        String[] pars  = params.split("_");
        String email = pars[1];
        int result = userService.resetPsd(email,password);
        if (result == 0){
            throw new UserException("重置密码失败");
        }
        return "reset password success";
    }

}












