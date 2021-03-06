package com.zqtao.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author zqtao
 *
 *  使用 @EnableEurekaServer 注解 开启Eureka 服务器功能,开启服务治理，标志当前微服务是 Eureka 注册服务中心
 *  服务端即是Eureka服务注册中心，客户端完成微服务向Eureka服务的注册与发现
 *  1、Eureka Server是服务端，负责管理各个微服务结点的信息和状态。
 *  2、在微服务上部署Eureka Client程序，远程访问Eureka Server将自己注册在Eureka Server。
 *  3、微服务需要调用另一个微服务时从Eureka Server中获取服务调用地址，进行远程调用。
 */
@EnableEurekaServer
@SpringBootApplication
public class EurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaApplication.class, args);
    }

}
