package com.video.util;

import cn.hutool.core.codec.Base64;
import cn.hutool.crypto.digest.DigestUtil;
import com.video.exception.UserException;

import javax.servlet.http.HttpServletRequest;

/**
 * 相关工具类
 */

public class VideoUtil {

    //获取ip地址
    public static String getIpAddress(HttpServletRequest request){
        String ip = null;

        //X-Forwarded-For：Squid 服务代理
        String ipAddresses = request.getHeader("X-Forwarded-For");

        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            //Proxy-Client-IP：apache 服务代理
            ipAddresses = request.getHeader("Proxy-Client-IP");
        }

        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            //WL-Proxy-Client-IP：weblogic 服务代理
            ipAddresses = request.getHeader("WL-Proxy-Client-IP");
        }

        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            //HTTP_CLIENT_IP：有些代理服务器
            ipAddresses = request.getHeader("HTTP_CLIENT_IP");
        }

        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            //X-Real-IP：nginx服务代理
            ipAddresses = request.getHeader("X-Real-IP");
        }

        //有些网络通过多层代理，那么获取到的ip就会有多个，一般都是通过逗号（,）分割开来，并且第一个ip为客户端的真实IP
        if (ipAddresses != null && ipAddresses.length() != 0) {
            ip = ipAddresses.split(",")[0];
        }

        //还是不能获取到，最后再通过request.getRemoteAddr();获取
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            ip = request.getRemoteAddr();
        }

        return ip;
    }

    //重置密码中判断传过来的url是否合法 过期
    public static void verifyUrl(String p){
        String params = Base64.decodeStr(p);
        String[] pars = params.split("_");
        String token = pars[0];
        String email = pars[1];
        String time = pars[2];

        //判断token是否一致
        StringBuilder builder = new StringBuilder();
        builder.append(time);
        builder.append(email);
        builder.append(Constants.TOKEN_SEC_KEY);
        String stoken = DigestUtil.md5Hex(builder.toString());
        if (!stoken.equalsIgnoreCase(token)){
            throw new UserException("链接不合法");
        }
        //判断时间是否合法
        long nowTime = System.currentTimeMillis();
        if ((nowTime - Long.parseLong(time)) > 1000*20){
            throw new UserException("链接已超时");
        }
    }
}
