package indi.dwq.orderingSys.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 董文强
 * @Time 2018/3/13 10:56
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer /*WebMvcConfigurerAdapter*/ {
    private static final Logger LOGGER = LoggerFactory.getLogger(WebMvcConfig.class);

    Map<String, String> map = new HashMap<String, String>() {
        {
            put("/login.html", "/login");
            put("/", "/index");
            put("/index.html", "/index");
            put("/register.html", "/register");
            put("/userHome.html", "/userHome");
        }
    };

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //registry.addViewController("/login").setViewName("/login");
        map.forEach((k, v) -> registry.addViewController(k).setViewName(v));
    }


}
