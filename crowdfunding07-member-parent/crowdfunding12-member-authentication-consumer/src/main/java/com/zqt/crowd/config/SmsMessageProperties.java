package com.zqt.crowd.config;

import com.zqt.crowd.api.aliyun.entity.SendSmsReqDO;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author: zqtao
 * @description: 封装阿里云短信API 发送短信请求参数 数据对象
 * <p>
 * 作为配置类，从application.yml 文件中获取配置参数，用于进行短信请求
 * 注解 @Component 将当前类装配到容器
 * 注解 @ConfigurationProperties 标注当前类获取配置文件中那些内容，属性prefix 标注获取以什么开头的信息
 * <p>
 * 参数配置在 application.properties 或 application.yml 文件中，
 * 通过 @ConfigurationProperties 注解，我们可以方便的获取这些参数值
 * <p>
 * 其他程序需要使用此类，@Autowired 注入即可
 */
@Component
@ConfigurationProperties(prefix = "sms.message")
public class SmsMessageProperties extends SendSmsReqDO {

}

