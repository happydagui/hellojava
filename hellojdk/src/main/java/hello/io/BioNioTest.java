package hello.io;

import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by min on 17-3-10.
 * - FileChannel不能被设置成非阻塞模式。它总是运行在阻塞模式下。
 * - 在你使用文件通道之前必须打开它。你不能直接打开文件通道。
 * - 你需要通过InputStream，OutputStream或者RandomAccessFile来获取文件通道
 * - channel.read(buffer); 数据从通道到缓冲，直到缓冲满 => 向内输入，等同于buffer.put
 * - channel.write(buff); 数据从缓冲到通道，直到缓冲空　=> 先外输出，等同于buffer.get
 */
public class BioNioTest {
    static final String filename = "/media/resources/downloads/windows/microsoft/SW_DVD5_Office_Professional_Plus_2010w_SP1_W32_ChnSimp_CORE_MLF_X17-76734.iso";

    @Test
    public void test1() throws Exception {
        readBigFile(100);
        readBigFile(1000);
        readBigFile(10000);

        /*
File size: 1412091803
Loop 14120920 times
Total: 19.027s

File size: 1412090903
Loop 1412092 times
Total: 3.719s

File size: 1412081903
Loop 141210 times
Total: 0.426s
         */
    }

    @Test
    public void test2() throws Exception {
        readBigFile(10000);
        readBigFile(1000);
        readBigFile(100);

        /*
File size: 1412081903
Loop 141210 times
Total: 0.641s
File size: 1412090903
Loop 1412092 times
Total: 3.791s
File size: 1412091803
Loop 14120920 times
Total: 3.909s
         */
    }

    @Test
    public void test3() throws Exception {
        readBigFileWithNio(10000);
        readBigFileWithNio(1000);
        readBigFileWithNio(100);

        /*
File size: 1412091904
Channel size: 1412091904
Loop 141210 times
Total: 1.534s
File size: 1412091904
Channel size: 1412091904
Loop 1412092 times
Total: 1.45s
File size: 1412091904
Channel size: 1412091904
Loop 14120920 times
Total: 7.392s
         */
    }

    @Test
    public void test4() throws Exception {
        readBigFileWithNio(100);
        readBigFileWithNio(1000);
        readBigFileWithNio(10000);

        /*
File size: 1412091904
Channel size: 1412091904
Loop 14120920 times
Total: 8.228
File size: 1412091904
Channel size: 1412091904
Loop 1412092 times
Total: 0.946
File size: 1412091904
Channel size: 1412091904
Loop 141210 times
Total: 0.397
         */
    }

    void readBigFile(int buffSize) throws Exception {
        long start = System.currentTimeMillis();
        BufferedInputStream bi = new BufferedInputStream(new FileInputStream(new File(filename)));
        byte[] buffer = new byte[buffSize];

        int count = 0;
        int loop = 0;
        int read = bi.read(buffer);
        while (read != -1) {
            // do nothing
            count += read;
            loop++;
            read = bi.read(buffer);
        }
        System.out.println("File size: " + count);
        System.out.println("Loop " + loop + " times");
        System.out.println("Total: " + (System.currentTimeMillis() - start) / 1000.0);
        bi.close();
    }

    void readBigFileWithNio(int buffSize) throws Exception {
        long start = System.currentTimeMillis();
        FileChannel channel = new FileInputStream(new File(filename)).getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(buffSize);

        long count = 0;
        long count2 = channel.size();
        int loop = 0;
        int read = channel.read(buffer);
        while (read != -1) {
            // do nothing
            count += read;
            buffer.flip();
//            byte[] dst = new byte[buffSize];
//            buffer.get(dst)
            buffer.clear();
            loop++;
            read = channel.read(buffer);
        }
        System.out.println("File size: " + count);
        System.out.println("Channel size: " + channel.size());
        System.out.println("Loop " + loop + " times");
        System.out.println("Total: " + (System.currentTimeMillis() - start) / 1000.0);
        channel.close();
    }

    @Test
    public void writeWithNio() throws Exception {
        RandomAccessFile fromFile = new RandomAccessFile("demo.dat", "rw");
        FileChannel channel = fromFile.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(10);
        buffer.clear();
        buffer.put("Hello".getBytes());
        buffer.flip();  // Notice: 勿忘
        while(buffer.hasRemaining()) {
            channel.write(buffer); // 向通道里面写，buffer 越来越空，知道buffer中没有数据
        }
        fromFile.close();
    }
}
