package com.fastcampus.ch3.di1;

import ch.qos.logback.core.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectionTest {
    public static void main(String[] args) throws Exception {
        Car car = new Car();
        Class carClass = car.getClass(); //1 . 객체로부터 class객체 얻기
        carClass = Car.class; // 2. 객체 리털러로부터 class객체얻기
        carClass = Class.forName("com.fastcampus.ch3.di1.Car");

        // 1. 설계도 객체로부터 객체 생성하기
        Car car2 = (Car) carClass.newInstance();
        System.out.println(car2);

        // 2.클래스에 선언된 멤버변수(field)와 method 목록 얻기
        Field[] mvArr = carClass.getDeclaredFields();
        Method[] methodArr =  carClass.getDeclaredMethods();
        for(Field mv : mvArr){
            System.out.println("mv.getName() = " + mv.getName());
        }        
        for(Method method : methodArr){
            System.out.println("method.getName() = " + method.getName());
        }
        Method method = carClass.getMethod("setEngine", Engine.class);
        method.invoke(car, new Engine()); // car.setEngine(new Engine()); 와 같은뜻.
        System.out.println("car = " + car);

        // 3. 멤버변수에 set붙여서 setter를 호출하기
        for(Field field : mvArr){
            System.out.println("field = " + field);
            String methodName = "set" + StringUtils.capitalize(field.getName()); // "set" + "Engine" = "setEngine"
            System.out.println("methodName = " + methodName);
            method = carClass.getMethod(methodName, field.getType()); // = carClass.getMethod("setEngine", Engine.Class);
            method.invoke(car,field.getType().newInstance()); // = car.setEngine(new Engine()), car.setDoor(new Door())
        }
        System.out.println("car = " + car);

        // 4. 멤버변수에 @Autowired붙었는지 확인하기
        for(Field field : mvArr){
            Annotation[] annoArr = field.getDeclaredAnnotations();
            for (Annotation anno : annoArr) {
                System.out.println("field = " + field);
                System.out.println("anno.annotationType().getSimpleName() = " + anno.annotationType().getSimpleName());
                System.out.println(anno.annotationType() == Autowired.class);
            }
        }

        // 5. @Autowired 붙은 멤버변수에 setter호출하기
        car = new Car();
        for(Field field : mvArr){
            Annotation[] annoArr = field.getDeclaredAnnotations();
            for (Annotation anno : annoArr) {
                if(anno.annotationType() == Autowired.class) {
                    String methodName = "set" + StringUtils.capitalize(field.getName()); // "set" + "Engine" = "setEngine"
                    method = carClass.getMethod(methodName, field.getType());
                    method.invoke(car,field.getType().newInstance()); // car.setEngine(new Engine()); 와 같은뜻.
                }
            }
        }
        System.out.println("car = " + car);
    }
}
