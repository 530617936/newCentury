package com.newCentury.web.shiro;

import com.newCentury.web.shiro.handler.ExceptionHandler;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.web.servlet.HandlerExceptionResolver;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @ClassName initShiro
 * @Description: TODO
 * @Author: 53061
 * @Date:2020/3/24
 */
@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //必须设置SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //setLoginUrl 如果不设置值，默认会自动寻找Web工程根目录下的“login.jsp”页面 或"/login"映射
        shiroFilterFactoryBean.setLoginUrl("/login");
        //设置无权限时跳转的url
        shiroFilterFactoryBean.setUnauthorizedUrl("/login");
        //拦截器
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        //开放登录接口
        filterChainDefinitionMap.put("/test1/login","anon");
        //其余接口一律拦截，这行代码必须放在所有权限设置的最后，否则会导致所有url被拦截
        filterChainDefinitionMap.put("/**","authc");
        //登录成功跳转的链接 (这个不知道怎么用，我都是自己实现跳转的)
        shiroFilterFactoryBean.setSuccessUrl("/page/main.html");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    /**
     * 凭证匹配器
     * 由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了
     * @return
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");//散列算法:这里使用MD5算法;
        hashedCredentialsMatcher.setHashIterations(2);//散列的次数，比如散列两次，相当于 md5(md5(""));
        return hashedCredentialsMatcher;
    }

    /**
     * 注入自定义身份认证realm
     * 必须写这个类 并加上@Bean注解，目的是注入 CustomRealm 否则会影响CustonRealm类中其他类的依赖注入
     * @return
     */
    @Bean
    public LoginRealm myShiroRealm() {
        LoginRealm myShiroRealm = new LoginRealm();
        //使用加密
//        myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return myShiroRealm;
    }

    /**
     * 注入securityManager
     * @return
     *
     * SecurityManager 类导入的应该是 import org.apache.shiro.mgt.SecurityManager;
     * 但是，如果你是复制代码过来的话，会默认导入 java.lang.SecurityManager
     * 这里也稍稍有点坑，其他的类的话，也是都属于 shiro 包里面的类
     */
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //设置realm
        securityManager.setRealm(myShiroRealm());
        return securityManager;
    }


    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 注册全局异常处理
     * @return
     */
    @Bean(name = "exceptionHandler")
    public HandlerExceptionResolver handlerExceptionResolver() {
        return new ExceptionHandler();
    }
}
