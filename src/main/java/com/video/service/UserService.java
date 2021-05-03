package com.video.service;

import com.video.domain.User;

public interface UserService {
    Boolean checkEmail(String email);

    int regist(String email,String password);

    User login(String email,String password);

    int resetPsd(String email,String password);

}
