package com.library.aspect;

import org.aspectj.lang.ProceedingJoinPoint;

public class LoggingAspect {

    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        
        System.out.println("[AOP-BEFORE] Starting execution of method: " + joinPoint.getSignature().getName());
        
        // This executes the actual method (e.g., processBookRecord)
        Object proceed = joinPoint.proceed();
        
        long executionTime = System.currentTimeMillis() - start;
        System.out.println("[AOP-AFTER] Completed method: " + joinPoint.getSignature().getName() + " | Execution Time: " + executionTime + "ms");
        
        return proceed;
    }
}