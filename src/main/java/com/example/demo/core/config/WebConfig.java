package com.example.demo.core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/css/**", "/js/**", "/vue/**", "/images/**", "/html/**", "/fonts/**", "/font/**", "/img/**")
				.addResourceLocations("classpath:/static/css/", "classpath:/static/js/", "classpath:/static/vue/",
						"classpath:/static/images/", "classpath:/templates/html/", "classpath:/templates/fonts/",
						"classpath:/templates/excel/", "classpath:/static/vue/", "classpath:/static/vue/css/",
						"classpath:/static/vue/js/", "classpath:/static/vue/img/", "classpath:/static/font/")
				.setCachePeriod(20);
	}
// @Override
//  public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
//    PageableHandlerMethodArgumentResolver r = new PageableHandlerMethodArgumentResolver();
//	@Override
//	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
//		PageableHandlerMethodArgumentResolver pageableArgumentResolver = new PageableHandlerMethodArgumentResolver();
//		pageableArgumentResolver.setOneIndexedParameters(true);
//		argumentResolvers.add(pageableArgumentResolver);
//	}
	

}
