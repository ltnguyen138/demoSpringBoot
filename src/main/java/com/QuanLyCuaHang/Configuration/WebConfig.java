package com.QuanLyCuaHang.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.QuanLyCuaHang.Interceptor.UserInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Autowired
	UserInterceptor userinterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(userinterceptor).addPathPatterns("/**").excludePathPatterns("/login","/style/style.css");
	}

}
