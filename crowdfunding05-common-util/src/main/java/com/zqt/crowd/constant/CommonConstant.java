package com.zqt.crowd.constant;

/**
 * @author: zqtao
 * @description: 通用常量
 * @Date: 2020/5/21 18:37
 * @version: 1.0
 */
public class CommonConstant {

    // 通用消息
    public static final String MESSAGE_LOGIN_FAILED = "抱歉！账号或密码错误！请重新输入！";
    public static final String MESSAGE_LOGIN_ACCT_ALREADY_IN_USE = "抱歉！该账号已注册";
    public static final String MESSAGE_ACCESS_FORBIDEN = "请先登录再访问";
    public static final String MESSAGE_STRING_INVALIDATE = "字符串不合法！请不要传入空字符串！";
    public static final String MESSAGE_SYSTEM_ERROR_LOGIN_NOT_UNIQUE = "系统错误：登录账号不唯一";


    // 通用属性
    public static final String ATTR_NAME_EXCEPTION = "exception";
    public static final String ATTR_NAME_LOGIN_ADMIN = "loginAdmin";
    public static final String ATTR_NAME_PAGE_INFO = "pageInfo";
    public static final String MESSAGE_ACCESS_DENIED_MVC_INTERCEPTOR = "无权限访问";
    public static final String MESSAGE_ACCESS_DENIED_SPRING_SECURITY_INTERCEPTOR = "无权限访问";

    // 阿里云短信相关
    // 阿里云短信发送状态
    public static final String SMS_SEND_STATUS_SUCCESS = "OK";
    public static final String SMS_NOT_ACTIVE_MESSAGE = "短信验证通道关闭，请使用邮箱验证";


    // 短信存储在Redis中存储数据的key前缀
    public static final String SMS_REDIS_CODE_PREFIX = "SMS_REDIS_CODE_PREFIX_";

    // 邮箱相关
    public static final String MAIL_NOT_ACTIVE_MESSAGE = "邮箱验证通道关闭，请使用短信验证";

}
