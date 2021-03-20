package org.fenixsoft.jvm.chapter9;

import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Method;

/**
 * Javaclass执行工具
 *
 * @author zzm
 */
public class JavaclassExecuter {

    /**
     * 执行外部传过来的代表一个Java类的Byte数组<br>
     * 将输入类的byte数组中代表java.lang.System的CONSTANT_Utf8_info常量修改为劫持后的HackSystem类
     * 执行方法为该类的static main(String[] args)方法，输出结果为该类向System.out/err输出的信息
     *
     * @param classByte 代表一个Java类的Byte数组
     * @return 执行结果
     */
    public static String execute(byte[] classByte) {
        HackSystem.clearBuffer();
        ClassModifier cm = new ClassModifier(classByte);
        byte[] modiBytes = cm.modifyUTF8Constant("java/lang/System", "org/fenixsoft/jvm/chapter9/HackSystem");
        HotSwapClassLoader loader = new HotSwapClassLoader();
        Class clazz = loader.loadByte(modiBytes);
        try {
            Method method = clazz.getMethod("main", new Class[]{String[].class});
            method.invoke(null, new String[]{null});
        } catch (Throwable e) {
            e.printStackTrace(HackSystem.out);
        }
        return HackSystem.getBufferString();
    }

    public static void main(String[] args) throws Exception {
//        String name = "org.fenixsoft.jvm.chapter9.DynamicProxyTest";//有内部类，不是一个Class了，实在要弄，再掏一层Jar包吧。
        String name = "org.fenixsoft.jvm.chapter9.HelloWorld";
        String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
//        InputStream is = new FileInputStream(fileName);
        InputStream is = JavaclassExecuter.class.getResourceAsStream(fileName);
        byte[] b = new byte[is.available()];
        is.read(b);
        is.close();
        System.out.println(JavaclassExecuter.execute(b));
    }
}
