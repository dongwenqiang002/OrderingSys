package indi.dwq.orderingSys.config.data;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("indi.dwq.orderingSys.*.dao")
public class MybatisConfig {

    @Bean
    public DataSource dataSource(){
        return  new DataSource();
    }
}
