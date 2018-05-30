package indi.dwq.orderingSys.app.service;

import indi.dwq.orderingSys.data.dao.MessageMapper;
import indi.dwq.orderingSys.data.pojo.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author 董文强
 * @Time 2018/4/25 10:04
 * 聊天室功能
 */
@Service
public class ChatRoomService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ChatRoomService.class);


    @Autowired
    private MessageMapper messageMapper;

    public List<Message> getTodyMessage(){

        return  messageMapper.selectToday();
    }

    public Message sendMessage(Message message){
        if(message == null)return null;
        if(message.getUserName() ==null)return null;
        message.setTime(new Date());
        if(messageMapper.insert(message) == 1) {
            return message;
        }else {
            return null;
        }
    }

}
