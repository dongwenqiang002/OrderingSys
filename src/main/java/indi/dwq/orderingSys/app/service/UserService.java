package indi.dwq.orderingSys.app.service;

import indi.dwq.orderingSys.data.dao.UserDetailMapper;
import indi.dwq.orderingSys.data.dao.UserMapper;
import indi.dwq.orderingSys.data.pojo.User;
import indi.dwq.orderingSys.data.pojo.UserDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author 董文强
 * @Time 2018/3/2 16:36
 */
@Service
public class UserService implements UserDetailsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserMapper userDao;
    @Autowired
    private UserDetailMapper userDetailMapper;


    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = userDao.findByUserName(name);
        if (user == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        return user;

    }


    public User register(User user, UserDetail detail) throws Exception {

        if (user.getPassword() == null || user.getPassword().isEmpty()) throw new Exception("密码为!");
        if (user.getUsername() == null || user.getUsername().isEmpty()) throw new Exception("用户名为空!");

        if (detail.getAddress() == null || user.getPassword().isEmpty()) throw new Exception("地址为空!");
        if (detail.getAge() == null || user.getPassword().isEmpty()) throw new Exception("年龄为空!");
        if (detail.getName() == null || user.getPassword().isEmpty()) throw new Exception("姓名为空!");
        if (detail.getPhone() == null || user.getPassword().isEmpty()) throw new Exception("电话为空!");
        if (detail.getSex() == null || user.getPassword().isEmpty()) throw new Exception("性别为空!");
        int id = userDetailMapper.insert(detail);
        user.setDetailId(id);
        userDao.insert(user);
        return user;
    }

}
