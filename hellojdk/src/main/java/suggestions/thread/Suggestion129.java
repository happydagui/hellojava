package suggestions.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by min on 17-3-14.
 * Notes: 适当设置阻塞队列长度
 */
public class Suggestion129 {
    public static void main(String[] args) {
        BlockingQueue<Integer> bq = new ArrayBlockingQueue<Integer>(5);
        // java.lang.IllegalStateException: Queue full
        for (int i = 0; i < 10; i++) {
            bq.add(i);
        }
        // 阻塞队列长度是不能扩展的。
        // bq.put(123); // 线程池中是使用的 put ，而不是 add ，put 队列满的话，会阻塞
    }
}
