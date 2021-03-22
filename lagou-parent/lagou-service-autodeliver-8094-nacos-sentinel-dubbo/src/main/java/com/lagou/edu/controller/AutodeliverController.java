package com.lagou.edu.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.lagou.edu.service.ResumeService;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/autodeliver")
public class AutodeliverController {

    //服务消费端注入dubbo接口用@reference (是dubbo的包)
    @Reference
    private ResumeService  resumeService;

    /**
     * @SentinelResource
    value：定义资源名
    blockHandlerClass：指定Sentinel规则异常兜底逻辑所在class类
    blockHandler：指定Sentinel规则异常兜底逻辑具体哪个⽅法
    fallbackClass：指定Java运⾏时异常兜底逻辑所在class类
    fallback：指定Java运⾏时异常兜底逻辑具体哪个⽅法
     */
    //注意 不符合sentinel规则的方法和java运行时出错的服务降级)
    /**如果不设置 value支援名称的话就是默认 方法的全限定名 例如com.lagou.edu.controller.AutodeliverController:resumestatetimeoutfailback(java.lang.Long)
    并且 路径资源(/autodeliver/resumestatetimeoutfailback/1111)是失效的  无论怎么设置都不会进行降级和流控*/
    /* @SentinelResource(value = "resumestatetimeoutfailback",blockHandlerClass =
            SentinelFallbackClass.class,//blockHandler 是不符合sentinel 熔断规则
            blockHandler = "handleException",fallback =
            "handleError",fallbackClass = SentinelFallbackClass.class)//fallback 是java运行时的错误*/
    @SentinelResource(value = "resumestatetimeoutfailback",blockHandlerClass =
            SentinelFallbackClass.class,//blockHandler 是不符合sentinel 熔断规则
            blockHandler = "handleException",fallback =
            "handleError",fallbackClass = SentinelFallbackClass.class)//fallback 是java运行时的错误
    @RequestMapping("/resumestatetimeoutfailback/{userId}")
    public Integer resumestatetimeoutfailback(@PathVariable Long userId) {
        // 模拟降级：模拟平均响应时间
       /* try {
            Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
        // 模拟降级：异常⽐例
        //int i = 1/0;
        //利用ribbon做负载均衡  在注册testtemplate方法上加@LoadBalance
       // String url = "http://lagou-server-resume/resume/getState/" + userId;
        //Integer forObject = restTemplate.getForObject(url, Integer.class);
        Integer defaultResumeByUserId = null;
        try {
            defaultResumeByUserId = resumeService.getDefaultResumeByUserId(userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
    public Integer myfailback(Long userid) {
        return -1;
    }
}
