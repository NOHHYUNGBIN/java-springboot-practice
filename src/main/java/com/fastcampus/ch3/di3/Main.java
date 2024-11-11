package com.fastcampus.ch3.di3;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

class Car {
    Engine engine;
    Door door;

    @Override
    public String toString() {
        return "Car{" +
                "door=" + door +
                ", engine=" + engine +
                '}';
    }
}
class Engine {}
class Door {}
public class Main {
    public static void main(String[] args) {
        //AC를 생성 - AC설정파일은 AppConfig.class 자바설정
        ApplicationContext ac = new AnnotationConfigApplicationContext(com.fastcampus.ch3.di3.AppConfig.class);
        // Car car = (Car) ac.getBean("car"); // byName 객체(빈) 조회
        Car car = ac.getBean("car", Car.class);

        Engine engine = ac.getBean(Engine.class);
        Engine engine2 = ac.getBean(Engine.class);
        Engine engine3 = ac.getBean(Engine.class);
        System.out.println("car = " + car);
        System.out.println("engine = " + engine);
        System.out.println("engine2 = " + engine2);
        System.out.println("engine3 = " + engine3);

        //빈이 몇개 들어있는지.
        System.out.println("ac.getBeanDefinitionCount() = " + ac.getBeanDefinitionCount());
        //빈의 이름들 배열로받음.
        System.out.println("ac.getBeanDefinitionNames() = " + Arrays.toString(ac.getBeanDefinitionNames()));
        //엔진이란 빈이있는지 확인
        System.out.println("ac.containsBeanDefinition(\"engine\") = " + ac.containsBeanDefinition("engine"));
        //car의 객체가 싱글톤인지 확인
        System.out.println("ac.isSingleton(\"car\") = " + ac.isSingleton("car"));
        //engine의 객체가 프로토타입인지 확인
        System.out.println("ac.isPrototype('engine') = " + ac.isPrototype("engine"));

    }
}
