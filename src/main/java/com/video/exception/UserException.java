package com.video.exception;

/**
 * 注册方法异常
 */
public class UserException extends RuntimeException{

    public UserException(){}
    public UserException(String s){
        super(s);
    }
}
