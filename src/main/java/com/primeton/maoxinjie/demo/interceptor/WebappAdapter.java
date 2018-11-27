package com.primeton.maoxinjie.demo.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 
 * <p>Title: WebappAdapter</p>
 * <p>Description: 拦截器配置</p>  
 * @author Jerry
 * @date 2018年11月27日 
 *
 */
@Configuration
public class WebappAdapter implements WebMvcConfigurer{
	
	@Autowired
	private LoginInterceptor loginInterceptor;

	/**
	 * 注册拦截器
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
        // addPathPatterns 添加拦截url，     excludePathPatterns 排除拦截url
		registry.addInterceptor(loginInterceptor)
				.addPathPatterns("/api/**")
				.excludePathPatterns("/api/users/login");
		WebMvcConfigurer.super.addInterceptors(registry);
	}
}
