package indi.dwq.orderingSys.user.controller;

import indi.dwq.orderingSys.data.dao.UserMapper;
import indi.dwq.orderingSys.data.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@Controller
@RequestMapping("/user")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserMapper userMapper;

    @GetMapping("/")
    public String index() {
        LOGGER.info(userMapper.selectByPrimaryKey(1).toString());
        return "/loginView";

    }


    @PostMapping
    public String register(User user){

        return "index";
    }

}
