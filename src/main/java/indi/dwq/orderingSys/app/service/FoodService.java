package indi.dwq.orderingSys.app.service;


import com.github.pagehelper.PageHelper;
import indi.dwq.orderingSys.data.dao.FoodMapper;
import indi.dwq.orderingSys.data.pojo.Food;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class FoodService {

    private static int pageSzie = 10;

    @Autowired
    private FoodMapper foodMapper;


    public List<Food> getAll(Integer eateryId ){


        return  foodMapper.getAll(eateryId);
    }
}

