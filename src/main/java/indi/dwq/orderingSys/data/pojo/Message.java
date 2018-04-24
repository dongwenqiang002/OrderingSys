package indi.dwq.orderingSys.data.pojo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * @author 董文强
 * @Time 2018/4/24 18:53
 */
public class Message {
    private static final Logger LOGGER = LoggerFactory.getLogger(Message.class);

    private String userName;
    private Date time;
    private String content;

    @Override
    public String toString() {
        return "Message{" +
                "userName='" + userName + '\'' +
                ", time=" + time +
                ", content='" + content + '\'' +
                '}';
    }

    public static Logger getLOGGER() {
        return LOGGER;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
