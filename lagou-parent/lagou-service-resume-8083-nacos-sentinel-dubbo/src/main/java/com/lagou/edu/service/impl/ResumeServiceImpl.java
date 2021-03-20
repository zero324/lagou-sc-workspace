package com.lagou.edu.service.impl;

import com.lagou.edu.service.ResumeService;
import org.apache.dubbo.config.annotation.Service;

@Service//引入的是dubbo的service   服务的提供者的接口用dubbo的@Service
public class ResumeServiceImpl implements ResumeService {
    @Override
    public Integer getDefaultResumeByUserId(Long userId) {
        return 0;
    }
}