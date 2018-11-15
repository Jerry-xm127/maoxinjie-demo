package com.primeton.maoxinjie.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
/**
 * swagger2的配置文件,可以配置swagger2的一些基本的内容，比如扫描的包等等
 * @author MAIBENBEN
 *
 */
@Configuration
@EnableSwagger2
public class Swagger2 extends WebMvcConfigurationSupport{
	

    @Bean
    public Docket createRestApi() {
    	
    	//构建API文档的详细信息
        ApiInfo apiInfo = new ApiInfoBuilder()	
                .title("maoxinjie-demo 项目接口文档")  //页面标题
                .description("maoxinjie-demo 项目的接口文档，用swagger2显示restful api。")
                .version("1.0")
                .build();

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo)  //调用apiInfo方法,创建一个ApiInfo实例,里面是展示在文档页面信息内容
                .select()
				//控制暴露出去的路径下的实例
				//如果某个接口不想暴露,可以使用以下注解
				//@ApiIgnore 这样,该接口就不会暴露在 swagger2 的页面下
                .apis(RequestHandlerSelectors.basePackage("com.primeton.maoxinjie.demo.controller")) //以扫描包的方式
                .paths(PathSelectors.any())
                .build();
    }
    
    /**
     * 防止@EnableMvc把默认的静态资源路径覆盖了，手动设置的方式
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    	registry.addResourceHandler("/**").addResourceLocations("classpath:/META-INF/resources/");
    }

}
