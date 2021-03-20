package org.fenixsoft.jvm.chapter8;

import java.io.Serializable;

/**
 * @author zzm
 */
public class Overload {
    //6
    public static void sayHello(Object arg) {
        System.out.println("hello Object");
    }
    //2
    public static void sayHello(int arg) {
        System.out.println("hello int");
    }
    //3
    public static void sayHello(long arg) {
        System.out.println("hello long");
    }
    //4
    public static void sayHello(Character arg) {
        System.out.println("hello Character");
    }
    //1
    public static void sayHello(char arg) {
        System.out.println("hello char");
    }
    //7
    public static void sayHello(char... arg) {
        System.out.println("hello char ...");
    }
    //5
    public static void sayHello(Serializable arg) {
        System.out.println("hello Serializable");
    }

    public static void main(String[] args) {
        sayHello('a');
    }
}
