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


import javax.servlet.http.HttpSession;

@RestController
@Controller
@RequestMapping("/user")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);


    @Autowired
    private EmailService emailService;

    @Autowired
    private UserService userService;



    /**
     * 用户名效验
     * */
    @GetMapping("/regFindName")
    @ResponseBody
    public String index(String name) {
        if(name.isEmpty()||name.length()<3)return "false";
        try {
            User user = (User) userService.loadUserByUsername(name);
            if (user == null) return "false";
        } catch (Exception e) {
            return "false";
        }

        return "true";

    }



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
    /**
     *获取注册验证码
     * */
    @GetMapping("regCode")
    public boolean regCide(String emailAddress, HttpSession session) {
        String code = emailService.regCode(emailAddress);
        if (code != null && !code.isEmpty()) {
            session.setAttribute("regcode", code);
            return true;
        }
        return false;
    }
    /**
     * 注册验证码 验证
     * */
    @GetMapping("regCodeVer")
    public boolean regCideVer(String code, HttpSession session) {
        String sessionCode = (String) session.getAttribute("regcode");
        LOGGER.info("code:{}\n\t sessioncode:{}",code,sessionCode);
        return  sessionCode != null&& code != null &&
                !code.isEmpty() && !sessionCode.isEmpty() &&
                code.equalsIgnoreCase(sessionCode);
    }




}
