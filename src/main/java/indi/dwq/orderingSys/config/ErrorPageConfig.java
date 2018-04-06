package indi.dwq.orderingSys.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author 董文强
 * @Time 2018/3/16 17:43
 */

@Controller
public class ErrorPageConfig implements ErrorController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ErrorPageConfig.class);


    private static final String ERROR_PATH = "/error";
    private Set<String> errorName = null;

    @PostConstruct
    public void initError() {
        try {
            File file = ResourceUtils.getFile("classpath:templates/error");
            errorName = new TreeSet<>();
            for (String s : Objects.requireNonNull(file.list())) {
                errorName.add(s.substring(0, 3));
                LOGGER.info("添加错误页面 {}", s);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(ERROR_PATH)
    public String errorrr(HttpServletResponse response, HttpServletRequest request, Exception e) {
        int statusInt = response.getStatus();
        LOGGER.error("页面出错,错误码:{}", statusInt);
        if (errorName.contains(Integer.toString(statusInt))) {
            if (statusInt == 404) {
              //  LOGGER.error("{}", response.getHeaderNames());
            }
            return "/error/" + statusInt;
        } else {
            return "/error/error";
        }
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }
}
