package com.lagou.edu.service;

import org.springframework.web.bind.annotation.PathVariable;

public interface ResumeService {
     Integer getDefaultResumeByUserId(Long userId);
}
