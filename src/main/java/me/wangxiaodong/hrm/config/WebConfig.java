package me.wangxiaodong.hrm.config;

import me.wangxiaodong.hrm.filter.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor interceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 添加拦截器，配置拦截地址
        registry.addInterceptor(interceptor).addPathPatterns("/**")
                                            .excludePathPatterns("/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**")
                                            .excludePathPatterns("/api/login")
                                            .excludePathPatterns("/api/login/**");
    }
}
