package suggestions.thread;

import java.util.Calendar;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by min on 17-3-14.
 * Notes: Lock 和 synchronized
 * 1. Lock　支持细粒度的控制
 * 2. Lock　需要显式 lock 和 unlock
 */
public class Suggestion127 {
    public static void main(String[] args) throws Exception {
        runTask(TaskWithLock.class);  // wrong
        runTask(TaskWithSync.class);  // wrong
        runTask(TaskWithSyncV2.class);  // right
        runTaskWithSingleLock(); // right

    }

    private static void runTask(Class<? extends Runnable> clz) throws Exception {
        ExecutorService es =  Executors.newCachedThreadPool();
        for (int i = 0; i < 3; i++) {
            es.execute(clz.newInstance());
        }

        TimeUnit.SECONDS.sleep(10);
        System.out.println("-----" + clz.getSimpleName() + " over.");
        es.shutdown();
    }

    private static void runTaskWithSingleLock() throws Exception {
        ReentrantLock lock = new ReentrantLock();
        ExecutorService es = Executors.newCachedThreadPool();
        for (int i = 0; i < 3; i++) {
            es.execute(new TaskWithLockV2(lock));
        }
        TimeUnit.SECONDS.sleep(10);
        System.out.println("-----" + TaskWithLockV2.class.getSimpleName() + " over.");
        es.shutdown();
    }
}

class Task {
    public void doSomething() { // 如果锁有效的话，该方法的不同线程执行时间至少差２秒
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {

        }
        StringBuffer sb = new StringBuffer();
        sb.append(Thread.currentThread().getName());
        sb.append("," + Calendar.getInstance().get(Calendar.SECOND) + "s");
        System.out.println(sb.toString());
    }
}

class TaskWithLock extends Task implements Runnable {
    // 这里肯定没有锁的功能，每一个对象有自己独立的锁实例
    private ReentrantLock lock = new ReentrantLock();
    @Override
    public void run() {
        try {
            lock.lock();
            doSomething();
        } finally {
            lock.unlock();
        }
    }
}

class TaskWithSync extends Task implements Runnable {
    // 这里肯定没有锁的功能，每一个对象有自己独立的锁实例
//    private Object lock = new Object();
    @Override
    public void run() {
        synchronized (this) {
            doSomething();
        }
    }
}

class TaskWithLockV2 extends Task implements Runnable {
    // 这里肯定没有锁的功能，每一个对象有自己独立的锁实例
    private ReentrantLock lock;

    public TaskWithLockV2(ReentrantLock lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            lock.lock();
            doSomething();
        } finally {
            lock.unlock();
        }
    }
}

class TaskWithSyncV2 extends Task implements Runnable {
    @Override
    public void run() {
        synchronized ("A") {
            doSomething();
        }
    }
}