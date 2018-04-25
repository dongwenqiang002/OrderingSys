package indi.dwq.orderingSys.data.dao;

import indi.dwq.orderingSys.data.pojo.Message;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MessageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Message record);



    Message selectByPrimaryKey(Integer id);



    List<Message> selectToday();
}