package com.fastcampus.ch3.di2;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;

class Car {
    @Autowired Engine engine; // byType으로 자동검색해서 주입
    // @Resource(name="door") 생략된거임.
    @Resource Door door;

    @Override
    public String toString() {
        return "Car{" +
                "engine=" + engine +
                ", door=" + door +
                '}';
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public void setDoor(Door door) {
        this.door = door;
    }
}
class SportCar extends Car {
    @Override
    public String toString() {
        return "SportCar{" +
                "door=" + door +
                ", engine=" + engine +
                '}';
    }
}
class Engine{}
class Door{}
public class Main {
    public static void main(String[] args) {
        //AppConfig.class에 사용할 class를 보관한다.
        AppContext ac = new AppContext(AppConfig.class);
        Car car = (Car)ac.getBean("car"); // ByName으로 찾기
        Car car2 = (Car)ac.getBean(Car.class); // ByType으로 찾기
        Engine engine = (Engine)ac.getBean("engine");
        Door door  = (Door)ac.getBean(Door.class);

//        car.setEngine(engine); // car.engine = engine;
//        car.setDoor(door); // car.engine = door;

        System.out.println("car = " + car);
        System.out.println("car2 = " + car2);
        System.out.println("engine = " + engine);
        System.out.println("door = " + door);
    }
}
