package com.fastcampus.ch3.di2;

import org.springframework.context.annotation.Bean;

// Bean이란? 저장소가 관리하는 객체
public class AppConfig {
    @Bean public Car car() { // 메서도 이름이 Bean의 이름
        // xml에서는 <bean id="car" class="com.fastcampus.ch3.Car">
        // 아래는 해당 코드와 같다고 보면됨. map.put("car", new Car());
        Car car = new Car();
        return car;
    }
    @Bean public Engine engine() {return new Engine();}
    @Bean public Door door() {return new Door();}

}
