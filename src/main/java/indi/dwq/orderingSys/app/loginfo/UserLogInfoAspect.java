package indi.dwq.orderingSys.app.loginfo;

import indi.dwq.orderingSys.app.service.UserLogService;
import indi.dwq.orderingSys.data.pojo.User;
import org.apache.catalina.SessionEvent;
import org.apache.catalina.SessionListener;
import org.apache.ibatis.plugin.Intercepts;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;

/**
 * @author 董文强
 * @Time 2018/4/20 10:35
 */
@Aspect
@Configuration
@WebListener
public class UserLogInfoAspect implements LogoutHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserLogInfoAspect.class);

    @Autowired
    private UserLogService userLogService;


    @Pointcut("execution(* indi.dwq.orderingSys.config.UserVerificationConfig.authenticate(*))")
    private void loginPoint() {
    }

    @AfterReturning(returning = "rvt", pointcut = "loginPoint()")
    public Object AfterExec(JoinPoint joinPoint, UsernamePasswordAuthenticationToken rvt) {
        LOGGER.info("登录记录");
        if (rvt != null) {
            try {

                User user = (User) rvt.getPrincipal();
                userLogService.loginInfo(user.getId());
            } catch (Exception e) {
                LOGGER.error(e.toString());
                return rvt;
            }
        }
        return rvt;
    }




    /**
     * 修改密码记录
     */
    @AfterReturning(returning = "rvt", pointcut = "execution(* indi.dwq.orderingSys.app.service.UserService.updatePassword(*))")
    public Object reapsswordAfter(JoinPoint joinPoint, boolean rvt) {

        LOGGER.info("改密码记录");

        if (rvt) {
            User user = (User) joinPoint.getArgs()[0];
            try {
                userLogService.restPasswordInfo(user);
            } catch (Exception e) {
                LOGGER.error(e.toString());
                return true;
            }
        }
        return rvt;
    }

    /**
     * spring security logout时 的注销session时 记录退出
     */
    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        LOGGER.info("退出拦截");
        HttpSession session = request.getSession();

        User user = (User) session.getAttribute("user");
        if (user == null) {
            return;
        }
        LOGGER.info(user.toString());
        userLogService.logoutInfo(user.getId());
    }
}
