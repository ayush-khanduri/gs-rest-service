package com.example.restservice;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.Arrays;
import java.util.List;

@Aspect
@Component
public class LoggingAspect
{
    private static final Logger LOGGER = LogManager.getLogger(LoggingAspect.class);

    //AOP expression for which methods shall be intercepted
    @Around("execution(* com.example.restservice..*(..)))")
    public Object profileAllMethods(ProceedingJoinPoint proceedingJoinPoint) throws Throwable
    {
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();

        //Get intercepted method details
        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();

        final StopWatch stopWatch = new StopWatch();

        //Measure method execution time
        stopWatch.start();
        Object result = proceedingJoinPoint.proceed();
        stopWatch.stop();

        //Log method execution time
        LOGGER.info("Execution time of " + className + "." + methodName + " :: " + stopWatch.getTotalTimeMillis() + " ms");

        return result;
    }

    @Around("execution(* com.example.restservice..*(..)))")
    public Object loggingAroundMethod(ProceedingJoinPoint jp) throws Throwable {
        final String signature = jp.getTarget().getClass().getName() + '.' + jp.getSignature().getName();
        final List<Object> arguments = Arrays.asList(jp.getArgs());
        final Object result;
        try {
            LOGGER.info("[BEFORE]"+ signature+" "+arguments);
            result = jp.proceed();
            LOGGER.info( "[AFTER] {}{} result={}"+signature+" "+arguments+" "+ result);
        } catch (Exception e) {
            LOGGER.error("[AFTER] {}{} exception={}"+signature+" "+ arguments+" "+ e);
            throw e;
        }

        return result;
    }

//    @AfterThrowing(pointcut = "springBeanPointcut()", throwing = "e")
//    public void afterThrowingAdvice(JoinPoint jp, Exception e) {
//       LOGGER.info("It broke" + e.toString());
//    }

}