package indi.dwq.orderingSys.data;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(DataProperties.class)
@ConfigurationProperties(prefix = "mydata")
public class DataProperties {

    // @Value("${data.mapper-path}")
    private String[] mapperPath = {"classpath*:/XML/*.xml"};
    // @Value("${data.url}")
    private String dbUrl = "jdbc:mysql://localhost:3306/network?useUnicode=true&characterEncoding=utf-8&useSSL=false";
    //@Value("${data.username}")
    private String username = "root";
    // @Value("${data.password}")
    private String password = "root";
    //  @Value("${data.driverClassName}")
    private String driverClassName = "com.mysql.jdbc.Driver";



    public String[] getMapperPath() {
        return mapperPath;
    }

    public void setMapperPath(String[] mapperPath) {
        this.mapperPath = mapperPath;
    }

    public String getDbUrl() {
        return dbUrl;
    }

    public void setDbUrl(String dbUrl) {
        this.dbUrl = dbUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }


}
