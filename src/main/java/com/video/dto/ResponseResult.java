package com.video.dto;

/**
 * 后端传输给前端的格式
 */
public class ResponseResult <T>{
    private Integer code; // 0:失败 1:成功 3:格式不正确
    private String message; // 传输的信息
    private T date; //传输的数据

    public ResponseResult() {
    }

    public ResponseResult(Integer code, String message, T date) {
        this.code = code;
        this.message = message;
        this.date = date;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getDate() {
        return date;
    }

    public void setDate(T date) {
        this.date = date;
    }
}
