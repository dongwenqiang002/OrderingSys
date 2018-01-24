package indi.dwq.orderingSys.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/abc")
    public String index(){
        return "aaavvv11";

    }
}
