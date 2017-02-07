package hello.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by min on 17-1-18.
 */
public class ThreadPoolTest {
    public static void main(String[] args) throws Exception {
//        test1();
        test2();
    }

    static void test1() {
        ExecutorService exec = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 10; i++) {
            exec.execute(new MyThread("Thread-" + i));
        }
    }

    static void test2() throws ExecutionException, InterruptedException {
        ExecutorService exec = Executors.newFixedThreadPool(3);
        List<Future<Integer>> results = new ArrayList<Future<Integer>>();
        for (int i = 0; i < 10; i++) {
            results.add(exec.submit(new MyCallable("Callable-" + i)));
        }

        for (Future<Integer> e : results) {
            System.out.println(e.get());
        }
    }
}

class MyThread implements Runnable {
    String name;
    public MyThread(String name) {
        this.name = name;
    }
    public void run() {
        try {
            Thread.sleep(1000);
            System.out.println(this.name);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class MyCallable implements Callable<Integer> {
    String name;
    public MyCallable(String name) {
        this.name = name;
    }
    public Integer call() throws Exception {
        Thread.sleep(1000);
        System.out.println(this.name);
        return ((int) (Math.random() * 1000)) % 1000;
    }
}

