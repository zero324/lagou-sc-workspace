package com.lagou.edu.service;

import org.springframework.stereotype.Component;

@Component
public class ResumeFallback implements ResumeFeignClient {
    @Override
    public Integer getDefaultResumeByUserId(Long userId) {
        return -9;
    }
}
