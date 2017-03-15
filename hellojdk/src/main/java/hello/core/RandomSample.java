package hello.core;

import java.util.Random;

/**
 * Created by min on 17-2-14.
 * NO.47 了解和使用类库
 */
public class RandomSample {
    public static void main(String[] args) {
        test1();
    }

    static void test1() {
        // 产生的随机数还是很均匀的，可以修改下面的那个模
        Random random = new Random();
        int even = 0;
        for (int i = 0; i < 100000; i++) {
            if (random.nextInt() % 10 == 0) {
                even++;
            }
        }
        System.out.println(String.format("Total even: %d of %d", even, 100000));
    }
}
