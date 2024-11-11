package com.fastcampus.ch3.di3;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class AppConfig {
    @Bean
    Car car() {
        return new Car();
    }
//    @Scope("singleton") 매번 같은 객체
//    @Scope("prototype") 매번 새로운 객체 싱글톤이 기본.
    @Bean
    Engine engine() {
        return new Engine();
    }
    @Bean
    Door door() {
        return new Door();
    }
}
