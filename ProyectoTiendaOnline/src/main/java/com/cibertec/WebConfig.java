package com.cibertec;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.cibertec.interceptor.UsuarioInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {

//	 @Override
//	 public void addInterceptors(InterceptorRegistry registry) {
//        // Agregar el interceptor para todas las URL
//        registry.addInterceptor(new UsuarioInterceptor()).addPathPatterns("/**");
//    }
}
