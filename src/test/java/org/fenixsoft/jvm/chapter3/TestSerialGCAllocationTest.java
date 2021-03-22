package org.fenixsoft.jvm.chapter3;

import org.junit.Test;

public class TestSerialGCAllocationTest {
    /*
    [GC (Allocation Failure) [PSYoungGen: 7934K->1008K(9216K)] 7934K->3371K(19456K), 0.0041127 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
    Heap
     PSYoungGen      total 9216K, used 5260K [0x00000000ff600000, 0x0000000100000000, 0x0000000100000000)
      eden space 8192K, 51% used [0x00000000ff600000,0x00000000ffa272d8,0x00000000ffe00000)
      from space 1024K, 98% used [0x00000000ffe00000,0x00000000ffefc020,0x00000000fff00000)
      to   space 1024K, 0% used [0x00000000fff00000,0x00000000fff00000,0x0000000100000000)
     ParOldGen       total 10240K, used 6459K [0x00000000fec00000, 0x00000000ff600000, 0x00000000ff600000)
      object space 10240K, 63% used [0x00000000fec00000,0x00000000ff24ef08,0x00000000ff600000)
     Metaspace       used 4730K, capacity 5130K, committed 5248K, reserved 1056768K
      class space    used 550K, capacity 562K, committed 640K, reserved 1048576K
     */
    @Test
    public void testAllocation() {
        TestSerialGCAllocation.testAllocation();
    }

    /*
    Heap
     PSYoungGen      total 9216K, used 5855K [0x00000000ff600000, 0x0000000100000000, 0x0000000100000000)
      eden space 8192K, 71% used [0x00000000ff600000,0x00000000ffbb7e68,0x00000000ffe00000)
      from space 1024K, 0% used [0x00000000fff00000,0x00000000fff00000,0x0000000100000000)
      to   space 1024K, 0% used [0x00000000ffe00000,0x00000000ffe00000,0x00000000fff00000)
     ParOldGen       total 10240K, used 4096K [0x00000000fec00000, 0x00000000ff600000, 0x00000000ff600000)
      object space 10240K, 40% used [0x00000000fec00000,0x00000000ff000010,0x00000000ff600000)
     Metaspace       used 4732K, capacity 5130K, committed 5248K, reserved 1056768K
      class space    used 550K, capacity 562K, committed 640K, reserved 1048576K
     */
    @Test
    public void testPretenureSizeThreshold() {
        TestSerialGCAllocation.testPretenureSizeThreshold();
    }

    /*
    [GC (Allocation Failure)
    Desired survivor size 1048576 bytes, new threshold 1 (max 1)
    [PSYoungGen: 6138K->1016K(9216K)] 14330K->9683K(19456K), 0.0017409 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
    Heap
     PSYoungGen      total 9216K, used 5275K [0x00000000ff600000, 0x0000000100000000, 0x0000000100000000)
      eden space 8192K, 52% used [0x00000000ff600000,0x00000000ffa28f90,0x00000000ffe00000)
      from space 1024K, 99% used [0x00000000ffe00000,0x00000000ffefe020,0x00000000fff00000)
      to   space 1024K, 0% used [0x00000000fff00000,0x00000000fff00000,0x0000000100000000)
     ParOldGen       total 10240K, used 8667K [0x00000000fec00000, 0x00000000ff600000, 0x00000000ff600000)
      object space 10240K, 84% used [0x00000000fec00000,0x00000000ff476de0,0x00000000ff600000)
     Metaspace       used 4731K, capacity 5130K, committed 5248K, reserved 1056768K
      class space    used 550K, capacity 562K, committed 640K, reserved 1048576K
     */
    @Test
    public void testTenuringThreshold() {
        TestSerialGCAllocation.testTenuringThreshold();
    }

    /*
    [GC (Allocation Failure)
    Desired survivor size 1048576 bytes, new threshold 7 (max 15)
    [PSYoungGen: 6394K->1016K(9216K)] 14586K->9956K(19456K), 0.0018035 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
    Heap
     PSYoungGen      total 9216K, used 5275K [0x00000000ff600000, 0x0000000100000000, 0x0000000100000000)
      eden space 8192K, 52% used [0x00000000ff600000,0x00000000ffa28f90,0x00000000ffe00000)
      from space 1024K, 99% used [0x00000000ffe00000,0x00000000ffefe030,0x00000000fff00000)
      to   space 1024K, 0% used [0x00000000fff00000,0x00000000fff00000,0x0000000100000000)
     ParOldGen       total 10240K, used 8940K [0x00000000fec00000, 0x00000000ff600000, 0x00000000ff600000)
      object space 10240K, 87% used [0x00000000fec00000,0x00000000ff4bb128,0x00000000ff600000)
     Metaspace       used 4732K, capacity 5130K, committed 5248K, reserved 1056768K
      class space    used 550K, capacity 562K, committed 640K, reserved 1048576K
     */
    @Test
    public void testTenuringThreshold2() {
        TestSerialGCAllocation.testTenuringThreshold2();
    }

    /*
    [GC (Allocation Failure) [PSYoungGen: 7909K->1016K(9216K)] 7909K->3372K(19456K), 0.0025941 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
    [GC (Allocation Failure) [PSYoungGen: 7316K->952K(9216K)] 9673K->9452K(19456K), 0.0052661 secs] [Times: user=0.05 sys=0.00, real=0.00 secs]
    [Full GC (Ergonomics) [PSYoungGen: 952K->0K(9216K)] [ParOldGen: 8500K->7102K(10240K)] 9452K->7102K(19456K), [Metaspace: 4713K->4713K(1056768K)], 0.0070824 secs] [Times: user=0.00 sys=0.00, real=0.01 secs]
    Heap
     PSYoungGen      total 9216K, used 6447K [0x00000000ff600000, 0x0000000100000000, 0x0000000100000000)
      eden space 8192K, 78% used [0x00000000ff600000,0x00000000ffc4bdd8,0x00000000ffe00000)
      from space 1024K, 0% used [0x00000000fff00000,0x00000000fff00000,0x0000000100000000)
      to   space 1024K, 0% used [0x00000000ffe00000,0x00000000ffe00000,0x00000000fff00000)
     ParOldGen       total 10240K, used 7102K [0x00000000fec00000, 0x00000000ff600000, 0x00000000ff600000)
      object space 10240K, 69% used [0x00000000fec00000,0x00000000ff2ef9c0,0x00000000ff600000)
     Metaspace       used 4735K, capacity 5130K, committed 5248K, reserved 1056768K
      class space    used 550K, capacity 562K, committed 640K, reserved 1048576K
     */
    @Test
    public void testHandlePromotion() {
        TestSerialGCAllocation.testHandlePromotion();
    }
}
