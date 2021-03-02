package com.lagou.edu.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
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

  /*  @RequestMapping("/resumestate/{userId}")
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
    }*/

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

    /**
     * 提供者模拟处理超时   服务消费端 断路器设置
     *
     * @param userId
     * @return
     */
    // 使⽤@HystrixCommand注解进⾏熔断控制
    @HystrixCommand(
            // commandProperties熔断的⼀些细节属性配置
            commandProperties = {
                    //一个属性一个@HystrixProperty注解  属性在hystrixcommandproperties类里
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")//默认是1000
            }
    )
    @RequestMapping("/resumestatetimeout/{userId}")
    public Integer resumestatetimeout(@PathVariable Long userId) {
        //利用ribbon做负载均衡  在注册testtemplate方法上加@LoadBalance
        String url = "http://lagou-server-resume/resume/getState/" + userId;
        Integer forObject = restTemplate.getForObject(url, Integer.class);
        return forObject;
    }

    /**
     * 提供者模拟处理超时   服务消费端 断路器设置
     *
     * @param userId
     * @return
     */
    // 使⽤@HystrixCommand注解进⾏熔断控制
    @HystrixCommand(
            // commandProperties熔断的⼀些细节属性配置
            commandProperties = {
                    //一个属性一个@HystrixProperty注解  属性在hystrixcommandproperties类里
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")//默认是1000
            }, fallbackMethod = "myfailback" // 回退⽅法
    )
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
    */
    public Integer myfailback(Long userid) {
        return -1;
    }
}
