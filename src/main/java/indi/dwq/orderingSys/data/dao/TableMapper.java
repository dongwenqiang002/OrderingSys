package indi.dwq.orderingSys.data.dao;

import indi.dwq.orderingSys.data.pojo.Table;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface TableMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Table record);

    int insertSelective(Table record);

    Table selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Table record);

    int updateByPrimaryKey(Table record);
}