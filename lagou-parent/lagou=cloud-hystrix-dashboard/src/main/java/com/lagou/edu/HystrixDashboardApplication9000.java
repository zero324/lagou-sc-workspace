package com.lagou.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableHystrixDashboard// 开启hystrix dashboard
@EnableDiscoveryClient
public class HystrixDashboardApplication9000 {
    /*
        正常状态是UP，跳闸是⼀种状态CIRCUIT_OPEN，可以通过/health查看，前提是⼯程中需要引⼊
        SpringBoot的actuator（健康监控），它提供了很多监控所需的接⼝，可以对应⽤系统进⾏配置查看、
        相关功能统计等。
     */
    public static void main(String[] args) {
        SpringApplication.run(HystrixDashboardApplication9000.class,args);
    }
}
