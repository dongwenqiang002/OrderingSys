package indi.dwq.orderingSys.data.dao;

import indi.dwq.orderingSys.data.pojo.Eatery;

public interface EateryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Eatery record);

    int insertSelective(Eatery record);

    Eatery selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Eatery record);

    int updateByPrimaryKey(Eatery record);
}