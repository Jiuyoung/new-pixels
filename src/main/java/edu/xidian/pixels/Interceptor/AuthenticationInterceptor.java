package edu.xidian.pixels.Interceptor;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import edu.xidian.pixels.Annotation.PassToken;
import edu.xidian.pixels.Annotation.UserLoginToken;
import edu.xidian.pixels.Entity.User;
import edu.xidian.pixels.Service.UserService;

/**
 * AuthenticationInterceptor
 */
@Component
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String token = request.getHeader("token");
        if(!(handler instanceof HandlerMethod)){
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        if(method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if(passToken.required()) {
                return true;
            }
        }
        if(method.isAnnotationPresent(UserLoginToken.class)) {
            UserLoginToken userLoginToken = method.getAnnotation(UserLoginToken.class);
            if(userLoginToken.required()) {
                if(token == null) {
                    response.setStatus(401);
                    throw new RuntimeException("无效token，请重新登录!");
                }
                String userAccount;
                try {
                    userAccount = JWT.decode(token).getAudience().get(0);

                } catch (JWTDecodeException e) {
                    response.setStatus(401);
                    throw new RuntimeException("401");
                }
                User user = userService.findByAccount(userAccount);
                if(user == null) {
                    response.setStatus(401);
                    throw new RuntimeException("用户不存在， 请重新登录");
                }
                JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
                try {
                    jwtVerifier.verify(token);
                } catch (JWTVerificationException e) {
                    response.setStatus(401);
                    throw new RuntimeException("401");
                }
                request.setAttribute("CurrentUser", user);
                return true;
            }
        }
        return true;
    }

    
}