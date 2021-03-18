package com.lagou.edu.controller;

import com.lagou.edu.pojo.Resume;
import com.lagou.edu.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resume")
@RefreshScope//设置动态刷新配置  注意是在使用配置中心配置(变量)的类上增加
public class ResumeController {
    @Autowired
    private ResumeService resumeService;

    @Value("${server.port}")
    private Integer port;
    @Value("${lagou.message}")
    private String message;
    @Value("${lagou.abc}")
    private String abc;
    @Value("${lagou.def}")
    private String def;
    @GetMapping("/getState/{userId}")
    public Integer getDefaultResumeByUserId(@PathVariable Long userId){
        System.out.println("调用了lagou-service-resume-8082");
        System.out.println("配置中心配置lagou.message"+message+abc+def);
       //return resumeService.findDefaultResumeByUserId(userId).getIsOpenResume();
       return port;
    }
}
