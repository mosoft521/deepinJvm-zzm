package org.fenixsoft.clazz;

import org.openjdk.jol.info.ClassLayout;
/**
 * VM Args1：none
 * VM Args2：-XX:-UseCompressedOops 关闭指针压缩
 * VM Args3：-XX:+UseCompressedOops 开启指针压缩【默认】
 */
public class Main{
    public static void main(String[] args) throws InterruptedException {
        //new 一个对象
        L l = new L();
        //输出 l对象 的布局
        System.out.println(ClassLayout.parseInstance(l).toPrintable());
    }
}
//对象类
class L{
    private boolean myboolean = true;
}
/*
>>VM Args1：none == VM Args3：-XX:+UseCompressedOops
org.fenixsoft.clazz.L object internals:
 OFFSET  SIZE      TYPE DESCRIPTION                               VALUE
      0     4           (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
      4     4           (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
      8     4           (object header)                           43 c1 00 f8 (01000011 11000001 00000000 11111000) (-134168253)
     12     1   boolean L.myboolean                               true
     13     3           (loss due to the next object alignment)
Instance size: 16 bytes
Space losses: 0 bytes internal + 3 bytes external = 3 bytes total
>>VM Args2：-XX:-UseCompressedOops
org.fenixsoft.clazz.L object internals:
 OFFSET  SIZE      TYPE DESCRIPTION                               VALUE
      0     4           (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
      4     4           (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
      8     4           (object header)                           48 35 71 7b (01001000 00110101 01110001 01111011) (2071016776)
     12     4           (object header)                           8e 02 00 00 (10001110 00000010 00000000 00000000) (654)
     16     1   boolean L.myboolean                               true
     17     7           (loss due to the next object alignment)
Instance size: 24 bytes
Space losses: 0 bytes internal + 7 bytes external = 7 bytes total
VM Args3：-XX:+UseCompressedOops
 */