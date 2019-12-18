package edu.xidian.pixels.aop;

import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsUtils;

/**
 * CustomerInterceptorAOP
 * 用来解决认证拦截器中将预检请求拒绝从而导致跨域设置失败的问题
 * 顺便用来练习AOP编程
 */
@Component
@Aspect
@Slf4j
public class CustomerInterceptorAOP {

    /**
     * execution()内部为表达式的主体
     * public 表示需要匹配方法的修饰符
     * 第一个 * 表示方法的返回值，可以指定
     * edu.xidian.pixels.Interceptor AOP所切服务的包名
     * 在包名后加 .. 表示当前包及子包
     * 第二个 * 表示类名
     * 后面就是方法的名称以及方法的参数，.. 表示任意参数
     */
    @Pointcut(value = "execution(public * edu.xidian.pixels.Interceptor.*.preHandle(..))")
    public void pointCutAllowPreFlightRequest() {
    }
    
    @Around(value = "pointCutAllowPreFlightRequest()")
    public Object processTx(ProceedingJoinPoint jp) throws Throwable {
        HttpServletRequest request = (HttpServletRequest) jp.getArgs()[0];
        if (CorsUtils.isPreFlightRequest(request)) {
            return true;
        } else {
            return jp.proceed();
        }
    }
}