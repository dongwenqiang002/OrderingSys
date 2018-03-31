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
    //每页页数
    private static int eateryPageSize = 12;

    @Autowired
    private EateryMapper eateryMapper ;


    public List<List> getAll(Integer pageNum){
        if(pageNum == null || pageNum.intValue() == 0){
            pageNum = 1;
        }
        //使用分页插件 PageHelper
        PageHelper.startPage(pageNum, eateryPageSize);
        List list = eateryMapper.getAll();
        return branch(list,4);
    }


    public static List branch(List list,int n){
        List lists = new LinkedList();

        int i;
        for( i = 0 ; i<list.size()-4;i+=4){
            lists.add(list.subList(i,i+4));
        }
        lists.add(list.subList(i,list.size()));
        return lists;
    }
}
