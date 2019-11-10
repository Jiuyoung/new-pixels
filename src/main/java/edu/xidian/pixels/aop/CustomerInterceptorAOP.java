package edu.xidian.pixels.aop;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsUtils;

/**
 * CustomerInterceptorAOP
 */
@Component
@Aspect
public class CustomerInterceptorAOP {
    @Pointcut("execution(public * edu.xidian.pixels.Interceptor.*.preHandle(..))")
    public void poinCut() {
    }
    
    @Around(value = "poinCut()") 
    public Object processTx(ProceedingJoinPoint jp) throws Throwable {
        HttpServletRequest request = (HttpServletRequest) jp.getArgs()[0];
        if (CorsUtils.isPreFlightRequest(request)) {
            return true;
        } else {
            return jp.proceed();
        }
    }
}