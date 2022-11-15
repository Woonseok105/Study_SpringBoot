package com.example.study_springboot.global.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")   //addMapping은 작성한 controller의 mapping의 패턴을 설정해준다.
                .allowedOrigins("*")            //allowedOrigins은 요청을 허용하는 클라이언트의 주소이다.
                .allowedMethods("*")
                .allowedHeaders("*");
    }

}
