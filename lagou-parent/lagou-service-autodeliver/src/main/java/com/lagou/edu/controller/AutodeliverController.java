package com.lagou.edu.controller;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/autodeliver")
public class AutodeliverController {
    @Resource
    private RestTemplate restTemplate;

    @Resource
    private DiscoveryClient discoveryClient;

  /*  @RequestMapping("/resumestate/{userId}")
    public Integer resumestate(@PathVariable Long userId){
        Integer forObject = restTemplate.getForObject("http://localhost:8080/resume/getState/" + userId, Integer.class);
        return  forObject;
    }*/

    @RequestMapping("/resumestate/{userId}")
    public Integer resumestate(@PathVariable Long userId) {
        //discoveryClient  客户端获取 服务列表
        List<ServiceInstance> instances = discoveryClient.getInstances("lagou-server-resume");
        //从服务列表中选一个服务(负载均衡)
        ServiceInstance serviceInstance = instances.get(0);
        //获取服务信息的元数据
        String host = serviceInstance.getHost();
        int port = serviceInstance.getPort();
        String url ="http://"+host + ":" +port +"/resume/getState/" + userId;
        System.out.println("从eureka server集群中获取 服务信息拼接的url "+url);
        Integer forObject = restTemplate.getForObject(url, Integer.class);
        return forObject;
    }
}
