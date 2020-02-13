package edu.xidian.pixels.Config;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
// import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import edu.xidian.pixels.Interceptor.AuthenticationInterceptor;
import edu.xidian.pixels.util.CurrentUserMethodArgumentResolver;

/**
 * WebMvcConfig
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private final AuthenticationInterceptor interceptor;
    private final CurrentUserMethodArgumentResolver resolver;

    public WebMvcConfig(AuthenticationInterceptor interceptor, CurrentUserMethodArgumentResolver resolver) {
        this.interceptor = interceptor;
        this.resolver = resolver;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptor).addPathPatterns("/**");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(resolver);
        WebMvcConfigurer.super.addArgumentResolvers(resolvers);
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
        converters.removeIf(converter -> converter instanceof MappingJackson2HttpMessageConverter);
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue,
                SerializerFeature.QuoteFieldNames);
        List<MediaType> fastMediaTypes = new ArrayList<>();
        fastMediaTypes.add(MediaType.APPLICATION_JSON);
        fastConverter.setSupportedMediaTypes(fastMediaTypes);
        fastConverter.setFastJsonConfig(fastJsonConfig);
        converters.add(fastConverter);
    }
    // 由于权限拦截器处理在跨域处理之前，导致跨域配置失效

//     @Override
//     public void addCorsMappings(CorsRegistry registry) {
//         registry.addMapping("/**").allowedOrigins("*");
//     }


    // 由于过滤器在拦截器之前，所以设置过滤器来解决跨域
    @Bean
    public CorsFilter corsFilter() {
       CorsConfiguration config = new CorsConfiguration();
       config.addAllowedOrigin("*");
       config.addAllowedMethod("*");
       config.addAllowedHeader("*");
       UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
       configSource.registerCorsConfiguration("/**", config);
       return new CorsFilter(configSource);
    }
}