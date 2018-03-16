package indi.dwq.orderingSys.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 董文强
 * @Time 2018/3/16 17:43
 */

@Controller
public class ErrorPageConfig  implements ErrorController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ErrorPageConfig.class);


    private static final String ERROR_PATH = "/error";

    @RequestMapping(ERROR_PATH)
    public String errorrr(, HttpServletResponse response){

        int statusInt = response.getStatus();
        LOGGER.error("错误状态码: {}",statusInt);
        return "/error/"+statusInt;
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }
}
