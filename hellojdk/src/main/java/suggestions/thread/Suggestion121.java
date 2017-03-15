package suggestions.thread;

import hello.thread.ThreadPoolTest;

/**
 * Created by min on 17-3-14.
 * Notes: 只使用三个优先级
 * 1. 不完全按照优先级来运行线程
 * 2. 优先级差别越大，优先级作用越明显
 * 3.　不同操作系统线程优先级不同(windows:7, linux: 140, free bsd:255)，都要映射到Thread的 1-10
 */
public class Suggestion121 {
    public static void main(String[] args) {
//        for (int i = 0; i < 21; i++) {
//            new MyThread(i % 10 + 1).start();
//        }

        int[] defaults = {Thread.MIN_PRIORITY, Thread.NORM_PRIORITY, Thread.MAX_PRIORITY};
        for (int i = 0; i < 21; i++) {
            new MyThread(defaults[i%3]).start();
        }
    }
}
class MyThread extends Thread {
    public MyThread(int priority) {
        this.setPriority(priority);
    }

    @Override
    public void run() {
        //　模拟cpu密集计算，性能差的机器，减小循环次数
        for (int i = 0; i < 100000; i++) {
            Math.hypot(Math.pow(921212112, i), Math.cos(i));
        }

        System.out.println(Thread.currentThread().getName() + ", priority="
                + Thread.currentThread().getPriority());
    }
}
