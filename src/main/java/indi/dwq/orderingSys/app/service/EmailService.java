package indi.dwq.orderingSys.app.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * @author 董文强
 * @Time 2018/4/2 9:18
 */
@Service
public class EmailService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmailService.class);

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    String emailUsername;

    private Random random = new Random(System.currentTimeMillis());

    public boolean sendMail(String mailAddress) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(emailUsername);
            message.setTo(mailAddress+";"+"1136859952@qq.com"); //目标地址
            message.setSubject("主题：简单邮件");
            message.setText("\n" +
                    "server:\n" +
                    "#      defaultZone: http://horcrux:hor66@127.0.0.1:8651/eureka/");
            mailSender.send(message);
        } catch (Exception e) {
            LOGGER.info(e.getMessage());
            return false;
        }
        return true;

    }


    public String regCode(String mailAddress) {
        if (mailAddress == null || mailAddress.isEmpty()){
            return null;
        }
            StringBuilder code = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            code.append(Integer.valueOf(random.nextInt(9)).toString());
        }
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(emailUsername);
            message.setTo(mailAddress); //目标地址
            message.setSubject("注册验证码");
            message.setText("欢迎注册网上订餐管理系统," +
                    "您的验证码是 " + code + "\n" +
                    "请尽快完成注册,谢谢!");
            mailSender.send(message);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return null;
        }
        return code.toString();

    }


    public String repasswordCode(String mailAddress) {
        if (mailAddress == null || mailAddress.isEmpty()){
            return null;
        }
        int[] radi= {10,25,25};
        char[] radc={'0','a','A'};
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            int n = random.nextInt(3);
            code.append((char)(radc[n]+random.nextInt(radi[n])));
        }
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(emailUsername);
            message.setTo(mailAddress); //目标地址
            message.setSubject("重置密码");
            message.setText("您当前在执行重置密码操作," +
                    "您的验证码是 " + code + "\n" +
                    "请尽快完使用,谢谢!");
            mailSender.send(message);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return null;
        }
        return code.toString();

    }

    public static void main(String[] args) {
        Random random = new Random(System.currentTimeMillis());
        random.nextInt(9);
        for (int i = 0; i < 100; i++) {
            System.out.println(random.nextInt(10));
        }

    }
}
