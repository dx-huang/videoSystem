package com.video.dto;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.video.domain.User;
import com.video.util.Constants;
import com.video.util.VideoUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public class UserToken{
    private User user;
    private String strTime;
    private String userAgent;
    private String ip;
    private String token;

    public UserToken(User user, HttpServletRequest request){
        this.user = user;
        this.strTime = DateUtil.now();
        this.userAgent = request.getHeader("User-Agent");
        this.ip = VideoUtil.getIpAddress(request);
        this.token = buildToken();
    }


    private String buildToken(){
        //生成token
        //邮箱 时间 userAgent ip 加盐
        StringBuilder builder = new StringBuilder();
        builder.append(user.getEmail());
        builder.append(strTime);
        builder.append(userAgent);
        builder.append(ip);
        builder.append(Constants.TOKEN_SEC_KEY);
        String token = DigestUtil.md5Hex(builder.toString());
        return token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getStrTime() {
        return strTime;
    }

    public void setStrTime(String strTime) {
        this.strTime = strTime;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
