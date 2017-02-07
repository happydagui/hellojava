package hello.hotspot;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试内存溢出：　java.lang.OutOfMemoryError: Java heap space
 * Created by min on 17-2-5.
 * vm options:
 * -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 * 产生 java_pid<pid>.hprof ，可以使用 [MAT 工具]进行分析
 */
public class TestOOM {
    static class OOMObject {

    }
    public static void main(String[] args) {
        List ls = new ArrayList();
        while (true) {
            ls.add(new OOMObject());
        }
    }
}
