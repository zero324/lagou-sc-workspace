package com.agou.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.Entity;
import javax.sql.DataSource;

@SpringBootApplication
@EnableDiscoveryClient//开启注册中心客户端
@EntityScan("com.lagou.edu.pojo")
public class Oauth2ServerApplication9999 {
    public static void main(String[] args) {
        SpringApplication.run(Oauth2ServerApplication9999.class,args);
    }


    @Bean
    public PlatformTransactionManager txManager(DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }
}
