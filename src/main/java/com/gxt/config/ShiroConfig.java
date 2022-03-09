package com.gxt.config;


import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {


    //管理设置安全管理器（拦截方式）
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("defaultWebSecurityManager") DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean subject = new ShiroFilterFactoryBean();
        //设置安全管理器
        subject.setSecurityManager(securityManager);
        Map<String, String> filterMap =new LinkedHashMap<String,String>();
        filterMap.put("/login/register","anon");
        filterMap.put("/login/loginTo","anon");
        filterMap.put("/toAbout","anon");
        filterMap.put("/toRecoverpw","anon");
        filterMap.put("/toRegister","anon");
        filterMap.put("/ajax/**","anon");

        filterMap.put("/css/**","anon");
        filterMap.put("/js/**","anon");
        filterMap.put("/picture/**","anon");
        filterMap.put("/image/**","anon");
        filterMap.put("/public/**","anon");
        filterMap.put("/file/**","anon");
        filterMap.put("/font/**","anon");

        filterMap.put("/**","authc");//表示/user/update这个请求只有登录后可以访问
        filterMap.put("/LoginOut","logout");//表示/user/update这个请求只有登录后可以访问

        subject.setLoginUrl("/toLogin");

        subject.setFilterChainDefinitionMap(filterMap);

        return subject;
    }

    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);

        return securityManager;

    }
    @Bean(name = "userRealm")
    public UserRealm userRealm(){
        return new UserRealm();
    }

}
