package com.fastcampus.ch3.di3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.Arrays;

class Car {
    @Autowired
    Engine[] engine;

    @Autowired
    Door door;

    @Autowired
    public Car(Door door, Engine[] engine) {
        this.door = door;
        this.engine = engine;
    }

    public Car() {
    }

    @Override
    public String toString() {
        return "Car{" +
                "door=" + door +
                ", engine=" + Arrays.toString(engine) +
                '}';
    }
}
@Component
class Engine {}

@Component
class SuperEngine extends Engine {}

@Component
class TurboEngine extends Engine {}

@Component
class Door {}
public class Main {
    public static void main(String[] args) {
        //AC를 생성 - AC설정파일은 AppConfig.class 자바설정
        ApplicationContext ac = new AnnotationConfigApplicationContext(com.fastcampus.ch3.di3.AppConfig.class);
        // Car car = (Car) ac.getBean("car"); // byName 객체(빈) 조회
        Car car = ac.getBean("car", Car.class);
        System.out.println("car = " + car);
    }
}
