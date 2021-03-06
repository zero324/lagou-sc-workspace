package com.lagou.edu.controller;

import com.lagou.edu.pojo.Resume;
import com.lagou.edu.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resume")
public class ResumeController {
    @Autowired
    private ResumeService resumeService;

    @Value("${server.port}")
    private Integer port;
    @GetMapping("/getState/{userId}")
    public Integer getDefaultResumeByUserId(@PathVariable Long userId){
        System.out.println("调用了lagou-service-resume-8081");
       //return resumeService.findDefaultResumeByUserId(userId).getIsOpenResume();
       return port;
    }
}
