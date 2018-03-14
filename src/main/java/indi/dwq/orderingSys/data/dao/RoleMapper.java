package indi.dwq.orderingSys.data.dao;

import indi.dwq.orderingSys.data.pojo.RoleKey;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleMapper {
    int deleteByPrimaryKey(RoleKey key);

    int insert(RoleKey record);

    int insertSelective(RoleKey record);
    String selectNameById(Integer id);
}