package com.example.restservice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;

@Aspect
@Component
public class InterceptRestAnnotationAspect {

//    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
//    public void restControllerExecution() {}
//
//
//    @Before("restControllerExecution()")
//    public void setMetodoHttpHeader(JoinPoint joinPoint) throws Throwable {
//
//        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
//                .getResponse();
//
//        String origem = VerificadorOrigem.processarOrigem(joinPoint);
//
//        response.setHeader("nomeMetodo", origem);
//
//    }

}
