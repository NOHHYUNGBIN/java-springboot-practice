package com.fastcampus.ch3.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.util.Arrays;

public class AopMain2 {
    public static void main(String[] args) {
        ApplicationContext ac = SpringApplication.run(Config.class);
        MyMath mm =  ac.getBean("myMath", MyMath.class);
        mm.add(3,5);
        mm.add(3,5,7);
        mm.subtract(3,5);
        mm.multiply(3,5);
    }
}
@EnableAspectJAutoProxy // AOP 자동설정
@Configuration
@ComponentScan // @Component 가 붙은 클래스는 빈에등록
class Config {

}
@Component
@Aspect
class LoggingAdvice {
    // 패턴에 일치하는 항목만..
    @Around("execution(* com.fastcampus.ch3.aop.MyMath.add*(..))")
    public Object methodClassLog(ProceedingJoinPoint pjp) throws Throwable {
        // target(MyMath) 의 메서도 시작 부분에 추가될 코드
        Long startTime = System.currentTimeMillis();
        System.out.println("<<[start]" + pjp.getSignature().getName() + Arrays.toString(pjp.getArgs()));
        Object result = pjp.proceed(); // target(MyMath)의 매소드호출

        // target(MyMath) 의 메서도 끝 부분에 추가될 코드
        System.out.println("result = " + result);
        System.out.println("[end]>>" + (System.currentTimeMillis() - startTime)+"ms");
        return result;
    }
}

@Component
class MyMath {
    int add(int a, int b) {
        int result = a + b;
        return result;
    }
    int add(int a, int b, int c) {
        int result = a + b + c;
        return result;
    }
    int subtract(int a, int b) {
        int result = a - b;
        return result;
    }
    int multiply(int a, int b) {
        int result = a * b;
        return result;
    }
}