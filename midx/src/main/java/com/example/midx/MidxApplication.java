package com.example.midx;

import com.example.midx.interceptor.ApiIdempotentInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class MidxApplication extends WebMvcConfigurerAdapter {

    public static void main(String[] args) {
        SpringApplication.run(MidxApplication.class, args);
    }

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        // 接口幂等性拦截器
//        registry.addInterceptor(apiIdempotentInterceptor());
//
//        super.addInterceptors(registry);
//    }
//
//    @Bean
//    public ApiIdempotentInterceptor apiIdempotentInterceptor() {
//        return new ApiIdempotentInterceptor();
//    }
}
