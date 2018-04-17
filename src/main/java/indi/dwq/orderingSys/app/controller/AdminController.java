package indi.dwq.orderingSys.app.controller;

import indi.dwq.orderingSys.app.service.UserService;
import indi.dwq.orderingSys.util.PageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author 董文强
 * @Time 2018/4/16 16:20
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private UserService userService;

    @GetMapping("/index.html")
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView("/admin/index");
        return mv;
    }

    @RequestMapping("/user.html")
    public ModelAndView lookUser(String pageNum){
        ModelAndView mv = new ModelAndView("/admin/user");
        PageUtil.paging("userList",mv,5,pageNum,()->userService.getAll());
        return mv;
     }


}
