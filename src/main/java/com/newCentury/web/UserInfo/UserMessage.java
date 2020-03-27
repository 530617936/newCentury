package com.newCentury.web.UserInfo;

import com.newCentury.web.util.UserInfo;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName UserMessage  存放登录用户数据
 * @Description: TODO
 * @Author: 53061
 * @Date:2020/3/27
 */
public class UserMessage {
    private static ConcurrentHashMap userInfo = new ConcurrentHashMap<String,UserInfo>();
}
