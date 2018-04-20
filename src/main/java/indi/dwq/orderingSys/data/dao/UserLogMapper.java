package indi.dwq.orderingSys.data.dao;

import indi.dwq.orderingSys.data.pojo.UserLog;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserLog record);

    int insertSelective(UserLog record);

    UserLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserLog record);

    int updateByPrimaryKey(UserLog record);
}