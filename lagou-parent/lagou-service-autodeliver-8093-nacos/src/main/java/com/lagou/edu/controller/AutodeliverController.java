package com.lagou.edu.controller;

import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@RequestMapping("/autodeliver")
public class AutodeliverController {
    @Resource
    private RestTemplate restTemplate;

    @Resource
    private DiscoveryClient discoveryClient;


    /**
     * ribbon 做负载均衡
     *
     * @param userId
     * @return
     */
    @RequestMapping("/resumestate/{userId}")
    public Integer resumestate(@PathVariable Long userId) {
        //利用ribbon做负载均衡  在注册testtemplate方法上加@LoadBalance
        String url = "http://lagou-server-resume/resume/getState/" + userId;
        Integer forObject = restTemplate.getForObject(url, Integer.class);
        return forObject;
    }


    /*
        方法上加一个@HystricCommand注解相当于一个熔断器
     */


    @RequestMapping("/resumestatetimeout/{userId}")
    public Integer resumestatetimeout(@PathVariable Long userId) {
        //利用ribbon做负载均衡  在注册testtemplate方法上加@LoadBalance
        String url = "http://lagou-server-resume/resume/getState/" + userId;
        Integer forObject = restTemplate.getForObject(url, Integer.class);
        return forObject;
    }


    @RequestMapping("/resumestatetimeoutfailback/{userId}")
    public Integer resumestatetimeoutfailback(@PathVariable Long userId) {
        //利用ribbon做负载均衡  在注册testtemplate方法上加@LoadBalance
        String url = "http://lagou-server-resume/resume/getState/" + userId;
        Integer forObject = restTemplate.getForObject(url, Integer.class);
        return forObject;
    }

    /*
    定义回退⽅法，返回预设默认值
    注意**
    降级（兜底）⽅法必须和被降级⽅法相同的⽅法签名（相同参数列表、相同返回值）
    可以在类上使⽤@DefaultProperties注解统⼀指定整个类中共⽤的降级（兜底）⽅法
    服务提供者端（简历微服务）模拟请求超时（线程休眠3s），只修改8080实例，8081不修改，对
    ⽐观察
    注意：该⽅法形参和返回值与原始⽅法保持⼀致
     可以在类上使用@defaultProperties()注解 配置这个类的共用服务降级 兜底数据 前提是该⽅法形参和返回值与原始⽅法保持⼀致
    */
    public Integer myfailback(Long userid) {
        return -1;
    }
}
