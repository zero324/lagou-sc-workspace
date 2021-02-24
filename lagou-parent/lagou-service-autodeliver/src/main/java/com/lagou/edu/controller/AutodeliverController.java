package com.lagou.edu.controller;

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

    @RequestMapping("/resumestate/{userId}")
    public Integer resumestate(@PathVariable Long userId){
        Integer forObject = restTemplate.getForObject("http://localhost:8080/resume/getState/" + userId, Integer.class);
        return  forObject;
    }
}
