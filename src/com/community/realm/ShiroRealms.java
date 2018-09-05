package com.community.realm;

import com.community.entity.User;
import com.community.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author: jack
 * @Create: 2018-08-16-22:28
 * @Desc: 自定义Realms用来与数据库交互
 **/
public class ShiroRealms extends AuthorizingRealm {

    @Autowired
    private UserService userServiceImpl;

    /**授权的方法*/
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /**认证的方法*/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("进入该方法");
        SimpleAuthenticationInfo info = null;

        //1.获取token,传入username
        String username =  token.getPrincipal().toString();
        String password = token.getCredentials().toString();
        System.out.println("username:"+username+","+"password:"+password);
        //2.根据用户名来查询数据库
        User user = userServiceImpl.findByName(username);

        //3.利用username作为盐值对页面的密码进行加密
        ByteSource salt = ByteSource.Util.bytes(username);
        if(user != null){
            info = new SimpleAuthenticationInfo(username,user.getPassword(),salt,getName());
        }

        return info;
    }
}
