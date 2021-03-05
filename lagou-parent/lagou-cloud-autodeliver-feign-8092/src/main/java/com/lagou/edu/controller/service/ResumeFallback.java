package com.lagou.edu.controller.service;

import com.lagou.edu.controller.ResumeFeignClient;
import org.springframework.stereotype.Component;

@Component
public class ResumeFallback implements ResumeFeignClient {
    @Override
    public Integer getDefaultResumeByUserId(Long userId) {
        return -9;
    }
}
