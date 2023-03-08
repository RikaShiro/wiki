package com.wxz.wiki.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import jakarta.annotation.Resource;
import com.wxz.wiki.interceptor.LogInterceptor;;

@Configuration
public class SpringMvcConfig implements WebMvcConfigurer {
  @Resource
  LogInterceptor logInterceptor;

  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(logInterceptor)
        .addPathPatterns("/**").excludePathPatterns("/login");
  }
}
