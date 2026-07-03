package com.library.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Around("@annotation(com.library.annotation.TrackExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        
        System.out.println("[AOP-BEFORE] Starting execution of method: " + joinPoint.getSignature().getName());
        
        Object proceed = joinPoint.proceed();
        
        long executionTime = System.currentTimeMillis() - start;
        System.out.println("[AOP-AFTER] Completed method: " + joinPoint.getSignature().getName() + " | Execution Time: " + executionTime + "ms");
        
        return proceed;
    }
}