package hello.hotspot;

/**
 * Created by min on 17-2-21.
 * -verbosegc -Xmx20M -Xms20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:+HeapDumpOnOutOfMemoryError
 * 通过　Eclipse MAT 打开 .hprof　文件，在 dominator_tree 面板的 main 线程中可以看到已经分配的前三个数组对象
 * > jdk 1.8
 */
public class VMSamples {
    public static final int _1MB = 1024 * 1024;

    public static void main(String[] args) {
        byte[] allocation1, allocation2, allocation3, allocation4;
        allocation1 = new byte[2 * _1MB];
        allocation2 = new byte[4 * _1MB];
        allocation3 = new byte[6 * _1MB];
        allocation4 = new byte[4 * _1MB]; //
    }
}

/*
-- 不设置 Xmn 参数，没有 OutOfMemoryError，新生代　４m + 老年代　12m
[min@t410 classes]$ java -verbose:gc -Xmx20M -Xms20M -XX:+PrintGCDetails -XX:SurvivorRatio=8 hello.hotspot.VMSamples
[GC (Allocation Failure) [PSYoungGen: 2627K->384K(6144K)] 12867K->12680K(19968K), 0.0030068 secs] [Times: user=0.01 sys=0.00, real=0.00 secs]
[Full GC (Ergonomics) [PSYoungGen: 384K->0K(6144K)] [ParOldGen: 12296K->12546K(13824K)] 12680K->12546K(19968K), [Metaspace: 2484K->2484K(1056768K)], 0.0062604 secs] [Times: user=0.01 sys=0.00, real=0.01 secs]
Heap
 PSYoungGen      total 6144K, used 4152K [0x00000000ff980000, 0x0000000100000000, 0x0000000100000000)
  eden space 5632K, 73% used [0x00000000ff980000,0x00000000ffd8e2c8,0x00000000fff00000)
  from space 512K, 0% used [0x00000000fff00000,0x00000000fff00000,0x00000000fff80000)
  to   space 512K, 0% used [0x00000000fff80000,0x00000000fff80000,0x0000000100000000)
 ParOldGen       total 13824K, used 12546K [0x00000000fec00000, 0x00000000ff980000, 0x00000000ff980000)
  object space 13824K, 90% used [0x00000000fec00000,0x00000000ff840ba8,0x00000000ff980000)
 Metaspace       used 2491K, capacity 4486K, committed 4864K, reserved 1056768K
  class space    used 268K, capacity 386K, committed 512K, reserved 1048576K

 */

/* 设置 Xmn 参数（年轻代最大值）
[min@t410 classes]$ java -verbose:gc -Xmx20M -Xms20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:+HeapDumpOnOutOfMemoryError hello.hotspot.VMSamples
[GC (Allocation Failure) --[PSYoungGen: 6815K->6815K(9216K)] 12959K->15015K(19456K), 0.0035543 secs] [Times: user=0.01 sys=0.00, real=0.00 secs]
[Full GC (Ergonomics) [PSYoungGen: 6815K->4354K(9216K)] [ParOldGen: 8200K->8192K(10240K)] 15015K->12546K(19456K), [Metaspace: 2484K->2484K(1056768K)], 0.0060694 secs] [Times: user=0.01 sys=0.00, real=0.01 secs]
[GC (Allocation Failure) --[PSYoungGen: 4354K->4354K(9216K)] 12546K->12554K(19456K), 0.0011800 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
[Full GC (Allocation Failure) [PSYoungGen: 4354K->4343K(9216K)] [ParOldGen: 8200K->8192K(10240K)] 12554K->12535K(19456K), [Metaspace: 2484K->2484K(1056768K)], 0.0043908 secs] [Times: user=0.01 sys=0.00, real=0.01 secs]
java.lang.OutOfMemoryError: Java heap space
Dumping heap to java_pid4980.hprof ...
Heap dump file created [13377309 bytes in 0.151 secs]
Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
        at hello.hotspot.VMSamples.main(VMSamples.java:15)
Heap
 PSYoungGen      total 9216K, used 4589K [0x00000000ff600000, 0x0000000100000000, 0x0000000100000000)
  eden space 8192K, 56% used [0x00000000ff600000,0x00000000ffa7b4a8,0x00000000ffe00000)
  from space 1024K, 0% used [0x00000000fff00000,0x00000000fff00000,0x0000000100000000)
  to   space 1024K, 0% used [0x00000000ffe00000,0x00000000ffe00000,0x00000000fff00000)
 ParOldGen       total 10240K, used 8192K [0x00000000fec00000, 0x00000000ff600000, 0x00000000ff600000)
  object space 10240K, 80% used [0x00000000fec00000,0x00000000ff4000c0,0x00000000ff600000)
 Metaspace       used 2516K, capacity 4486K, committed 4864K, reserved 1056768K
  class space    used 271K, capacity 386K, committed 512K, reserved 1048576K
[min@t410 classes]$

 */