package indi.dwq.orderingSys.config;


import indi.dwq.orderingSys.config.FilterUrl;
import indi.dwq.orderingSys.data.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*@Component
//@ServletComponentScan
@WebFilter(urlPatterns = "/eatery/*.html",filterName = "EateryFilter")*/
public class EateryFilter implements Filter  {

    private static final Logger LOGGER = LoggerFactory.getLogger(FilterUrl.class);

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        LOGGER.info("商铺验证");
        HttpServletRequest request= (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        try {
            User user = (User) request.getSession().getAttribute("user");
            if(user == null){
                return;
            }
            LOGGER.info(user.getRole());
        }catch (Exception e){
            return;
        }
        // LOGGER.info("{}:{} " ,request.getMethod() ,request.getRequestURL().toString());
        filterChain.doFilter(request,response);

    }


    @Override
    public void destroy() {

    }
}
