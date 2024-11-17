package com.fastcampus.ch3.aop;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class AopMain {
    public static void main(String[] args) throws Exception {
        Class myClass = Class.forName("com.fastcampus.ch3.aop.MyClass");
        Object obj = myClass.newInstance();

        MyAdvice myAdvice = new MyAdvice();

        for(Method m : myClass.getDeclaredMethods()) {
            myAdvice.invoke(m,obj,null);
        }
    }
}
class MyAdvice {
    void invoke(Method m, Object obj, Object... args) throws Exception{
        System.out.println("[before]{" );
        m.invoke(obj, args); // aaa(), aaa2(), bbb()
        System.out.println("[after]{" );
    }
}

class MyClass {
    void aaa() {
        System.out.println("aaa is called");
    }
    void aaa2() {
        System.out.println("aaa2 is called");
    }
    void bbb() {
        System.out.println("bbb is called");
    }
}