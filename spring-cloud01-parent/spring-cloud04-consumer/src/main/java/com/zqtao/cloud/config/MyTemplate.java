package com.zqtao.cloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author: zqtao
 * @description: 创建 Template 通用配置类,提供 RestTemplate,用于远程调用（测试使用，正式使用）
 * @Date: 2020/7/7
 */
@Configuration
public class MyTemplate {

    /**
     * 提供 RestTemplate Bean
     * @return new RestTemplate();
     */
    // 此注解开启 RestTemplate Bean 的负载均衡功能，可以通过调用ribbon访问provider 集群
    @LoadBalanced
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}