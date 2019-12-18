package edu.xidian.pixels.Interceptor;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import edu.xidian.pixels.Annotation.PassToken;
import edu.xidian.pixels.Annotation.UserLoginToken;
import edu.xidian.pixels.Entity.User;
import edu.xidian.pixels.Service.TokenService;
import edu.xidian.pixels.Service.UserService;

/**
 * AuthenticationInterceptor
 */
@Component
public class AuthenticationInterceptor implements HandlerInterceptor {

    private final UserService userService;
    private final TokenService tokenService;

    public AuthenticationInterceptor(UserService userService, TokenService tokenService) {
        this.userService = userService;
        this.tokenService = tokenService;
    }

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
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }
        if (method.isAnnotationPresent(UserLoginToken.class)) {
            UserLoginToken userLoginToken = method.getAnnotation(UserLoginToken.class);
            if (userLoginToken.required()) {
                if (token == null) {
                    throw new RuntimeException("无效token，请重新登录!");
                }
                String userAccount;
                try {
                    userAccount = JWT.decode(token).getAudience().get(0);

                } catch (JWTDecodeException e) {
                    throw new RuntimeException("登录失效，请重新登录!");
                }

                if (tokenService.hasToken(userAccount)) {
                    String uuid = tokenService.findUUID(userAccount);
                    JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(uuid)).build();
                    try {
                        jwtVerifier.verify(token);
                    } catch (JWTVerificationException e) {
                        throw new RuntimeException("无效Token，请重新登录!");
                    }
                    User user = userService.findByAccount(userAccount);
                    tokenService.updateToken(userAccount, token);
                    request.setAttribute("CurrentUser", user);
                    return true;
                } else {
                    throw new RuntimeException("登录失效，请重新登录!");
                }
            }
        }
        return true;
    }

}