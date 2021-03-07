package com.lagou.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer //开启配置中心服务端
public class ConfigServerApplication9003 {
    public static void main(String[] args) {
        SpringApplication.run(ConfigServerApplication9003.class,args);
    }
}
