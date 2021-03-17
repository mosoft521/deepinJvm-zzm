package org.fenixsoft.jvm.chapter2;

public class RuntimeConstantPoolOOM_2 {

    public static void main(String[] args) {
        String str1 = new StringBuilder("计算机").append("软件").toString();
        System.out.println(str1.intern() == str1);

        String str2 = new StringBuilder("ja").append("va").toString();
        System.out.println(str2.intern() == str2);//sun.misc.Version：private static final java.lang.String launcher_name = "java"; [Loaded sun.misc.Version from D:\tools\Java\jdk1.8.0_281\jre\lib\rt.jar]
    }
}
/*
true
false
 */