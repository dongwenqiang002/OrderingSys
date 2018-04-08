package indi.dwq.orderingSys.app.service;


import com.github.pagehelper.PageHelper;
import indi.dwq.orderingSys.data.dao.EateryMapper;
import indi.dwq.orderingSys.data.pojo.Eatery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class EateryService {


    @Autowired
    private EateryMapper eateryMapper;


    public List<List> getAll() {

        //使用分页插件 PageHelper

        List list = eateryMapper.getAll();
        //分行
        return branch(list, 4);
    }

    /**
     * 分行算法
     */
    public static List branch(List list, int n) {
        List lists = new LinkedList();

        int i;
        for (i = 0; i < list.size() - 4; i += 4) {
            lists.add(list.subList(i, i + 4));
        }
        lists.add(list.subList(i, list.size()));
        return lists;
    }
}
