package indi.dwq.orderingSys.app.service;


import indi.dwq.orderingSys.data.dao.CityMapper;
import indi.dwq.orderingSys.data.pojo.City;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
     * 如果 name 有误，则返回 null
     */
    public List<String> name(String name) {

        Integer id = cityMapper.selectByName(name);
        if (id == null) {
            return null;
        }
        List<String> list = cityMapper.selectByParentid(id);
        return list;
    }

    /**
     * 进行模糊搜索
     */
    public List<String> likeName(String name) {

        Integer id = cityMapper.selectByName(name);
        if (id == null) {
            return null;
        }
        List<String> list = cityMapper.selectParentName(name);
        return list;
    }

    /**
     * 根据已输入的确定接下来的
     */
    public List findName(String name) {
        LOGGER.error(name);
        if (name == null || name.isEmpty()) {
            return this.cityMapper.selectByParentid(0);
        }
        List data = this.getCity(name);
        if (data == null || data.isEmpty()) {
            return this.cityMapper.selectByParentid(0);
        }
        return data;
    }

    /**
     * 获取字符串中可能的所有有效地区
     */
    private List<String> getCity(String cityStr) {

        char[] a = cityStr.toCharArray();

        Map<Integer, City> city = new HashMap<>();

        //获取字符串中可能的所有有效地区
        for (int i = 0; i < a.length; i++) {
            List<City> temp_cityList = null;
            for (int j = i + 2; j <= a.length; j++) {
                char[] temp_city = new char[20];
                cityStr.getChars(i, j, temp_city, 0);
                List<City> cityList = cityMapper.findName(String.valueOf(temp_city).trim());
                if (cityList == null || cityList.isEmpty()) {
                    i = j - 2;
                    break;
                } else {
                    temp_cityList = cityList;
                    if (j == a.length) {
                        i = a.length;
                        break;
                    }
                }
            }
            if (temp_cityList != null) {
                temp_cityList.forEach(v -> city.put(v.getId(), v));
            }
        }
        if (city.size() == 0) {
            return null;
        }
        //链接地区
        List<List<City>> list = new LinkedList();
        int i = 0;
        city.forEach((k, v) -> {
            List<City> citys = new LinkedList();
            list.add(i, citys);
            citys.add(v);
            for (int id = v.getParentid(); city.containsKey(id); ) {

                citys.add(0, city.get(id));
                id = city.get(id).getParentid();
            }

        });
        //获取最大可能性的地区
        list.sort((v1, v2) -> {
            if (v1.size() > v2.size()) return -1;
            else if (v1.size() < v2.size()) return 1;
            else {
                int v1Size = v1.get(v1.size() - 1).getAreaname().length();
                int v2Size = v2.get(v2.size() - 1).getAreaname().length();
                if (v1Size < v2Size) return -1;
                else return 0;
            }
        });
        List<City> firstList = list.get(0);
        City smallCity = firstList.get(firstList.size() - 1);
        cityMapper.ByParentid(smallCity.getId()).forEach(v -> {
            List temp_list = new LinkedList<>();
            temp_list.add(v);
            list.add(1, temp_list);
        });

        //填补完全
        list.forEach(v -> {
            //v.get(0).getParentid();
            for (int id = v.get(0).getParentid(); id != 0; id = v.get(0).getParentid()) {
                v.add(0, this.cityMapper.selectByPrimaryKey(id));
            }
        });


        List<String> cccc = new LinkedList<>();
        for (int i1 = 0; i1 < list.size() && i1 < 10; i1++) {
            StringBuilder c = new StringBuilder();
            list.get(i1).forEach(v -> c.append(v.getAreaname()));
            cccc.add(c.toString());
        }
        return cccc;
    }


}
