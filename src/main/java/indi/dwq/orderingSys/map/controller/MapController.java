package indi.dwq.orderingSys.map.controller;

import indi.dwq.orderingSys.map.service.MapService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * @author 董文强
 * @Time 2018/3/2 10:19
 */
@RestController
@RequestMapping("/map")
public class MapController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MapController.class);

    @Autowired
    MapService mapService;


    /**
     * 省市区街道四级联动
     * @param name 当前地区名， 可以是 省，市，区，县
     * @return  返回下一级列表
     * */
    @GetMapping("/sele")
    public List name(String name){

        List list = mapService.name(name);
        return list;
    }
}
