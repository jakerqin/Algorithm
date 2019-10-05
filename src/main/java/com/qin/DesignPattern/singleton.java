package com.qin.DesignPattern;

/**
 * 单例模式：饿汉式、静态常量

public class singleton {
    private static final singleton singleton = new singleton();
    //构造器私有化 (防止 new )
    private singleton() {

    }
    public static singleton getInstance() {
        return singleton;
    }
} */


/**
 * 单例模式：饿汉式（静态代码块）

public class singleton {
    private static singleton singleton ;
    static {
        singleton = new singleton();
    }
    //构造器私有化 (防止 new )
    private singleton() {

    }
    public static singleton getInstance() {
        return singleton;
    }
} */

/**
 * 单例模式：懒汉式(线程不安全)

public class singleton {
    private static singleton singleton ;

    //构造器私有化 (防止 new )
    private singleton() {

    }
    public static singleton getInstance() {
        if (singleton == null) {
            singleton = new singleton();
        }
        return singleton;
    }
} */


/**
 * 单例模式：懒汉式(线程安全:同步方法)

public class singleton {
    private static singleton singleton ;

    //构造器私有化 (防止 new )
    private singleton() {

    }
    public static synchronized singleton getInstance() {
        if (singleton == null) {
            singleton = new singleton();
        }
        return singleton;
    }
} */


/**
 * 单例模式：懒汉式(线程不安全，同步代码块)

public class singleton {
    private static singleton singleton ;

    //构造器私有化 (防止 new )
    private singleton() {

    }
    public static singleton getInstance() {
        if (singleton == null) {
            synchronized (com.qin.DesignPattern.singleton.class){
                singleton = new singleton();
            }
        }
        return singleton;
    }
} */


/**
 * 单例模式：双重检查

public class singleton {
    private static volatile singleton singleton1 ;

    //构造器私有化 (防止 new )
    private singleton() {

    }
    public static singleton getInstance() {
        if (singleton1 == null) {
            synchronized (singleton.class){
                if (singleton1 == null) {
                    singleton1 = new singleton();
                }
            }
        }
        return singleton1;
    }
} */


/**
 * 单例模式：静态内部类
 */
public class singleton {

    private singleton(){}

    private static class SingletonInstance{
        private static final singleton sin = new singleton();
    }

    public singleton getInstance() {

        return SingletonInstance.sin;
    }
}
