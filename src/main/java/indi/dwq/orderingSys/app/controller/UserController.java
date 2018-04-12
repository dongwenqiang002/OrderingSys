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
     */
    @GetMapping("/regFindName")
    @ResponseBody
    public String index(String name) {
        if (name.isEmpty() || name.length() < 3) return "false";
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
     * 获取注册验证码
     */
    @GetMapping("/regCode")
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
     */
    @GetMapping("/regCodeVer")
    public boolean regCideVer(String code, HttpSession session) {
        String sessionCode = (String) session.getAttribute("regcode");
        LOGGER.info("code:{}\n\t sessioncode:{}", code, sessionCode);
        return sessionCode != null && code != null &&
                !code.isEmpty() && !sessionCode.isEmpty() &&
                code.equalsIgnoreCase(sessionCode);
    }

    /**
     * 发送修改密码验证码
     */
    @GetMapping("/repasswordCode")
    public boolean repasswordCode(String emailAddress, HttpSession session) {
        if(emailAddress==null|| emailAddress.isEmpty())return false;
        User user = (User) session.getAttribute("user");
        UserDetail userDetail = userService.getDetail(user.getDetailId());
        if (emailAddress.equals(userDetail.getEmail())) {
            String code = emailService.repasswordCode(emailAddress);
            if (code != null && !code.isEmpty()) {
                session.setAttribute("repasswordCode", code);
                return true;
            }
        }
        return false;
    }

    /**
     * 修改密码
     */
    @PostMapping("/repassword")
    public boolean repassword(String password,String code ,HttpSession session) {
        LOGGER.info("新密码: {}",password);
        LOGGER.info("code: {}",code);
        LOGGER.info("repasswordCode: {}",session.getAttribute("repasswordCode"));
        if (password == null || password.isEmpty() || password.length() < 3) return false;
        if(!code.equals(session.getAttribute("repasswordCode")))return  false;

        User user = (User) session.getAttribute("user");
        user.setPassword(password);
        return  userService.updatePassword(user);
        //return true;
    }




    /**
     * 发送忘记密码验证码
     */
    @GetMapping("/regPasswordCode")
    public boolean regPasswordCode(String emailAddress,String username ,HttpSession session) {
        if(emailAddress==null|| emailAddress.isEmpty())return false;
        User user = null;
        try {
            user = (User) userService.loadUserByUsername(username);
        }catch (Exception e){
            user = null;
        }
        if(user == null)return false;
        UserDetail userDetail = userService.getDetail(user.getDetailId());
        if (emailAddress.equals(userDetail.getEmail())) {
            String code = emailService.repasswordCode(emailAddress);
            if (code != null && !code.isEmpty()) {
                session.setAttribute("regPasswordCode", code);
                return true;
            }
        }
        return false;
    }
    /**
     * 忘记密码时的验证
     */
    @PostMapping("/regPassword")
    public boolean r(String emailAddress, String username, String name,String code, HttpSession session) {
        User user = null;
        try {
            user = (User) userService.loadUserByUsername(username);
        } catch (Exception e) {
            user = null;
        }
        if (user == null) {
            return false;
        }
        if (user.getUsername().equals(username)) {
            UserDetail userDetail = userService.getDetail(user.getDetailId());
            if (userDetail.getEmail().equals(emailAddress) && userDetail.getName().equals(name)) {
                return code.equals(session.getAttribute("regPasswordCode"));
            }
        }

        return false;
    }
    /**
     * 忘记密码时的二次验证,提交新密码
     */
    @PostMapping("/regPasswordSub")
    public boolean regPassword(String emailAddress, String username, String name,String code,String password, HttpSession session) {
        User user = null;
        try {
            user = (User) userService.loadUserByUsername(username);
        } catch (Exception e) {
            user = null;
        }
        if (user == null) {
            return false;
        }
        LOGGER.info("emailAddress :{}",emailAddress);
        LOGGER.info("username :{}",username);
        LOGGER.info("name :{}",name);
        LOGGER.info("code :{}",code);
        LOGGER.info("password :{}",password);
        if (user.getUsername().equals(username)&&code.equals(session.getAttribute("regPasswordCode"))) {
            UserDetail userDetail = userService.getDetail(user.getDetailId());
            LOGGER.info("userDetail name : {}",userDetail.getName());
            LOGGER.info("userDetail emailAddress:{}",userDetail.getEmail());
            LOGGER.info("regPasswordCode :{}",session.getAttribute("regPasswordCode"));
            if (userDetail.getEmail().equals(emailAddress) && userDetail.getName().equals(name)) {
                user.setPassword(password);
                session.removeAttribute("regPasswordCode");
                return  userService.updatePassword(user);

            }
        }

        return false;
    }
}
