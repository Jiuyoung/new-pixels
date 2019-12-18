package edu.xidian.pixels.util;

import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import edu.xidian.pixels.Annotation.CurrentUser;
import edu.xidian.pixels.Entity.User;

/**
 * CurrentUserMethodArgumentResolver
 * 配合@CurrentUser注解，注入当前登录的用户
 */
@Component
public class CurrentUserMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().isAssignableFrom(User.class)
                && parameter.hasParameterAnnotation(CurrentUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        // setAttribute()的过程在认证拦截器中
        User user = (User) webRequest.getAttribute("CurrentUser", RequestAttributes.SCOPE_REQUEST);
        if (user != null) {
            return user;
        }
        throw new MissingServletRequestPartException("CurrentUser miss");
    }
}