package indi.dwq.orderingSys.app.service;

import indi.dwq.orderingSys.data.dao.UserDetailMapper;
import indi.dwq.orderingSys.data.dao.UserMapper;
import indi.dwq.orderingSys.data.pojo.User;
import indi.dwq.orderingSys.data.pojo.UserDetail;
import indi.dwq.orderingSys.util.MD5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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

    public List<Map<String, Object>> getAll() {

        List<Map<String, Object>> listMap = new LinkedList<>();
        List<User> list = userDao.All();
        list.forEach(v -> {
            UserDetail userDetail = userDetailMapper.selectByPrimaryKey(v.getDetailId());
            Map map = new HashMap();
            listMap.add(map);
            map.put("id", v.getId());
            map.put("username", v.getUsername());
            map.put("role", v.getRole());
            map.put("address", userDetail.getAddress());
            map.put("email", userDetail.getEmail());
            map.put("age",userDetail.getAge());
            map.put("sex",userDetail.getSex());
            map.put("phone",userDetail.getPhone());
            map.put("name",userDetail.getName());
        });

        return listMap;
    }

    public UserDetail getDetail(Integer detailId) {
        return userDetailMapper.selectByPrimaryKey(detailId);

    }

    public boolean updatePassword(User user) {
        if (user == null || user.getId() == null || user.getPassword() == null) {
            return false;
        }
        User newUser = new User();
        String newPassword = MD5Util.MD5(user.getPassword());

        newUser.setId(user.getId());
        newUser.setPassword(newPassword);

        return userDao.updateByPrimaryKeySelective(newUser) == 1;
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
        user.setDetailId(detail.getId());
        userDao.insert(user);
        return user;
    }

    public Map getUser(Integer userId) {
        User v ;
        UserDetail userDetail;
        if(userId !=null && userId==0){
            v = new User();
            v.setId(0);
            userDetail = new UserDetail();
        }else {
           v = userDao.selectByPrimaryKey(userId);
           userDetail = userDetailMapper.selectByPrimaryKey(v.getDetailId());
        }
        Map map = new HashMap();

        map.put("id", v.getId());
        map.put("username", v.getUsername());
        map.put("role", v.getRole());
        map.put("address", userDetail.getAddress());
        map.put("email", userDetail.getEmail());
        map.put("age",userDetail.getAge());
        map.put("sex",userDetail.getSex());
        map.put("phone",userDetail.getPhone());
        map.put("name",userDetail.getName());
        map.forEach((k,val)->{
        if(val==null)map.put(k,"");
        });
        return  map;
    }

    public User updata(User user, UserDetail userDetail) {
        if(user==null||user.getId()==null)return null;
        try {
            if(user.getId()==0){
                user.setPassword(MD5Util.MD5("123456"));
                return register(user,userDetail);
            }
            User resultUser = userDao.selectByPrimaryKey(user.getId());

            userDao.updateByPrimaryKeySelective(user);

            userDetail.setId(resultUser.getDetailId());

            userDetailMapper.updateByPrimaryKeySelective(userDetail);

        }catch (Exception e){
            return null;
        }
        return userDao.selectByPrimaryKey(user.getId());
    }

    public boolean removeUser(Integer userId) {
        return  userDao.updateStateByUserId(userId,0);
    }


}
