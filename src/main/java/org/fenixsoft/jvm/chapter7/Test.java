package org.fenixsoft.jvm.chapter7;

public class Test {
    static {
        i = 0;//ok
//        System.out.println(i);//这句编译报错：“非法向前引用”
    }

    static int i = 1;
}
