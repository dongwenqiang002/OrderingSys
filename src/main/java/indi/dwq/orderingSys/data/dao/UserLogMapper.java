package indi.dwq.orderingSys.data.dao;

import indi.dwq.orderingSys.data.pojo.UserLog;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface UserLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserLog record);

    int insertSelective(UserLog record);

    UserLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserLog record);

    int updateByPrimaryKey(UserLog record);

    List<UserLog> selectByUserId(Integer userId);

    UserLog selectLastTimeByUserId(@Param("userId") Integer userId, @Param("type") String type);

    List<UserLog> selectLast(String type);

    List<UserLog> getAll();

    List<UserLog> getUserLogAll(@Param("name") String name, @Param("username") String username, @Param("type") String type,@Param("userId") Integer userId, @Param("startTime") Date startTime, @Param("endTime") Date endTime);
}