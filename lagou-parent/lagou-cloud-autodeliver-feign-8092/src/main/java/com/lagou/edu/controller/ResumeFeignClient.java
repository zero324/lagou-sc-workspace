package com.lagou.edu.controller;

import com.lagou.edu.controller.service.ResumeFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "lagou-service-resume",fallback =
        ResumeFallback.class,path = "/resume")
public interface ResumeFeignClient {
    @RequestMapping(value = "resume/getState/{userId}")
    public Integer getDefaultResumeByUserId(@PathVariable Long userId);
}
