package com.fastcampus.ch3.di1;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

class Car { }
class SportsCar extends Car {}
class Truck extends Car{}
class Engine {}
class Door{}

public class Main {
    public static void main(String[] args) throws Exception {
        Car car = (Car) getObject("car");
        System.out.println("car = " + car);
    }
    // 이렇게 하드코딩 하는거보다... 아래처럼..
//    static Object getObject(String key) {
//        if(key.equals("sports")) {
//            return new SportsCar();
//        } else if(key.equals("truck")) {
//            return new Truck();
//        } else if(key.equals("engine")) {
//            return new Engine();
//        } else if(key.equals("door")) {
//            return new Door();
//        } else return new Car();
//    }
    static Object getObject(String key) throws Exception{
        Properties prop = new Properties();
        Class clazz = null; // 지정된 클래스 이름에 해당하는 클래스 객체를 얻는다.
        prop.load(new FileReader("config.txt"));
        String className = prop.getProperty(key);// 지정한 key의 value를 반환
        clazz = Class.forName(className);
        return clazz.newInstance(); // new Car() 이런 구문과 동일
    }
    static Car getCar() {
        // 아래 내용이 변경된다하더라도 상단에서 사용하는 코드는 수정하지 않아도 된다.
        return new SportsCar();
    }
}
