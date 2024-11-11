package com.fastcampus.ch3.di2;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class AppContext {
    Map map = new HashMap();
    AppContext() {
        map.put("car", new SportCar());
        map.put("engine", new Engine());
        map.put("door", new Door());
    }
    AppContext(Class clazz) {
        Object config = null;
        try {
            config = clazz.newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println("method = " + method.getName());
            for(Annotation anno : method.getDeclaredAnnotations()) {
                if(anno.annotationType() == Bean.class) {
                    try {
                        // map.put("car", config.car()); => map.put("car", new Car());
                        map.put(method.getName(), method.invoke(config, null));
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    } catch (InvocationTargetException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        doAutowired(); // @Autowired를 찾아서 빈(객체)간의 자동연결처리 byType
        doResource(); // @Resource를 찾아서 빈(객체)간의 자동연결처리  byName
    }

    private void doResource() {
        for(Object bean : map.values()) {
            System.out.println("bean = " + bean);
            for(Field field : bean.getClass().getDeclaredFields()) {
                if(field.getAnnotation(Resource.class) != null) {
                    try {
                        field.set(bean, getBean(field.getName())); // car.engine = obj;
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

    private void doAutowired() {
        for(Object bean : map.values()) {
            for(Field field : bean.getClass().getDeclaredFields()) {
                if(field.getAnnotation(Autowired.class) != null) {
                    try {
                        field.set(bean, getBean(field.getType())); // car.engine = obj;
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

    public Object getBean(Class clazz) {
        for(Object obj : map.values()) {
            if(clazz.isInstance(obj)) return obj;
        }
        return null;
    }

    public Object getBean(String id) {
        return map.get(id);
    }
}
