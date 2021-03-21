package org.fenixsoft.jvm.chapter13;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Atomic变量自增运算测试
 *
 * @author zzm
 */
public class AtomicTest {

    private static final int THREADS_COUNT = 20;
    public static AtomicInteger race = new AtomicInteger(0);

    public static void increase() {
        race.incrementAndGet();
    }

    public static void main(String[] args) throws Exception {
        Thread[] threads = new Thread[THREADS_COUNT];
        for (int i = 0; i < THREADS_COUNT; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 10000; i++) {
                        increase();
                    }
                }
            });
            threads[i].start();
        }

//        while(Thread.activeCount() > 1) //Intellij IDEA除了main方法的主线程外还有，还多了一个预期外的 Monitor Ctrl-Break线程
        while(Thread.activeCount() > 2)
            Thread.yield();//阻塞？

        System.out.println(race);
    }
}
/* Output:
200000
 */
