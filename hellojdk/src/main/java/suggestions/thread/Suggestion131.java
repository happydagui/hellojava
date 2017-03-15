package suggestions.thread;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * Created by min on 17-3-14.
 * Note: CyclicBarrier 让多线程齐步走
 * 1. 何时还是并不管，只保证一起到达
 * 2. `cyclicBarrier.await()`等待，后面的代码最后才能执行　（不同于CountDownLatch）
 */
public class Suggestion131 {
    public static void main(String[] args) {
        // 等待两个线程到达临界点
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2, ()-> {
            System.out.println("All works done.");
        });
        new Thread(new Worker(cyclicBarrier)).start();
        new Thread(new Worker(cyclicBarrier)).start();
    }
}
class Worker implements Runnable {
    private CyclicBarrier cyclicBarrier;

    public Worker(CyclicBarrier cyclicBarrier) {
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(new Random().nextInt(10));
            System.out.println(Thread.currentThread().getName() + ", before ``cyclicBarrier.await()``");
            cyclicBarrier.await();
            System.out.println("after `cyclicBarrier.await()` ");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
