package hello.hotspot;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by min on 17-2-21.
 * -Xms100m -Xmx100m -XX:+UseSerialGC
 */
public class JConsoleTest {
    static class OOMObject {
        public byte[] placeHolder = new byte[64 * 1024]; // 64k
    }

    /*
    以64KB/100毫秒的速度往Java堆中填充数据，一共填充1000次
     */
    public static void fillHeap(int num) throws InterruptedException {
        List<OOMObject> list = new ArrayList<OOMObject>();
        for (int i = 0; i < num; i++) {
            Thread.sleep(100);
            list.add(new OOMObject());
        }
        System.gc();
    }

    public static void main(String[] args) throws Exception{
        fillHeap(1000);
    }
}
