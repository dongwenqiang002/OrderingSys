package indi.dwq.orderingSys.app.controller;

import com.sun.corba.se.impl.corba.RequestImpl;
import indi.dwq.orderingSys.app.service.ChatRoomService;
import indi.dwq.orderingSys.data.pojo.Message;
import indi.dwq.orderingSys.data.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.support.HttpRequestHandlerServlet;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 董文强
 * @Time 2018/4/24 18:59
 */
@Controller
public class ChatRoomController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ChatRoomController.class);

    @Autowired
    private ChatRoomService chatRoomService;


    @RequestMapping("/chatroom")
    @ResponseBody
    public Map getTodayMessage(HttpSession session,HttpServletRequest request) throws ServletException {
        Map map = new HashMap();
        map.put("message", chatRoomService.getTodyMessage());
        map.put("userName", ((User) session.getAttribute("user")).getUsername());

        return map;
    }
    //直接返回给当前链接的socket
    @SubscribeMapping
    public String rebacl(){

        return "hahahah";
    }


    @MessageMapping("/welcome") //当浏览器向服务端发送请求时,通过@MessageMapping映射/welcome这个地址,类似于@ResponseMapping
    @SendTo("/topic/getResponse")//当服务器有消息时,会对订阅了@SendTo中的路径的浏览器发送消息
    public Message say(String content, @Header UsernamePasswordAuthenticationToken simpUser) {

        User user = (User) simpUser.getPrincipal();
        WebSocketSession s;
        //s.getPrincipal().
//        HttpServletRequest request;request.getUserPrincipal()
//        s.sendMessage(aa);
//        s.sendMessage(new TextMessage());
        Message message = new Message();
        message.setContent(content);
        message.setUserName(user.getUsername());
        LOGGER.info(message.toString());

        return chatRoomService.sendMessage(message);
    }


}
