package indi.dwq.orderingSys.app.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 餐馆controller
 * */

@Controller
@RequestMapping("/eatery")
public class EateryController {

    @RequestMapping("/list")
    public ModelAndView eateryList(){
        ModelAndView mv = new ModelAndView("/eatery/list");

       // mv.addObject();
        return null;
    }


}
