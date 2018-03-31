package indi.dwq.orderingSys.security.filter;


import indi.dwq.orderingSys.config.FilterUrl;
import indi.dwq.orderingSys.data.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*@Component
@WebFilter(urlPatterns = "/eatery/*",filterName = "EateryFilter")*/
public class EateryFilter implements Filter  {

    private static final Logger LOGGER = LoggerFactory.getLogger(FilterUrl.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request= (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        try {
            User user = (User) request.getSession().getAttribute("user");
            if(user == null){

            }

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
