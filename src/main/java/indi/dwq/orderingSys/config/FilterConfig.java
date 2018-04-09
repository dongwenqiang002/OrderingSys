package indi.dwq.orderingSys.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author 董文强
 * @Time 2018/4/9 17:47
 */
@Configuration
@Component
public class FilterConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(FilterConfig.class);


    @Bean
    public FilterRegistrationBean testFilterRegistration() {

        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new EateryFilter());
        registration.addUrlPatterns("/eatery/**");
        //registration.addInitParameter("paramName", "paramValue");
        registration.setName("eateryFilter");
        registration.setOrder(1);
        return registration;
    }
}
