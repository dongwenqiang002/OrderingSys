package indi.dwq.orderingSys.config;


import indi.dwq.orderingSys.app.service.UserService;
import indi.dwq.orderingSys.util.MD5Util;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.Collection;

/**
 * @author 董文强
 * @Time 2018/3/13 16:55
 * 验证用户名与密码
 */
@Component
public class UserVerificationConfig implements AuthenticationProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserVerificationConfig.class);

    @Autowired
    private UserService userService;
    @Autowired
    private HttpSession session;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        LOGGER.info("MD5登录");
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();
        UserDetails userDetials;

        try {
            userDetials = userService.loadUserByUsername(username);
        }catch (UsernameNotFoundException e){
            return null;
        }
        //获取用户权限列表
        Collection<? extends GrantedAuthority> authorities = userDetials.getAuthorities();

        //判断用户密码是否正确
        String MD5Password = MD5Util.MD5(password);
        if (MD5Password.equals(userDetials.getPassword())) {
            session.setAttribute("user",userDetials);
            return new UsernamePasswordAuthenticationToken(userDetials, password, authorities);
        } else {
            /*密码不正确*/
            return null;//new UsernamePasswordAuthenticationToken(userDetials,password,null);
        }
    }

    @Override
    public boolean supports(Class<?> arg0) {
        LOGGER.error(arg0.getName());
        return true;
    }

}