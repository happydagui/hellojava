package hello.io;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Created by min on 17-2-7.
 */
public class ObjectIOTest {
    public static void main(String[] args) {
//        test();
        test2();
    }

    /* 结果可以分析， aced 0005 770e　是一个头部
    aced 0005 770e 0068 0065 006c 006c 006f
    0000 0002
     */
    public static void test() {
        try {
            ObjectOutputStream o = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("obj.dat")));
            o.writeChars("hello");
            o.writeInt(2);
            o.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void test2() {
        System.out.println((int) 'h'); // 104    0068
        System.out.println((int) 'e'); // 101    0065
        System.out.println((int) 'l'); // 108    006c
        System.out.println((int) 'l'); // 108    006c
        System.out.println((int) 'o'); // 111    006f
    }
}

/*
＠java.io.ObjectOutputStream
public final void writeInt(int v) throws IOException {
        out.write((v >>> 24) & 0xFF);
        out.write((v >>> 16) & 0xFF);
        out.write((v >>>  8) & 0xFF);
        out.write((v >>>  0) & 0xFF);
        incCount(4);
    }
 */
