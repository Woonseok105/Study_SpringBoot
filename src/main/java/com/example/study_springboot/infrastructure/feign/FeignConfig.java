package com.example.study_springboot.infrastructure.feign;

import feign.codec.ErrorDecoder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@EnableFeignClients(basePackages = "com.example.study_springboot.infrastructure.feign")
//root package에 있어야한다. 그렇지 않은 경우 basePackage 또는 basePackageClasses를 지정해줘야 한다.

@Import(FeignClientErrorDecoder.class)
@Configuration
public class FeignConfig {
//FeignClientErrorDecoder를 @Bean으로 등록해주어 FeignClientException이 발생하였을때 FeignClientExceptionErrorDecoder가 동작하도록 해줍니다.
    @Bean
    @ConditionalOnMissingBean(value = ErrorDecoder.class) //스프링 부트 프로젝트 상에서 동명의 스프링 빈이 정의되었을 시에는 쓰지 않고 그 스프링 빈을 쓰며 만약 없을 시에는 자동 등록한 빈을 쓰게 끔 유도하는 용도로 쓰임.
    public FeignClientErrorDecoder commonFeignErrorDecoder() {
        return new FeignClientErrorDecoder();
    }

}
