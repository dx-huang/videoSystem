package com.video.controller;

import cn.hutool.core.util.RandomUtil;
import com.video.util.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * 验证码控制类
 */
@Controller
public class VcodeController {
    //验证码字节数组
    static final char[] VCODE_ARRAY = {'0','1','2','3','4','5','6','7','8','9'};


    /**
     * 验证码方法
     * @param response
     * @param session
     */
    @RequestMapping("/vcode")
    public void vcode(HttpServletResponse response, HttpSession session){

        //awt工具类的画笔，画出验证码
        BufferedImage image = new BufferedImage(80,30,BufferedImage.TYPE_INT_RGB);
        Graphics2D gd = image.createGraphics();
        gd.drawRect(0,0,80,30);
        gd.setFont(new Font("宋体",Font.BOLD,25));
        //循环出四个数字验证码
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < 4; i++){
            int ri = RandomUtil.randomInt(10);
            char v = VCODE_ARRAY[ri];
            gd.setColor(new Color(
                    RandomUtil.randomInt(255),
                    RandomUtil.randomInt(255),
                    RandomUtil.randomInt(255)
            ));
            gd.drawString(""+v,(i+1)*15,25);
            builder.append(v);
        }

        //保存验证码。将验证码存入session
        session.setAttribute(Constants.SESSION_VCODE,builder.toString());


        //设置返回类型
        response.setContentType("image/jpeg");

        //设置验证码不缓存
        response.setDateHeader("Expires",-1);
        response.setHeader("Cache-Control","no-cache");
        response.setHeader("Pragma","no-cache");


        //将验证码写回
        try {
            ServletOutputStream ous = response.getOutputStream();
            ImageIO.write(image,"jpeg",ous);
            ous.flush();
            ous.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
