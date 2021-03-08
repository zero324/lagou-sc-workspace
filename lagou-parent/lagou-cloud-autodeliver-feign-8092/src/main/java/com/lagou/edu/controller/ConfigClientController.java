package com.lagou.edu.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RefreshScope
@RestController
@RequestMapping("/configclient")
public class ConfigClientController {
    @Value("${lagoumessage}")
    private String message;

    @RequestMapping("viewconfig")
    public String viewConfig(){
        return "lagou.message===>"+message;
    }

}
