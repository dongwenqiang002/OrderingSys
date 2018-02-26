package indi.dwq.orderingSys.auth;


import indi.dwq.orderingSys.data.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Documented;
import java.lang.reflect.Method;

/**
 * 登录拦截器
 */
@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginInterceptor.class);

    /**
     * controller 执行之前调用
     * 进行登录验证拦截
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
//        LOGGER.info("------preHandle-----");
//        if (!handler.getClass().isAssignableFrom(HandlerMethod.class)) {
//            return true;
//        }
        if (isInter(handler)) {
            User user = (User) request.getSession().getAttribute("user");
            if (user != null && user.getName() != null && !user.getName().isEmpty()) {
                LOGGER.error("登录！");
                return true;
            } else {
                LOGGER.error("未登录！");
                response.sendRedirect("login.html");
                return false;
            }
        }

        return true;
    }

    /**
     * 判断是否进行登录拦截
     */
    private static boolean isInter(Object handler) {
        final HandlerMethod handlerMethod = (HandlerMethod) handler;
        final Method method = handlerMethod.getMethod();
        final Class<?> clazz = method.getDeclaringClass();
        if (clazz.isAnnotationPresent(Pub.class) || method.isAnnotationPresent(Pub.class)) return false;
        if (method.isAnnotationPresent(Auth.class)) return true;
        return true;
    }


    /**
     * controller 执行之后，且页面渲染之前调用
     */
 /*   @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        LOGGER.info("------postHandle-----");

    }*/


    /**
     * 页面渲染之后调用，一般用于资源清理操作
     */
   /* @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        LOGGER.info("------afterCompletion-----");


    }*/


}
