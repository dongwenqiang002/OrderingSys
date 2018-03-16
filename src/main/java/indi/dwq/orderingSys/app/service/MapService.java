package indi.dwq.orderingSys.app.service;

import indi.dwq.orderingSys.data.dao.CityMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 董文强
 * @Time 2018/3/2 10:24
 */
@Service
public class MapService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MapService.class);

    @Autowired
    CityMapper cityMapper;


    /**
     * 省市区街道四级联动
     *
     * @param name 当前地区名， 可以是 省，市，区，县
     * @return 返回当前 name 所包含的所有下一级地区 列表
     *          如果 name 有误，则返回 null
     */
    public List<String> name(String name) {

        Integer id = cityMapper.selectByName(name);
        if (id == null) {
            return null;
        }
        List<String> list = cityMapper.selectByParentid(id);
        return list;
    }
}
