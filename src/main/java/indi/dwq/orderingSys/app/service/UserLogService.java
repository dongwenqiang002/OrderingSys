package indi.dwq.orderingSys.app.service;

import indi.dwq.orderingSys.data.dao.UserLogMapper;
import indi.dwq.orderingSys.data.dao.UserMapper;
import indi.dwq.orderingSys.data.pojo.User;
import indi.dwq.orderingSys.data.pojo.UserLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author 董文强
 * @Time 2018/4/20 9:55
 */
@Service
public class UserLogService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserLogService.class);
    @Autowired
    private UserLogMapper userLogMapper;
    @Autowired
    private UserMapper userMapper;


    public void loginInfo(Integer userId) {
        UserLog userLog = new UserLog();
        userLog.setTime(new Date());
        userLog.setType("登录");
        userLog.setUserId(userId);
        userLogMapper.insert(userLog);

    }

    public void logoutInfo(Integer userId) {
        UserLog userLog = new UserLog();
        userLog.setTime(new Date());
        userLog.setType("退出");
        userLog.setUserId(userId);
        userLogMapper.insert(userLog);
    }

    public void restPasswordInfo(User user) {
        LOGGER.info("修改密码记录");

        if (user == null) return;

        if (user.getId() == null) {
            if (user.getUsername() == null) return;
            user = userMapper.findByUserName(user.getUsername());
        }
        UserLog userLog = new UserLog();
        userLog.setTime(new Date());
        userLog.setType("修改密码");
        userLog.setUserId(user.getId());
        userLogMapper.insert(userLog);
    }
}
