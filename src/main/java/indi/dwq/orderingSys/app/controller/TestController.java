package indi.dwq.orderingSys.app.controller;

import indi.dwq.orderingSys.app.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.Method;

/**
 * @author 董文强
 * @Time 2018/3/21 17:25
 */
@RequestMapping("/test")
@Controller
public class TestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private EmailService emailService;

    @RequestMapping("/{name}")
    @ResponseBody
    public String name(@PathVariable("name") String name) {

        if(emailService.sendMail("1136859952@qq.com")){
            return "true";
        }else{

        }
        return "/" + name;

    }
}
