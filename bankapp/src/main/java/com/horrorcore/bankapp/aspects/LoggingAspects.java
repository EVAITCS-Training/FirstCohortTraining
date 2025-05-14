package com.horrorcore.bankapp.aspects;

import lombok.extern.slf4j.Slf4j;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspects {

    @Around("@annotation(com.horrorcore.bankapp.annotations.LogExecutionTime)")
    private Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        log.info(joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName() + " Has Started");
        Object proceed = joinPoint.proceed();
        long executionTime = System.currentTimeMillis() - start;
        log.info(joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName() + " Has Finished");
        log.info(joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName() + " executed in " + executionTime + "ms");
        return proceed;
    }

}
