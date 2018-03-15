package indi.dwq.orderingSys.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 董文强
 * @Time 2018/3/15 14:55
 */
@Controller
public class ErrorController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ErrorController.class);

    @RequestMapping("403.html")
    public String eee(){
        return "/error/403";
    }
}
