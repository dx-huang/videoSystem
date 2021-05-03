package com.video.service.impl;

import cn.hutool.crypto.digest.DigestUtil;
import com.video.dao.UserDao;
import com.video.domain.User;
import com.video.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.handler.UserRoleAuthorizationInterceptor;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户业务逻辑类
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;


    /**
     * 检查邮箱账号是否已存在
     * @param email
     * @return
     */
    public Boolean checkEmail(String email){
        HashMap<String,Object> emailMap = new HashMap<>();
        emailMap.put("email",email);
        List<User> userList = userDao.findUserByCondition(emailMap);
        if (userList == null || userList.size() == 0){
            //账户不存在，可以被注册
            return true;
        }
        //账户存在，不可以被注册
        return false;
    }

    /**
     * 注册功能
     * @param email
     * @param password
     * @return
     */
    public int regist(String email, String password) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(DigestUtil.md5Hex(password));
        user.setCreateTime(new Date());
        user.setVipFlag(0);
        int result = userDao.insertUser(user);
        return result;
    }


    /**
     * 登录功能
     * @param email
     * @param password
     * @return
     */
    public User login(String email,String password){
        Map<String,Object> params = new HashMap<>();
        params.put("email",email);
        params.put("password",DigestUtil.md5Hex(password));
        List<User> users = userDao.findUserByCondition(params);
        if (users == null || users.size() == 0){
            return null;
        }
        return users.get(0);
    }

    /**
     * 重置密码功能
     * @param email
     * @param password
     * @return
     */
    public int resetPsd(String email,String password){
        Map<String,Object> params = new HashMap<>();
        params.put("email",email);
        params.put("password",DigestUtil.md5Hex(password));
        int result = userDao.updateUser(params);
        return result;
    }

}
