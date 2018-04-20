package indi.dwq.orderingSys.app.controller;

import indi.dwq.orderingSys.app.service.EmailService;
import indi.dwq.orderingSys.app.service.UserService;
import indi.dwq.orderingSys.data.pojo.User;
import indi.dwq.orderingSys.data.pojo.UserDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author 董文强
 * @Time 2018/4/17 13:59
 */
@Controller
@RequestMapping("/admin/user")
public class AdminUserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AdminUserController.class);

    @Autowired
    private EmailService emailService;
    @Autowired
    private UserService userService;


    @GetMapping("/updata")
    public ModelAndView updataUser(Integer userId) {
        LOGGER.info("用户ID:{}", userId);
        ModelAndView mv = new ModelAndView("/admin/userUpdataModal");
        mv.addObject("user", userService.getUser(userId));
        return mv;
    }

    @GetMapping("/add")
    public ModelAndView addUser() {
        ModelAndView mv = new ModelAndView("/admin/userAddModal");
        return mv;
    }

    @PostMapping("/add")
    @ResponseBody
    public boolean addUser(User user, UserDetail userDetail) throws Exception {
        LOGGER.info(user.toString());
        LOGGER.info(userDetail.toString());
        User resultUser = userService.register(user, userDetail);
        return resultUser != null;
    }

    @PostMapping("/updata")
    @ResponseBody
    public boolean updataUser(User user, UserDetail userDetail) {
        LOGGER.info(user.toString());
        LOGGER.info(userDetail.toString());
        User resultUser = userService.updata(user, userDetail);
        return resultUser != null;
    }

    @PostMapping("/delete")
    @ResponseBody
    public boolean deleteUser(Integer userId) {
        return userService.removeUser(userId);
        //return false;
    }

    @PostMapping("/rePassword")
    @ResponseBody
    public boolean rePassword(Integer userId) {
        String email = (String) userService.getUser(userId).get("email");
        if (email == null) return false;
        String password = emailService.sendPasswordMail(email);
        if (password == null) return false;
        User user = new User();
        user.setId(userId);
        user.setPassword(password);
        return userService.updatePassword(user);
    }
}
