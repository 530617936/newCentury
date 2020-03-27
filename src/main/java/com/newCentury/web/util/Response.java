package com.newCentury.web.util;

import com.newCentury.web.Enum.ResponseEnum;

import java.io.Serializable;

/**
 * @ClassName Response
 * @Description: TODO
 * @Author: 53061
 * @Date:2020/3/23
 */
public class Response<T> implements Serializable {

    private Integer code;

    private String message;

    private T  data;

    public Response() {
    }

    public Response(Integer code, String message) {
        this.code = code;
        this.message = message;
    }


    public Response(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static Response success(Object data){
        return new Response(200,"成功",data);
    }

    public static Response success(){return new Response(200,"成功");}

    public static Response error(Object data){return new Response(204,"请求失败",data);}

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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static Response error(ResponseEnum responseEnum){
        return new Response(responseEnum.getCode(),responseEnum.getMessage(),null);
    }
}
