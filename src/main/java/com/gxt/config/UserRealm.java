package com.gxt.config;


import com.gxt.pojo.Msg;
import com.gxt.service.MsgService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRealm extends AuthorizingRealm {
    @Autowired
    MsgService msgService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了授权方法");

        return null;
    }
    //在这里实现对用户的认证方法
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        UsernamePasswordToken userToken = (UsernamePasswordToken)token;
        Msg msg = msgService.getMsgByUserCode(userToken.getUsername());


        if (msg==null){
            return null;
        }
        System.out.println("执行了认证方法");

        return new SimpleAuthenticationInfo("",msg.getUserPassword(),"");


    }
}
