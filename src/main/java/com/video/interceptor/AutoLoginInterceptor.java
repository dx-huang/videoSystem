package com.video.interceptor;

import cn.hutool.core.date.DateUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.video.domain.User;
import com.video.dto.UserToken;
import com.video.util.Constants;
import com.video.util.VideoUtil;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 自动登录拦截器
 */
public class AutoLoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //获取cookie 看是否合法，若合法，未登录，直接登录
        Cookie[] cookies = request.getCookies();
        //判断cookie是否为空
        if (cookies == null || cookies.length == 0) {
          return true;
        }
        //循环取出cookie
        for (Cookie cookie : cookies){
            String cname = cookie.getName();
            //判断cookie名是否为COOKIE_LOGIN_TOKEN
            if (Constants.COOKIE_LOGIN_TOKEN.equalsIgnoreCase(cname)){
                String ctoken = cookie.getValue(); //cookie中的value为token
                //从服务器中的servletContext中取出储存token的map集合
                ServletContext context = request.getServletContext();
                HashMap<String, UserToken> userTokenHashMap = (HashMap<String, UserToken>) context.getAttribute(Constants.CONTEXT_TOKEN_USERTOKEN);
                //没有登录的数据
                if (userTokenHashMap == null){
                    return true;
                }
                UserToken userToken = userTokenHashMap.get(ctoken); //map集合以token为key
                if (userToken != null){
                    String strTime = userToken.getStrTime();//之前自动登录的时间
                    long preTime = DateUtil.parseDateTime(strTime).getTime();
                    long curTime = System.currentTimeMillis();
                    long time = curTime - preTime;
                    //主要判断以下时间是否过期和是否同一浏览器发起的
                    //超过规定时间，需重新登录
                    if (time > 60*60*24*100){
                        System.out.println("aiaiai");
                        return true;
                    }
                    //判断是否为同一浏览器、ip发起的请求(验证User-Agent ip是否一致)
                    //生成token
                    //邮箱 时间 userAgent ip 加盐
                    StringBuilder builder = new StringBuilder();
                    builder.append(userToken.getUser().getEmail());
                    builder.append(userToken.getStrTime());
                    //-----------------------
                    builder.append(request.getHeader("User-Agent"));
                    builder.append(VideoUtil.getIpAddress(request));
                    //------------------------
                    builder.append(Constants.TOKEN_SEC_KEY);
                    String token = DigestUtil.md5Hex(builder.toString());
                    if (!ctoken.equalsIgnoreCase(token)){
                        System.out.println("bibi");
                        return true;
                    }

                    //合法，自动登录
                    HttpSession session = request.getSession(true); // true：若存在会话则返回该会话，否则新建一个会话 false：若存在会话则返回该会话，否则返回NULL
                    session.setAttribute(Constants.SESSION_LOGINUSER,userToken.getUser());
                    break;
                }


            }

        }


        return true;
    }

}
