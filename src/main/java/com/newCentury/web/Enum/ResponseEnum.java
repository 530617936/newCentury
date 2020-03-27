package com.newCentury.web.Enum;

import lombok.Data;

/**
 * @ClassName ResponseEnum
 * @Description: TODO
 * @Author: 53061
 * @Date:2020/3/27
 */

public enum  ResponseEnum {
    Unauthorized(401,"缺少认证信息"),
    Forbidden(403,"无执行权限");

    private Integer code;
    private String message;

    ResponseEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode(){
        return code;
    }

    public String getMessage(){
        return message;
    }
}
