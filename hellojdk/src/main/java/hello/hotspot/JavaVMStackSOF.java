package hello.hotspot;

/**
 * 测试栈溢出：java.lang.StackOverflowError
 * Created by min on 17-2-5.
 * vm options:
 * -Xms20m -Xmx20m -Xss256k -XX:+HeapDumpOnOutOfMemoryError
 * 产生 java_pid<pid>.hprof ，可以使用 [MAT 工具]进行分析
 * (The stack size specified is too small, Specify at least 228k)
 */
public class JavaVMStackSOF {
    private int stackLength = 0;

    public void stackLeak() {
        stackLength++;
        stackLeak();
    }

    public void stackLeak2() {
        stackLength++;
        int str = 12;
        int str2 = 12;
        int str3 = 12;
        int str4 = 12;
        int str5 = 12;
        int str6 = 12;
        int str7 = 12;
        int str8 = 12;
        int str9 = 12;
        stackLeak2();
    }

    public static void main(String[] args) throws Throwable {
        JavaVMStackSOF oom = new JavaVMStackSOF();
        try {
//            oom.stackLeak(); // 3794
            oom.stackLeak2(); // 2179: 明显小于3794，说明局部变量也占据了栈空间
        } catch (Throwable e) {
            System.out.println("Stack length: " + oom.stackLength);
            throw e;
        }
    }
}
