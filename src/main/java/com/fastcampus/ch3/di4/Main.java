package com.fastcampus.ch3.di4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigurationExcludeFilter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.TypeExcludeFilter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.stereotype.Component;

import java.util.Arrays;

//@SpringBootApplication // 아래 3개 에너테이션을 붙힌것과 동일

@Component
@Conditional(TrueCondition.class)
class Engine {
    @Override
    public String toString() {
        return "Engine{}";
    }
}

@Component
@Conditional(FalseCondition.class)
class Door {
    @Override
    public String toString() {
        return "Door{}";
    }
}
class TrueCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        return true;
    }
}
class FalseCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        return false;
    }
}
@SpringBootConfiguration // @Configuration 하고 동일
//@EnableAutoConfiguration
@ComponentScan // @Component 클래스는 자동검색하여 AC에 빈으로 등록
public class Main {

    public static void main(String[] args) {
        ApplicationContext ac = SpringApplication.run(Main.class, args);
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        Arrays.sort(beanDefinitionNames);
        Arrays.stream(beanDefinitionNames)
                .filter(name -> !name.startsWith("org"))
                .forEach(System.out::println);
    }
    @Bean
    MyBean myBean() {return new MyBean();}
}

class MyBean {

}
