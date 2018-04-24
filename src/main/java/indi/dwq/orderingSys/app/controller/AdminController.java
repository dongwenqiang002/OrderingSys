package indi.dwq.orderingSys.app.controller;

import indi.dwq.orderingSys.app.service.EateryService;
import indi.dwq.orderingSys.app.service.UserLogService;
import indi.dwq.orderingSys.app.service.UserService;
import indi.dwq.orderingSys.data.pojo.User;
import indi.dwq.orderingSys.data.pojo.UserLog;
import indi.dwq.orderingSys.util.PageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.util.DateUtils;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @author 董文强
 * @Time 2018/4/16 16:20
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private UserLogService userLogService;
    @Autowired
    private EateryService eateryService;

    /**
     * 管理员基本界面
     */
    @GetMapping("/index.html")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("/admin/index");
        return mv;
    }

    /**
     * 管理员首页
     */
    @GetMapping("/home.html")
    public ModelAndView home(HttpSession session) {
        User user = (User) session.getAttribute("user");
        ModelAndView mv = new ModelAndView("/admin/home");
        mv.addObject("userLog", userLogService.getLastLogin(user.getId()));
        return mv;
    }

    /**
     * 用户管理页面
     */
    @RequestMapping("/user.html")
    public ModelAndView lookUser(String pageNum) {
        ModelAndView mv = new ModelAndView("/admin/user");
        PageUtil.paging("userList", mv, 5, pageNum, () -> userService.getAll());
        return mv;
    }

    /**
     * 商家管理页面
     */
    @RequestMapping("/eatery.html")
    public ModelAndView lookEatery(String pageNum) {
        ModelAndView mv = new ModelAndView("/admin/eatery");
        LOGGER.info("商铺第{}页", pageNum);
        PageUtil.paging("eateryList", mv, 5, pageNum, () -> eateryService.getEattery());

        return mv;
    }

    /**
     * 用户日志页面
     */
    @RequestMapping("/userlog.html")
    public ModelAndView userLog(String pageNum) {
        ModelAndView mv = new ModelAndView("/admin/userlog");
        PageUtil.paging("userlogList", mv, 13, pageNum, () -> userLogService.getAll());

        return mv;
    }
    /**
     * 用户日志页面附带查询条件
     */
    @RequestMapping("/userlogSelect.html")
    public ModelAndView userLogSelect(String name, String username, Integer userId, Date startTime,Date endTime ,String pageNum) {
        ModelAndView mv = new ModelAndView("/admin/userlog ::#userlog_main_context");
        PageUtil.paging("userlogList", mv, 13, pageNum, () -> userLogService.getAll());

        return mv;
    }


}
