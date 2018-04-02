package indi.dwq.orderingSys.data.dao;

import indi.dwq.orderingSys.data.pojo.City;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityMapper {



    City selectByPrimaryKey(Integer id);

    List<City> findName(String name);
    List<City>  ByParentid(Integer parentid);
    Integer selectByName(String name);

    List<String> selectByParentid(Integer id);

    List<String> selectParentName(String name);
}