package com.newCentury.web.shiro;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.newCentury.web.entity.UserDao;
import com.newCentury.web.service.IUserService;
import com.newCentury.web.util.UserInfo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

/**
 * @ClassName myRealm1
 * @Description: TODO
 * @Author: 53061
 * @Date:2020/3/24
 */
@Component
public class LoginRealm extends AuthorizingRealm {
    @Autowired
    IUserService userService;

    /**
     * 获取授权信息
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = (String) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        //查询此用户是否存在
        UserDao one = userService.getOne(new LambdaQueryWrapper<UserDao>().eq(UserDao::getUsername, username));
        if (Objects.isNull(one)){
            //用户不存在
            throw new UnknownAccountException();
        }
        //在数据库中查询用户拥有的角色/权限
        authorizationInfo.setRoles(userService.getUserRoles(one.getId()));
        return authorizationInfo;
    }
    /**
     * 获取身份验证信息
     * shiro中，最终是通过Realm 来获取应用程序中的用户，角色及权限信息的
     * @param authenticationToken
     * @return
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
//        token.getUsername()  //获得用户名 String
//        token.getPrincipal() //获得用户名 Object
//        token.getPassword()  //获得密码 char[]
//        token.getCredentials() //获得密码 Object
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        char[] password = token.getPassword();
        //从数据库中获取对应的用户信息
        List<UserDao> userDaos = userService.list(new LambdaQueryWrapper<UserDao>().eq(UserDao::getUsername,username));
        if (CollectionUtils.isEmpty(userDaos)){
            throw new UnknownAccountException();
        }
        UserDao userDao = userDaos.get(0);
        if (!comparePassword(password,userDao.getPassword().toCharArray())){
            throw new IncorrectCredentialsException();
        }
        UserInfo userInfo = new UserInfo();
        System.out.println("userDao :" + userDao.toString());
        BeanUtils.copyProperties(userDao,userInfo);
        System.out.println("userInfo :" + userInfo);
        return new SimpleAuthenticationInfo(userInfo,password,getName());
    }

    //密码校验
    private Boolean comparePassword(char[] args1,char[] args2){
        if (args1.length == args2.length){
            for (int i=0;i<args1.length;i++){
                if (args1[i]!=args2[i]){
                    return false;
                }
            }
        }
        return true;
    }
}
