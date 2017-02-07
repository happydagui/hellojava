package hello.hotspot;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试永生代内存溢出：　
 * Created by min on 17-2-5.
 * vm options:
 * -Xms20m -Xmx20m -XX:PermSize=5m -XX:+HeapDumpOnOutOfMemoryError
 * 产生 java_pid<pid>.hprof ，可以使用 [MAT 工具]进行分析
 * (ignoring option PermSize=5m; support was removed in 8.0)
 */
public class TestOOM3 {
    public static void main(String[] args) {
        List ls = new ArrayList();
        int i = 0;
        while (true) {
            ls.add(String.valueOf(i++).intern());
        }
    }
}

/*
Java HotSpot(TM) 64-Bit Server VM warning: ignoring option PermSize=5m; support was removed in 8.0
java.lang.OutOfMemoryError: GC overhead limit exceeded
Dumping heap to java_pid5418.hprof ...
Heap dump file created [25161408 bytes in 0.387 secs]
Exception in thread "main" java.lang.OutOfMemoryError: GC overhead limit exceeded
	at java.lang.Integer.toString(Integer.java:401)
	at java.lang.String.valueOf(String.java:3099)
	at hello.hotspot.TestOOM3.main(TestOOM3.java:18)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:498)
	at com.intellij.rt.execution.application.AppMain.main(AppMain.java:147)
 */