package indi.dwq.orderingSys.user.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author 董文强
 * @Time 2018/3/20 11:20
 */
@Controller
@RequestMapping("/foot")
public class FootController {
    private static final Logger LOGGER = LoggerFactory.getLogger(FootController.class);


    @RequestMapping("/footList")
    public ModelAndView footList() {
        LOGGER.info("列表页面!");
        ModelAndView modelAndView = new ModelAndView("/foot/footList");
        return modelAndView;
    }
}
