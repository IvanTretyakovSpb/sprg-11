package com.tretyakov.aspect;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Slf4j
@Aspect
@RequiredArgsConstructor
public class TimerAspect {

    private final TimerProperties properties;

    // Для обработки методов с указанной аннотацией
    @Pointcut("@annotation(com.tretyakov.aspect.Timer)")
    public void methodLoggingPointcut() {
    }

    // Для обработки бинов (классов) с указанной аннотацией
    @Pointcut("within(@com.tretyakov.aspect.Timer *)")
    public void classLoggingPointcut() {
    }

    @Around("methodLoggingPointcut() || classLoggingPointcut()")
    public Object logMethodExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {

        long start = System.currentTimeMillis();
        Object result = null;

        try {
            result = joinPoint.proceed();
        } finally {
            long elapsedTime = System.currentTimeMillis() - start;
            log.atLevel(properties.getLogLevel()).log("Method: {} - {} # {} millis",
                    joinPoint.getTarget().getClass().getName(),
                    joinPoint.getSignature().getName(),
                    elapsedTime);
        }

        return result;
    }
}
