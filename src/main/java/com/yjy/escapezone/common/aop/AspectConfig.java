package com.yjy.escapezone.common.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class AspectConfig {
    @Before("execution(* com.yjy.escapezone.common.exception.handler.GlobalExceptionHandler.*(*))")
    public void before(JoinPoint joinPoint) {
        Exception exception = (Exception) joinPoint.getArgs()[0];
        log.error("exception occurred class: {} \n {}", exception.getClass().getSimpleName(), exception.getMessage());
    }
}
