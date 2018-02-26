package indi.dwq.orderingSys.data;


import com.mchange.v2.c3p0.AbstractComboPooledDataSource;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.naming.Referenceable;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * mybatis自动化工具
 * free-idea-mybatis (https://github.com/wuzhizhan/free-idea-mybatis)
 */
@Configuration
@MapperScan("indi.dwq.orderingSys.data.dao")
@Component("DataSource")
public class DataSource extends AbstractComboPooledDataSource implements Serializable, Referenceable, javax.sql.DataSource {

    private static Logger LOGGER = LoggerFactory.getLogger(DataSource.class);

    private static final long serialVersionUID = 1L;


    @Override
    public Connection getConnection() {
        try {
            return super.getConnection();
        } catch (Exception e) {
            LOGGER.error("数据库connection对象失败: {}", e.getMessage());
            LOGGER.error("url: {}", getJdbcUrl());
            e.printStackTrace();
            return null;
        }

    }

    public DataSource() {
        super();
    }

    public DataSource(boolean autoregister) {
        super(autoregister);
    }

    public DataSource(String configName) {
        super(configName);
    }

    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.writeShort(2);
    }

    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        short version = ois.readShort();
        switch (version) {
            case 2:
                return;
            default:
                throw new IOException("Unsupported Serialized Version: " + version);
        }
    }

    @Autowired
    MybatisProperties mybatisProperties;
    @Autowired
    DataProperties dataProperties;

    /**
     * c3p0数据库初始化
     */
    @PostConstruct
    public void initDataSources() throws SQLException {

        DataProperties thii = dataProperties;
        mybatisProperties.setMapperLocations(thii.getMapperPath());
        super.setJdbcUrl(thii.getDbUrl());
        try {
            this.setDriverClass(thii.getDriverClassName());
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        super.setDataSourceName("DataSource");
        super.setPassword(thii.getPassword());
        super.setUser(thii.getUsername());
        super.setInitialPoolSize(this.initialPoolSize);
        super.setMinPoolSize(this.minPoolSize);
        super.setMaxPoolSize(this.maxPoolSize);
        super.setAcquireIncrement(this.acquireIncrement);
        super.setMaxIdleTime(this.maxIdleTime);
        super.setIdleConnectionTestPeriod(this.idleConnectionTestPeriod);
        super.setMaxStatements(this.maxStatements);
        super.setMaxStatementsPerConnection(this.maxStatementsPerConnection);
        super.setAcquireRetryAttempts(this.acquireRetryAttempts);
        super.setAcquireRetryDelay(this.acquireRetryDelay);
        super.setCheckoutTimeout(this.checkoutTimeout);
        super.setAutoCommitOnClose(this.autoCommitOnClose);
        super.setNumHelperThreads(this.numHelperThreads);


        try {
            getConnection(thii.getUsername(), thii.getPassword());
            LOGGER.info("初始化数据库连接池");
        } catch (Exception e) {
            LOGGER.info("初始化OK");
        }
        try {
            getConnection(thii.getUsername(), thii.getPassword());
        } catch (Exception e) {
            LOGGER.error("数据库ERROR");
            LOGGER.error(e.getMessage());
            LOGGER.error("url: {}", thii.getDbUrl());
            LOGGER.error("password: {}", thii.getPassword());
            LOGGER.error("name : {}", thii.getUsername());
            e.printStackTrace();
            throw e;
        }

    }


    //#initialPoolSize：连接池初始化时创建的连接数,default : 3，取值应在minPoolSize与maxPoolSize之间
    private int initialPoolSize = 10;

    // minPoolSize：连接池保持的最小连接数,default : 3
    private int minPoolSize = 10;

    //maxPoolSize：连接池中拥有的最大连接数，如果获得新连接时会使连接总数超过这个值则不会再获取新连接，而是等待其他连接释放，所以这个值有可能会设计地很大,default : 15
    private int maxPoolSize = 200;

    //#acquireIncrement：连接池在无空闲连接可用时一次性创建的新数据库连接数,default : 3
    private int acquireIncrement = 5;

    //管理连接池的大小和连接的生存时间
    //#maxIdleTime：连接的最大空闲时间，如果超过这个时间，某个数据库连接还没有被使用，则会断开掉这个连接。如果为0，则永远不会断开连接,即回收此连接。default : 0 单位 s
    private int maxIdleTime = 600;

    //idleConnectionTestPeriod：每900秒检查所有连接池中的空闲连接
    private int idleConnectionTestPeriod = 900;

    //#配置PreparedStatement缓存
    //#连接池为数据源缓存的PreparedStatement的总数。由于PreparedStatement属于单个Connection,所以这个数量应该根据应用中平均连接数乘以每个连接的平均PreparedStatement
    //#来计算。同时maxStatementsPerConnection的配置无效。default : 0（不建议使用）
    private int maxStatements = 200;

    //#连接池为数据源单个Connection缓存的PreparedStatement数，这个配置比maxStatements更有意义，因为它缓存的服务对象是单个数据连接，
    // #如果设置的好，肯定是可以提高性能的。为0的时候不缓存。default : 0（看情况而论）
    private int maxStatementsPerConnection = 0;

    /* #重连相关配置
    #acquireRetryAttempts：连接池在获得新连接失败时重试的次数，如果小于等于0则无限重试直至连接获得成功。default : 30（建议使用）*/
    private int acquireRetryAttempts = 5;

    //#acquireRetryDelay:两次连接中间隔时间，单位毫秒，连接池在获得新连接时的间隔时间。default :1000单位ms（建议使用）
    private int acquireRetryDelay = 1000;


    //#checkoutTimeout：配置当连接池所有连接用完时应用程序getConnection的等待时间。为0则无限等待直至有其他连接释放或者创建新的连接，
    //#不为0则当时间到的时候如果仍没有获得连接，则会抛出SQLException。
    //其实就是acquireRetryAttempts*acquireRetryDelay。default :0（与上面两个，有重复，选择其中两个都行）
    private int checkoutTimeout = 0;

    //#autoCommitOnClose：连接池在回收数据库连接时是否自动提交事务。如果为false，则会回滚未提交的事务，如果为true，则会自动提交事务。default :false（不建议使用）
    private boolean autoCommitOnClose = false;

    //#c3p0是异步操作的，缓慢的JDBC操作通过帮助进程完成。
    //扩展这些操作可以有效的提升性能 通过多线程实现多个操作同时被执行。Default:3
    private int numHelperThreads = 10;


}
