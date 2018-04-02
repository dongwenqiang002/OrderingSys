package indi.dwq.orderingSys.app.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * @author 董文强
 * @Time 2018/4/2 9:18
 */
@Service
public class EmailService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmailService.class);

    @Autowired
    private JavaMailSender mailSender;



    public boolean sendMail(String mailAddress){
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("dongwenqiang1995@foxmail.com");
            message.setTo(mailAddress); //自己给自己发送邮件
            message.setSubject("主题：简单邮件");
            message.setText("\n" +
                    "server:\n" +
                    "#      defaultZone: http://horcrux:hor66@127.0.0.1:8651/eureka/");
            mailSender.send(message);
        }catch (Exception e){
            LOGGER.info(e.getMessage());
            return false;
        }
        return true;

    }


}
