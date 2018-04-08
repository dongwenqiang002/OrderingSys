package indi.dwq.orderingSys.app.controller;


import indi.dwq.orderingSys.app.service.EmailService;
import indi.dwq.orderingSys.data.pojo.User;
import indi.dwq.orderingSys.data.pojo.UserDetail;
import indi.dwq.orderingSys.app.service.UserService;
import indi.dwq.orderingSys.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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
    private EmailService emailService;

    @Autowired
    private UserService userService;

    @GetMapping("/regFindName")
    @ResponseBody
    public String index(String name) {
        try {
            User user = (User) userService.loadUserByUsername(name);
            if (user == null) return "false";
        } catch (Exception e) {
            return "false";
        }
        return "true";

    }

    /*@GetMapping("/register.html")
    public ModelAndView registerHtml(){
        LOGGER.info("注册页面");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/register");
        return modelAndView;
    }*/

    /**
     * 注册
     */
    @PostMapping("/reg")
    public String register(User user, UserDetail detail) {

        try {
            userService.register(user, detail);
        } catch (Exception e) {
            return "注册失败";
        }
        LOGGER.info(JsonUtil.objectToJson(user));
        LOGGER.info(JsonUtil.objectToJson(detail));
        return "index";
    }

    @GetMapping("regCode")
    public boolean regCide(String emailAddress, HttpSession session) {
        String code = emailService.regCode(emailAddress);
        if (code != null && !code.isEmpty()) {
            session.setAttribute("regcode", code);
            return true;
        }
        return false;
    }

    @GetMapping("regCodeVer")
    public boolean regCideVer(String code, HttpSession session) {
        String sessionCode = (String) session.getAttribute("regcode");
        LOGGER.info("code:{}\n\t sessioncode:{}",code,sessionCode);
        return  sessionCode != null&& code != null && !code.isEmpty() && sessionCode != null
                && !sessionCode.isEmpty() && code.equalsIgnoreCase(sessionCode);
    }

    @GetMapping("/aa")
    public ModelAndView aa() {

        return new ModelAndView("/index ::#header");

    }

}
