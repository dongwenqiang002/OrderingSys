package indi.dwq.orderingSys.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * @author 董文强
 * @Time 2018/4/24 18:45
 */
@Configuration
@EnableWebSocketMessageBroker
public class ChatRoomWebSocketConfig implements WebSocketMessageBrokerConfigurer {
    private static final Logger LOGGER = LoggerFactory.getLogger(ChatRoomWebSocketConfig.class);


    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        //广播式应配置一个/topic消息代理
        config.enableSimpleBroker("/topic");
        //设置访问API
        //config.setApplicationDestinationPrefixes("/chat");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //注册一个STOMP的endpoint,并指定使用SockJS协议
        registry.addEndpoint("/chatRoom").withSockJS();
    }
}
