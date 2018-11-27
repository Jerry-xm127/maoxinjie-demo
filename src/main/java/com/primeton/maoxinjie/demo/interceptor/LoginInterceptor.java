package com.primeton.maoxinjie.demo.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.primeton.maoxinjie.demo.exception.BusyException;
import com.primeton.maoxinjie.demo.exception.ResultCodeEnum;

/**
 * 
 * <p>Title: LoginInterceptor</p>
 * <p>Description: 登录的拦截器</p>  
 * @author Jerry
 * @date 2018年11月22日 
 *
 */
@Component
public class LoginInterceptor implements HandlerInterceptor{

	private static final Logger log = LoggerFactory.getLogger(LoginInterceptor.class);
	
	/**
	 * 开始进入地址请求拦截
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		if(request.getSession().getAttribute("user") == null){
			log.info("拦截成功，请求路径：{}", request.getRequestURI());
			throw new BusyException(ResultCodeEnum.NOT_LOGIN.getCode(),ResultCodeEnum.NOT_LOGIN.getMessage());
		}else{
			return true;
		}
	}

	/**
	 * 处理请求完成后视图渲染之前的处理操作
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
	}

	/**
	 * 视图渲染之后的操作
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		 
	}
}
