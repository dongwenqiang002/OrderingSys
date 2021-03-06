package indi.dwq.orderingSys.config;

import org.apache.catalina.Session;
import org.apache.catalina.SessionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpSession;
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
            put("/login.html", "/user/login");
            put("/", "/index");
            put("/index", "/index");
            put("/index.html", "/index");
            put("/register.html", "/user/register");
            put("/user/regPassword.html", "/user/regPassword");
            put("/user/restPassword.html", "/user/restPassword");
            //put("/userHome.html", "/food/lookOrder");
        }
    };

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/userHome.html","/user/home.html");
        map.forEach((k, v) -> registry.addViewController(k).setViewName(v));
    }


}
