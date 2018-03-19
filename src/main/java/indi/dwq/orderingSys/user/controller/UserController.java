package indi.dwq.orderingSys.user.controller;


import indi.dwq.orderingSys.data.pojo.User;
import indi.dwq.orderingSys.data.pojo.UserDetail;
import indi.dwq.orderingSys.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.HttpSession;

@RestController
@Controller
@RequestMapping("/user")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

   /* @Autowired
    private UserMapper userMapper;*/

    @Autowired
    private UserService userService;

    @GetMapping("/sele")
    public String index(HttpSession session) {
        return "/loginView";

    }

    /*@GetMapping("/register.html")
    public ModelAndView registerHtml(){
        LOGGER.info("注册页面");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/register");
        return modelAndView;
    }*/

    @PostMapping("/reg")
    public String register(User user, UserDetail detail){
        LOGGER.info(user.toString());
        return "index";
    }

    @GetMapping("/aa")
    public ModelAndView aa(){
        return new ModelAndView("/index ::#header");
    }

}
