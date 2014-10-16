package com.noveogroup.tulupov.addressbook.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * Contact service logging aspect.
 */
@Component
@Aspect
@Slf4j
public class LoggingAspect {

    @Before("execution(* com.noveogroup.tulupov.addressbook.service.ContactService.count(..))")
    public void logBefore(final JoinPoint joinPoint) {
        log.debug("logBefore() call " + joinPoint.getSignature().getName());
    }

    @After("execution(* com.noveogroup.tulupov.addressbook.service.ContactService.count(..))")
    public void logAfter(final JoinPoint joinPoint) {
        log.debug("logAfter() call " + joinPoint.getSignature().getName());
    }
 
}
