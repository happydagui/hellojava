package hello.core;

/**
 * Created by min on 17-2-14.
 * NO.51 当心字符串连接的性能
 */
public class StringSample {
    public static void main(String[] args) {
        test1(); // 1086 milliseconds
        test2(); // 7 milliseconds
    }

    static void test1() {
        long start = System.currentTimeMillis();
        int total = 10000;
        String result = "";
        for (int i = 0; i < total; i++) {
            result += ("foo" + i);
        }
        //System.out.println(result);
        System.out.println("time: " + (System.currentTimeMillis() - start));
    }

    static void test2() {
        long start = System.currentTimeMillis();
        int total = 10000;
        final int LINE_WIDTH = 8; // maybe
        StringBuilder result = new StringBuilder(total * LINE_WIDTH);
        for (int i = 0; i < total; i++) {
            result.append("foo" + i);
        }
        //System.out.println(result);
        System.out.println("time: " + (System.currentTimeMillis() - start));
    }
}
