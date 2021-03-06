package com.zqt.crowd.mvc.config;

import com.google.gson.Gson;
import com.zqt.crowd.constant.CommonConstant;
import com.zqt.crowd.exception.AccessForbiddenException;
import com.zqt.crowd.exception.LoginAcctAlreadyInUseException;
import com.zqt.crowd.util.JudgeRequestTypeUtil;
import com.zqt.crowd.util.ResultEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: zqtao
 * @description: 封装 Spring 基于注解的异常处理器类
 * @Date: 2020/5/21 17:16
 * @version: 1.0
 */
// @ControllerAdvice表示当前类是一个基于注解的异常处理器类
@ControllerAdvice
public class BaseAnnotationExceptionResolver {

    // 基于注解的异常映射和基于 XML 的异常映射如果映射同一个异常类型，那么基于注解的方案优先。
//    @ExceptionHandler(value = AccessDeniedException.class)
//    public ModelAndView resolveException(Exception exception,
//                                         HttpServletRequest request,
//                                         HttpServletResponse response) throws IOException {
//        String viewName = "system-error";
//        return commonResolve(viewName, exception, request, response);
//    }

    /**
     * 自定义账号重复异常注解处理器
     */
    // @ExceptionHandler 注解将一个具体的异常类型和此方法关联
    @ExceptionHandler(value = LoginAcctAlreadyInUseException.class)
    public ModelAndView resolveLoginAcctAlreadyInUseException(
            LoginAcctAlreadyInUseException exception,
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        String viewName = "admin-add";
        return commonResolve(viewName, exception, request, response);
    }

    /**
     * 自定义访问控制异常注解处理器
     */
    @ExceptionHandler(value = AccessForbiddenException.class)
    public ModelAndView resolveAccessForbiddenException(
            AccessForbiddenException exception,
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {

        String viewName = "admin-login";
        return commonResolve(viewName, exception, request, response);
    }

    /**
     * 自定义数学异常映射注解处理器
     *
     * @param exception ArithmeticException
     */
    @ExceptionHandler(value = ArithmeticException.class)
    public ModelAndView resolveMathException(
            ArithmeticException exception,
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        String viewName = "system-error";
        return commonResolve(viewName, exception, request, response);
    }

    /**
     * 自定义空指针异常映射
     *
     * @param exception NullPointerException
     */
    // @ExceptionHandler将一个具体的异常类型和一个方法关联起来
    @ExceptionHandler(value = NullPointerException.class)
    public ModelAndView resolveNullPointerException(
            NullPointerException exception,
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {

        String viewName = "system-error";
        return commonResolve(viewName, exception, request, response);
    }

    /**
     * 捕获spring security 异常，主要是基于方法注解权限分配中抛出的异常
     *
     * @param exception AccessDeniedException
     */
    @ExceptionHandler(value = AccessDeniedException.class)
    public ModelAndView resolveAccessDeniedException(
            AccessDeniedException exception,
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {

        String viewName = "system-error";
        return commonResolve(viewName, new Exception(CommonConstant.MESSAGE_ACCESS_DENIED_MVC_INTERCEPTOR), request, response);
    }

    /**
     * @param viewName  异常处理完成后要去的视图页面
     * @param exception 实际捕获到的异常类型
     * @param request   当前请求对象
     * @param response  当前响应对象
     */
    private ModelAndView commonResolve(

            String viewName,
            Exception exception,
            HttpServletRequest request,
            HttpServletResponse response) throws IOException {

        // 1.判断当前请求类型
        boolean judgeResult = JudgeRequestTypeUtil.judgeRequestType(request);

        // 2.如果是Ajax请求
        if (judgeResult) {

            // 3.创建ResultEntity对象
            ResultEntity<Object> resultEntity = ResultEntity.failed(exception.getMessage());

            // 4.创建Gson对象
            Gson gson = new Gson();

            // 5.将ResultEntity对象转换为JSON字符串
            String json = gson.toJson(resultEntity);

            // 6.将JSON字符串作为响应体返回给浏览器
            response.getWriter().write(json);

            // 7.由于上面已经通过原生的response对象返回了响应，所以不提供ModelAndView对象
            return null;
        }

        // 8.如果不是Ajax请求则创建ModelAndView对象
        ModelAndView modelAndView = new ModelAndView();

        // 9.将Exception对象存入模型
        modelAndView.addObject("exception", exception);

        // 10.设置对应的视图名称
        modelAndView.setViewName(viewName);

        // 11.返回modelAndView对象
        return modelAndView;
    }

}
