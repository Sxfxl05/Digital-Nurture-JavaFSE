package com.library.aspect;

import org.aspectj.lang.ProceedingJoinPoint;

public class LoggingAspect {

    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        
        System.out.println("[AOP-BEFORE] Starting execution of method: " + joinPoint.getSignature().getName());
        
        // Let the actual business logic method execute
        Object proceed = joinPoint.proceed();
        
        long executionTime = System.currentTimeMillis() - startTime;
        System.out.println("[AOP-AFTER] Completed method: " + joinPoint.getSignature().getName() + " | Execution Time: " + executionTime + "ms");
        
        return proceed;
    }
}