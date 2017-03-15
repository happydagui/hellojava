package suggestions.thread;

import java.util.concurrent.TimeUnit;

/**
 * Created by min on 17-3-14.
 * Notes: 使用线程异常处理器提升系统可靠性
 */
public class Suggestion122 {
    public static void main(String[] args) {
        TCPServer server =  new TCPServer();
    }
}

class TCPServer implements Runnable {
    public TCPServer() {
        Thread t = new Thread(this);
        t.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println("Thread Exception, trying to reset...");
                e.printStackTrace();
                new TCPServer();
            }
        });
        t.start();
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println("Run normally#" + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        throw new RuntimeException();
    }
}
