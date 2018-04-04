package indi.dwq.orderingSys.data;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;

/**
 * @author 董文强
 * @Time 2018/4/3 17:22
 */
//datasource配置
@Configuration
//设置mybatis接口的包
@MapperScan("indi.dwq.orderingSys.data.dao")
//开启事务 因为使用了 pagehelper 分页插件，mybatissession工厂被代理了所以设置 proxyTargetClass = true
@EnableTransactionManagement(proxyTargetClass = true)
@Component("DataSource")
public class TestDatasource implements DataSource {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestDatasource.class);

    @Autowired
    DataProperties dataProperties;

    @Autowired
    MybatisProperties mybatisProperties;
    @PostConstruct
    public void init() throws ClassNotFoundException {
        mybatisProperties.setMapperLocations(dataProperties.getMapperPath());
        Class.forName(dataProperties.getDriverClassName());
    }

    @Override
    public Connection getConnection() throws SQLException {
        return  DriverManager.getConnection(dataProperties.getDbUrl(), dataProperties.getUsername(), dataProperties.getPassword());
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return  DriverManager.getConnection(dataProperties.getDbUrl(), dataProperties.getUsername(), dataProperties.getPassword());
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {

    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {

    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return 0;
    }

    @Override
    public java.util.logging.Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }




}
