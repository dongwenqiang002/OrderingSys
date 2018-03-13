package indi.dwq.orderingSys.data.dao;

import indi.dwq.orderingSys.data.pojo.City;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(City record);

    int insertSelective(City record);

    City selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(City record);

    int updateByPrimaryKey(City record);

    Integer selectByName(String name);

    List<String> selectByParentid(Integer id);
}