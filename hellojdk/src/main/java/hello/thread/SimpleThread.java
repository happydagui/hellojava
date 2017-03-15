package hello.thread;

import java.util.Scanner;

/**
 * Created by min on 17-2-13.
 * Topic: 如何使用性能工具
 * 1. 配置启动参数
 * java -Dcom.sun.management.jmxremote -Xmx20m -Xms20m -XX:+UnlockCommercialFeatures -XX:+FlightRecorder hello.thread.SimpleThread
 * 2. 查看基本信息
 * jps -l
 * jinfo -flags [pid]
 * 3. 使用　jmc　搜集信息
 * jmc
 */
public class SimpleThread extends Thread {
    private Hello hello;

    public SimpleThread(Hello hello) {
        this.hello = hello;
    }
    public static void main(String[] args) {
        SimpleThread thread = new SimpleThread(new Hello());
        thread.run();

        Scanner sc = new Scanner(System.in);
        System.out.println("Press any key to stop");
        String s = sc.nextLine();

        thread.getHello().end = true;

    }

    public Hello getHello() {
        return hello;
    }

    @Override
    public void run() {
        super.run();
        hello.sayHello();
    }
}


class Hello {
    public volatile boolean end = false;
    public void sayHello() {
        try {
            while (end) {
                byte[] bytes = new byte[1024];
                Thread.sleep(1000);
                byte[] bytes1 = new byte[4096];
            }
            System.out.println("hello");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
