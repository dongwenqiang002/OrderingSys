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
            if(user == null){
                resp.sendRedirect("/login.html");
            }
            if(user.getRole().equals("管理员"));
            chain.doFilter(request,response);
        }catch (Exception e){
            ((HttpServletResponse) response).setStatus(111);
            return;
        }
        ((HttpServletResponse) response).setStatus(111);
    }

    @Override
    public void destroy() {

    }
}
