package indi.dwq.orderingSys.config;



import indi.dwq.orderingSys.data.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@Component
//@ServletComponentScan
//@WebFilter(urlPatterns = "/eatery/*.html",filterName = "EateryFilter")
public class EateryFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(FilterUrl.class);


    @Override
    public void init(javax.servlet.FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        try {
            User user = (User) request.getSession().getAttribute("user");
            if (user == null) {
                response.sendRedirect("/login.html");
                return;
            }
            LOGGER.info("当前用户为{},请求商铺访问登录",user.getRole());
            if (user.getRole().equals("商铺")) {
                filterChain.doFilter(request, response);
                return;
            }
        } catch (Exception e) {
            response.sendError(403);
            return;
        }
        LOGGER.error("无权限!");
        response.sendError(403,"权限错误");
        return;


    }


    @Override
    public void destroy() {

    }
}
