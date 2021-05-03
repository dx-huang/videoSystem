package com.video.dao;

import com.video.domain.User;

import java.util.List;
import java.util.Map;


public interface UserDao {

    int insertUser(User user);

    int updateUser(Map<String,Object> map);

    List<User> findUserByCondition(Map<String,Object> params);

}
