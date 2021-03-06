package com.lagou.edu.controller;

import com.lagou.edu.service.ResumeFeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/autodeliver")
public class AutodeliverController {
    @Resource
    private ResumeFeignClient resumeFeignClient;


    /**
     * 提供者模拟处理超时   服务消费端 断路器设置
     *
     * @param userId
     * @return
     */
    // 使⽤@HystrixCommand注解进⾏熔断控制
    /*@HystrixCommand(
            // 线程池标识，要保持唯⼀，不唯⼀的话就共⽤了
            threadPoolKey = "resumestatetimeoutfailback",
            // 线程池细节属性配置
            threadPoolProperties = {
                    @HystrixProperty(name="coreSize",value = "2"), // 线程数
                    @HystrixProperty(name="maxQueueSize",value="20") // 等待队列⻓度
            },
            // commandProperties熔断的⼀些细节属性配置
            commandProperties = {
                    //一个属性一个@HystrixProperty注解  属性在hystrixcommandproperties类里
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")//默认是1000
                    //hystrix高级配置 ,指定hystrix流程细节配置
                    //设置hystrix流程的时间窗口
                    ,@HystrixProperty(name =
                            "metrics.rollingStats.timeInMilliseconds",value = "8000"),
                    //最小请求书
                    @HystrixProperty(name =
                            "circuitBreaker.requestVolumeThreshold",value = "2"),
                    //失败率阀值 50%
                    @HystrixProperty(name =
                            "circuitBreaker.errorThresholdPercentage",value = "50"),
                    //活动窗口  每隔3秒 放一个请求访问
                    @HystrixProperty(name =
                            "circuitBreaker.sleepWindowInMilliseconds",value = "3000")

            }, fallbackMethod = "myfailback" // 回退⽅法
    )*/
    @RequestMapping("/resumestatetimeoutfailback/{userId}")
    public Integer resumestatetimeoutfailback(@PathVariable Long userId) {
        Integer defaultResumeByUserId = resumeFeignClient.getDefaultResumeByUserId(userId);
        return defaultResumeByUserId;
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

}
