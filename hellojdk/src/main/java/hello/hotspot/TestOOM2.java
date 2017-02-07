package hello.hotspot;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试栈溢出：java.lang.StackOverflowError
 * Created by min on 17-2-5.
 * vm options:
 * -Xms20m -Xmx20m -Xss256k -XX:+HeapDumpOnOutOfMemoryError
 * 产生 java_pid<pid>.hprof ，可以使用 [MAT 工具]进行分析
 * (The stack size specified is too small, Specify at least 228k)
 */
public class TestOOM2 {
    static int stackIndex = 0;

    public void testStackOverFlow() {
        stackIndex++;
        testStackOverFlow();
    }

    public void testStackOverFlow2() {
        stackIndex++;
        int str = 12;
        int str2 = 12;
        int str3 = 12;
        int str4 = 12;
        int str5 = 12;
        int str6 = 12;
        int str7 = 12;
        int str8 = 12;
        int str9 = 12;
        testStackOverFlow2();
    }

    public static void main(String[] args) throws Throwable {
        try {
            TestOOM2 oom = new TestOOM2();
//            oom.testStackOverFlow(); // 3794
            oom.testStackOverFlow2(); // 2179: 明显小于3794，说明局部变量也占据了栈空间
        } catch (Throwable e) {
            System.out.println("Biggest statck deepth: " + stackIndex);
            throw e;
        }
    }
}
