package indi.dwq.orderingSys.user.controller;

import indi.dwq.orderingSys.data.dao.UserMapper;
import indi.dwq.orderingSys.data.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
public class UserController {

    public static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserMapper userMapper;

    @GetMapping("/")
    public String index() {
        LOGGER.info(userMapper.selectByPrimaryKey(1).toString());
        return "/loginView";

    }

    @PostMapping("/login")
    public User login(User user) {


        return null;
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect://";
    }

    @PostMapping
    public String register(User user){

        return "index";
    }

}
