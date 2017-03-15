package suggestions.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by min on 17-3-14.
 * Notes: 使用 CountDownLatch 协调线程
 * 1. CountDownLatch 类似于倒计数器
 * 2. 线程 coundDown　之后的代码可以继续执行 (使用cyclicBarrier调用await之后只能等待)
 */
public class Suggestion130 {
    static class Runner implements Callable<Integer> {
        private CountDownLatch begin;
        private CountDownLatch end;

        public Runner(CountDownLatch begin, CountDownLatch end) {
            this.begin = begin;
            this.end = end;
        }

        @Override
        public Integer call() throws Exception {
            begin.await();  // 等待发令枪响
            int score = new Random().nextInt(10);
            TimeUnit.SECONDS.sleep(score);
            System.out.println(Thread.currentThread().getName() + ", before `end.countDown()`");

            end.countDown(); // 我已经跑完
            System.out.println(Thread.currentThread().getName() + ", after `end.countDown()`");

            // 上面两句不是同步块，打印结果并不一定是一致的
            return score;
        }
    }

    public static void main(String[] args) throws Exception {
        final int NUM = 10;
        final CountDownLatch begin = new CountDownLatch(1);
        final CountDownLatch end = new CountDownLatch(NUM);

        ExecutorService es = Executors.newFixedThreadPool(NUM);
        List<Future<Integer>> results = new ArrayList<>();

        for (int i = 0; i < NUM; i++) {
            results.add(es.submit(new Runner(begin, end)));
        }
        begin.countDown(); //　发令枪响：　开始计数器变成0，线程继续
        end.await();    // 等待全部结束： 等待结束计数器变成0
        System.out.println(Thread.currentThread().getName() + ", All works done.");


        int count = 0;
        for (Future<Integer> r : results) {
            System.out.print(r.get() + ", ");
            count += r.get();
        }
        System.out.println("\navg: " + count / (NUM * 1.0));
        // 否则主线程不会终止
        es.shutdown();
    }
}
