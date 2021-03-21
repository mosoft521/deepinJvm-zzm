package org.fenixsoft.jvm.chapter12;

//代码清单12-7 final与可见性
public class FinalTest {
    public static final int i;
    public final int j;

    static {
        i = 0;
        //省略后续动作
    }

    {
        //也可以选择在构造函数中初始化
        j = 0;
        //省略后续动作
    }
}