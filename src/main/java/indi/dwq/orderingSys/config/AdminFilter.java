package indi.dwq.orderingSys.config;

import indi.dwq.orderingSys.data.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.FilterConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 董文强
 * @Time 2018/4/10 10:38
 */
public class AdminFilter implements Filter {
    private static final Logger LOGGER = LoggerFactory.getLogger(AdminFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        LOGGER.info("管理员验证");

        HttpServletRequest req= (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        try {
            User user = (User) req.getSession().getAttribute("user");
            LOGGER.info("当前用户为{},请求管理员访问登录",user.getRole());
            if(user == null){
                resp.sendRedirect("/login.html");
            }
            if(user.getRole().equals("管理员")) {
                chain.doFilter(request, response);
            }
        }catch (Exception e){

            //((HttpServletResponse) response).setStatus(111);
            ((HttpServletResponse) response).sendError(111);
            ((HttpServletResponse) response).sendRedirect("/error");
            return;
        }
       // ((HttpServletResponse) response).setStatus(111);
        ((HttpServletResponse) response).sendError(111);
        ((HttpServletResponse) response).sendRedirect("/error");
        return;
    }

    @Override
    public void destroy() {

    }
}
