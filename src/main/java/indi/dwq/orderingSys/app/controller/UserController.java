package indi.dwq.orderingSys.app.controller;


import indi.dwq.orderingSys.app.service.EmailService;
import indi.dwq.orderingSys.app.service.OrderService;
import indi.dwq.orderingSys.data.pojo.Order;
import indi.dwq.orderingSys.data.pojo.User;
import indi.dwq.orderingSys.data.pojo.UserDetail;
import indi.dwq.orderingSys.app.service.UserService;
import indi.dwq.orderingSys.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@Controller
@RequestMapping("/user")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);


    @Autowired
    private EmailService emailService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;
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
            LOGGER.info(user.toString());
            LOGGER.info(detail.toString());
            user.setRole("普通用户");
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
        LOGGER.info("注册验证码: {}",code);
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
        User user ;
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
    public boolean r(String email, String username, String name,String code, HttpSession session) {
        User user ;
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
            if (userDetail.getEmail().equals(email) && userDetail.getName().equals(name)) {
                return code.equals(session.getAttribute("regPasswordCode"));
            }
        }
        return false;
    }
    /**
     * 忘记密码时的二次验证,提交新密码
     */
    @PostMapping("/regPasswordSub")
    public boolean repassword(String email, String username, String name,String code,String password, HttpSession session) {
        User user ;
        try {
            user = (User) userService.loadUserByUsername(username);
        } catch (Exception e) {
            user = null;
        }
        if (user == null) {
            return false;
        }
        if (user.getUsername().equals(username)&&code.equals(session.getAttribute("regPasswordCode"))) {
            UserDetail userDetail = userService.getDetail(user.getDetailId());
            if (userDetail.getEmail().equals(email) && userDetail.getName().equals(name)) {
                user.setPassword(password);
                session.removeAttribute("regPasswordCode");
                return  userService.updatePassword(user);

            }
        }
        return false;
    }


    @GetMapping("/home.html")
    public ModelAndView lookOrder(HttpSession session) {
        LOGGER.info("根据用户ID查订单");
        User user = (User) session.getAttribute("user");
        if (user == null) throw new NullPointerException("用户未登录");
        ModelAndView modelAndView = new ModelAndView();
        List<Order> list = orderService.lookOrder(user.getId());
        //根据Order中的state分组
        Map<Integer, List<Order>> orderMap  = list.stream().collect(Collectors.groupingBy(Order::getState));
        modelAndView.addObject("orderMap", orderMap);
        modelAndView.setViewName("/user/home");
        return modelAndView;
    }

    @GetMapping("/orderOK")
    @ResponseBody
    public boolean orderOK(Integer orderid,HttpSession session) {
       LOGGER.info("用户确认订单");
        User user = (User) session.getAttribute("user");
        if (user == null) throw new NullPointerException("用户未登录");
        return orderService.orderOK(orderid, user.getId());

    }
}
