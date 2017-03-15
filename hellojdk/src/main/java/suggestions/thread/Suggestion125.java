package suggestions.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by min on 17-3-14.
 * Notes: 优先选择使用线程池
 * @see java.util.concurrent.ThreadPoolExecutor
 */
public class Suggestion125 {
    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(2);
        for (int i = 0; i < 20; i++) {
            es.submit(() -> System.out.println(Thread.currentThread().getName()));
        }
        es.shutdown();
    }
}
