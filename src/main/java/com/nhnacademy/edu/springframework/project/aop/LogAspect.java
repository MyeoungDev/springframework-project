package com.nhnacademy.edu.springframework.project.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.ArrayList;
import java.util.List;

@Aspect
@Component
public class LogAspect {

    @Pointcut("execution(* com.nhnacademy.edu.springframework.project.service.* .*(..))")
    public void serviceLayer() {

    }

    @Around("serviceLayer()")
    public Object pringLog(ProceedingJoinPoint joinPoint) throws Throwable {

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        Object proceed = joinPoint.proceed();

        stopWatch.stop();

        Signature signature = joinPoint.getSignature();
        System.out.println("===========================================");
        System.out.println("[" + joinPoint.getTarget().getClass().getSimpleName() + "]."
                + "[" + signature.getName() + "] "
                + "[" + stopWatch.getLastTaskTimeMillis() +"]ms");
        System.out.println("===========================================");

        return proceed;
    }
}
