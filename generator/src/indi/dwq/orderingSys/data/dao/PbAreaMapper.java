package indi.dwq.orderingSys.data.dao;

import indi.dwq.orderingSys.data.pojo.PbArea;

public interface PbAreaMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PbArea record);

    int insertSelective(PbArea record);

    PbArea selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PbArea record);

    int updateByPrimaryKey(PbArea record);
}