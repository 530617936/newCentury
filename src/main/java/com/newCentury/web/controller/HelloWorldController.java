package com.newCentury.web.controller;

import com.newCentury.web.request.UserRequest;
import com.newCentury.web.util.Response;
import com.newCentury.web.util.UserInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @ClassName HelloWorldController
 * @Description: TODO
 * @Author: 53061
 * @Date:2020/3/23
 */
@RestController
@RequestMapping(value = "/test1" ,produces = {"application/json;charset=UTF-8"})
public class HelloWorldController {

    @GetMapping("/test1")
    public Response test1(){
        return Response.success("hello world !");
    }

    @GetMapping("/getUserInfo")
    public Response getUserInfo(){
        PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
        UserInfo userInfo = (UserInfo)principals.getPrimaryPrincipal();
        System.out.println(userInfo);
        return Response.success(userInfo);
    }

    @PostMapping("/login")
    public Response login(@RequestBody UserRequest userRequest){
        // 从SecurityUtils里边创建一个 subject
        Subject subject = SecurityUtils.getSubject();
        //再认证提交前准备token
        UsernamePasswordToken token = new UsernamePasswordToken(userRequest.getUsername(), userRequest.getPassword());
        //执行登录
        try{
            subject.login(token);
        }catch (UnknownAccountException e){
            return Response.error(e.getCause());
        }catch (IncorrectCredentialsException e){
            return Response.error(e.getCause());
        }
        //根据权限返回指定数据
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        UUID uuid = UUID.randomUUID();
        response.setHeader("Authorization",uuid.toString());
        return Response.success("登录成功");
    }
}
