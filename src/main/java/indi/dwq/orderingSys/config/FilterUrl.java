package indi.dwq.orderingSys.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 董文强
 * @Time 2018/3/22 16:01
 */
@Component
@WebFilter(urlPatterns = "/*",filterName = "blosTest")
public class FilterUrl implements Filter{
    private static final Logger LOGGER = LoggerFactory.getLogger(FilterUrl.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request= (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        LOGGER.info("{}:{} " ,request.getMethod() ,request.getRequestURL().toString());
        filterChain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}
