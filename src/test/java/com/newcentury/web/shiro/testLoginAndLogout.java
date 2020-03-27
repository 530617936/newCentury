package com.newcentury.web.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.jupiter.api.Test;
import org.junit.platform.engine.support.hierarchical.ThrowableCollector;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @ClassName testLoginAndLogout
 * @Description: TODO
 * @Author: 53061
 * @Date:2020/3/23
 */
@SpringBootTest
public class testLoginAndLogout {
    @Test
    public void test(){
        //1创建SecurityManager工厂 通过ini配置文件创建SecurityManager
        IniSecurityManagerFactory iniSecurityManagerFactory = new IniSecurityManagerFactory("classpath:shiro-realm.ini");
        //2创建SecurityManager
        SecurityManager securityManager = iniSecurityManagerFactory.getInstance();
        //3将securityManager设置到当前的运行环境中
        SecurityUtils.setSecurityManager(securityManager);
        // 从SecurityUtils中创建一个subject
        Subject subject = SecurityUtils.getSubject();
        // 在认证提交前准备token(令牌)
        // 这里的账号和密码 将来是由用户输入进去的
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("zhangsan", "123456");
        //执行认证提交
        try{
            //执行认证提交
            subject.login(usernamePasswordToken);
        }catch (AuthenticationException e){
            e.printStackTrace();
        }

        boolean authenticated = subject.isAuthenticated();
        System.out.println("是否认证通过"+ authenticated);

        subject.logout();

        //是否认证通过
        authenticated = subject.isAuthenticated();
        System.out.println("是否认证通过"+ authenticated);

    }

}
