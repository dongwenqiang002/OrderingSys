package indi.dwq.orderingSys.data.dao;

import indi.dwq.orderingSys.data.pojo.Menutype;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface MenutypeMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Menutype record);

    int insertSelective(Menutype record);

    Menutype selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Menutype record);

    int updateByPrimaryKey(Menutype record);

}