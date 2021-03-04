package com.lagou.edu;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
//开启注册中心服务端
@EnableDiscoveryClient
//@EnableHystrix  开启hystrix熔断器
@EnableCircuitBreaker  //开启熔断器
//@SpringCloudApplication 是 @SpringBootApplication @EnableDiscoveryClient @EnableCircuitBreaker 组合注解
public class AutodeliverApplication {
    public static void main(String[] args) {
        SpringApplication.run(AutodeliverApplication.class, args);
    }

    @Bean
    @LoadBalanced  //加这个注解是使用ribbon做负载均衡
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    /*
        在被监测的微服务中注册监控servlet（⾃动投递微服务，监控数据就是来⾃于这个微服务）前提的有actuator jar
     */
    @Bean
    public ServletRegistrationBean getServlet() {
        HystrixMetricsStreamServlet streamServlet = new
                HystrixMetricsStreamServlet();
        ServletRegistrationBean registrationBean = new
                ServletRegistrationBean(streamServlet);
        registrationBean.setLoadOnStartup(1);
        registrationBean.addUrlMappings("/actuator/hystrix.stream");
        registrationBean.setName("HystrixMetricsStreamServlet");
        return registrationBean;
    }

}
