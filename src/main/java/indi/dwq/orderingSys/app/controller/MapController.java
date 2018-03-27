package indi.dwq.orderingSys.app.controller;

import indi.dwq.orderingSys.app.service.MapService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author 董文强
 * @Time 2018/3/2 10:19
 * 地图有关功能的controller
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
    public List name(String name , HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", "*");

        List list = mapService.name(name);
        return list;
    }
}
