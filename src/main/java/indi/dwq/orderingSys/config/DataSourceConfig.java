package indi.dwq.orderingSys.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.SQLException;

@Configuration
public class DataSourceConfig {

/*
        private static Logger LOGGER = LoggerFactory.getLogger(DataSourceConfig.class);
        private static final long serialVersionUID = 1L;

        private String dbUrl="jdbc:mysql://localhost:3306/network?useUnicode=true&characterEncoding=utf-8&useSSL=false";
        private String username ="root";
        private String password = "root";
        private String driverClassName ="com.mysql.jdbc.Driver";

        @Override
        public Connection getConnection() throws SQLException {
            return super.getConnection();
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
            switch(version) {
                case 2:
                    return;
                default:
                    throw new IOException("Unsupported Serialized Version: " + version);
            }
        }



        @PostConstruct
        public void init(){
            this.setJdbcUrl(dbUrl);
            try {
                this.setDriverClass(driverClassName);
            } catch (PropertyVetoException e) {
                e.printStackTrace();
            }
            this.setDataSourceName("dataSource");
            this.setPassword(password);
            this.setUser(username);

        }


*/
}
