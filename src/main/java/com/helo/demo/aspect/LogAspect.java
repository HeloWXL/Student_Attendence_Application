package com.helo.demo.aspect;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @Classname LogAspect
 * @Description TODO
 * @Date 2019/12/17 8:45 下午
 * @Created by wangxianlin
 */
@Aspect
@Component
//lombok日志
@Slf4j
public class LogAspect {

    //execution表达式自行搜索引擎
    @Pointcut("execution(* com.helo.demo.service.*.*(..))")
    public void pointcut() {
    }

    @Before("pointcut()")
    public void printParam(JoinPoint joinPoint) {
        //获取请求的方法
        Signature sig = joinPoint.getSignature();
        String method = joinPoint.getTarget().getClass().getName() + "." + sig.getName();
        //获取请求的参数
        Object[] args = joinPoint.getArgs();
        //fastjson转换
        String params = JSONObject.toJSONString(args);
        //打印请求参数
        log.info(method + ":" + params);
    }


    @AfterReturning(pointcut = "pointcut()", returning = "ret")
    public void printAfterParam(JoinPoint joinPoint, Object ret) {
        //获取请求的方法
        Signature sig = joinPoint.getSignature();
        String method = joinPoint.getTarget().getClass().getName() + "." + sig.getName();
        //获取请求的参数
        Object[] args = joinPoint.getArgs();
        //fastjson转换
        String params = JSONObject.toJSONString(args);

        //打印请求参数
        log.info(method + ":" + params);
        log.info("-->" + ret);
    }
}
