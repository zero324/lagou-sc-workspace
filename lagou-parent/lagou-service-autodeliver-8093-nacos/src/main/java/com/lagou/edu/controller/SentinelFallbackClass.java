package com.lagou.edu.controller;

import com.alibaba.csp.sentinel.slots.block.BlockException;

public class SentinelFallbackClass {
    // 整体要求和当时Hystrix⼀样，这⾥还需要在形参最后添加BlockException参数，⽤于接收异常
    // 注意：⽅法是静态的  如果不讲降级方法  java运行错误方法 提取出一个类的话(也就是在其本身业务类中)  就不用加static
    public static Integer handleException(Long userId, BlockException
            blockException) {
        return -100;
    }
    public static Integer handleError(Long userId) {
        return -500;
    }
}
