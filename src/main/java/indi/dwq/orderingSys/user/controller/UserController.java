package indi.dwq.orderingSys.user.controller;

import indi.dwq.orderingSys.data.dao.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    public static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserMapper userMapper;

    @GetMapping("/abc")
    public String index(){
        LOGGER.info(userMapper.selectByPrimaryKey(1).toString());
        return "aaavvv11";

    }
}
