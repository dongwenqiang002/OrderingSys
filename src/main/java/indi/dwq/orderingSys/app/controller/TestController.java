package indi.dwq.orderingSys.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Method;

/**
 * @author 董文强
 * @Time 2018/3/21 17:25
 */
@RequestMapping("/test")
@Controller
public class TestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);


    @RequestMapping("/{name}")
    public String name(@PathVariable("name") String name) {

        return "/" + name;

    }
}
