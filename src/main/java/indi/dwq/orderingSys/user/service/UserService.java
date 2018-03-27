package indi.dwq.orderingSys.user.service;

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

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = userDao.findByUserName(name);
        if (user == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        return user;

    }


    public User register(User user, UserDetail detail){
        userDao.insert(user);
        return user;
    }

}
