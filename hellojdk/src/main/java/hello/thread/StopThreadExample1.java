package hello.thread;

/**
 * Created by min on 17-2-10.
 */
public class StopThreadExample1 {

    public static void main(String[] args) {
        test1();
        // test2();
    }
    static void test1() {
        MyThread001 thread = new MyThread001();
        thread.start();

        try {
            Thread.sleep(1000);
            System.out.println(thread.isInterrupted()); // false
            thread.interrupt();
            System.out.println(thread.isInterrupted());// true
            System.out.println(Thread.interrupted()); // false
            System.out.println(Thread.interrupted()); // false

            System.out.println(Thread.interrupted()); // false
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    static void test2() {
        MyThread002 myThread001 = new MyThread002();
        Thread thread = new Thread(myThread001);
        thread.start();

        try {
            Thread.sleep(1000);
            System.out.println(thread.isInterrupted()); // false
            thread.interrupt();
            System.out.println(thread.isInterrupted());// true
            System.out.println(Thread.interrupted()); // false
            System.out.println(Thread.interrupted()); // false

            System.out.println(Thread.interrupted()); // false
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class MyThread001 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 500000; i++) {
            if (this.isInterrupted()) {
                System.out.println("isInterrupted");
                break;
            }
            System.out.println(i);
        }
        System.out.println("MyThread001 END");
    }
}

class MyThread002 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 500000; i++) {
            if (Thread.interrupted()) {
                System.out.println("isInterrupted");
                break;
            }
            System.out.println(i);
        }
        System.out.println("MyThread001 END");
    }
}
