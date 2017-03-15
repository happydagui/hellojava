package suggestions.thread;

import java.util.concurrent.*;

/**
 * Created by min on 17-3-14.
 * Notes: 异步运算考虑使用 Callable
 */
public class Suggestion124 {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService es = Executors.newFixedThreadPool(1);

        Future<Integer> future = es.submit(new TaxCaculator(100000));
        while (!future.isDone()) {
            TimeUnit.SECONDS.sleep(1);
            System.out.print(".");
        }
        System.out.println("\nResult is: " + future.get());
        es.shutdown();
    }
}
