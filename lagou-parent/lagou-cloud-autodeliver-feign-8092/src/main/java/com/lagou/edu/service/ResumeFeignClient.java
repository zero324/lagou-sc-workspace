package com.lagou.edu.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "lagou-server-resume",fallback =ResumeFallback.class,path ="/resume" )
//@RequestMapping(value = "/resume")  开启熔断 注释掉@RequestMapping
public interface ResumeFeignClient {
    @RequestMapping(value = "/getState/{userId}")
    public Integer getDefaultResumeByUserId(@PathVariable Long userId);
}
