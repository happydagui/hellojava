package hello.hotspot;

import org.apache.commons.codec.binary.Hex;

import java.io.*;
import java.util.Arrays;

/**
 * Created by min on 17-2-7.
 */
public class ASimpleClassDecompiler {

    public static void main(String[] args) {
        test2();
    }

    public static void test2() {
        String filename = "ASimpleClass.class";
        try {
            InputStream o = new BufferedInputStream(new FileInputStream(filename));
            byte[] magic = new byte[4];
            o.read(magic);
            System.out.println(Hex.encodeHex(magic)); // cafebabe

            System.out.println(magic[0]); // -54
            System.out.print((magic[0]&0xF0)>>4); // c
            System.out.print((magic[0]&0x0F));     // a
            System.out.print((magic[1]&0xF0)>>4); // f
            System.out.print((magic[1]&0x0F));     // e
            System.out.print((magic[2]&0xF0)>>4); // b
            System.out.print((magic[2]&0x0F));     // a
            System.out.print((magic[3]&0xF0)>>4); // b
            System.out.println((magic[3]&0x0F));     // e

            byte[] minor_version = new byte[2];
            byte[] major_version = new byte[2];
            o.read(minor_version);
            o.read(major_version);
            System.out.println(Hex.encodeHex(minor_version)); // 0000
            System.out.println(Hex.encodeHex(major_version)); // 0034

            byte[] constant_pool_count = new byte[2];
            o.read(constant_pool_count); // 0039
            int cnt = (constant_pool_count[0] << 8) + constant_pool_count[1];
            System.out.println(cnt);  // 57
            // TODO
            for (int i = 0; i < cnt; i++) {
                byte[] tag = new byte[1];
                byte[] info;
            }



        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }
    }


    /**
     * @deprecated ObjectOutputStream　写入文件的时候，会带一个头部，所以不能直接使用 \
     * ObjectOutputStream　来读 .class　文件
     */
    public static void test1() {
        String filename = "ASimpleClass.class";
        try {
            ObjectInputStream o = new ObjectInputStream(new BufferedInputStream(new FileInputStream(filename)));
            int magic = o.readInt();
            // java.io.StreamCorruptedException: invalid stream header: CAFEBABE

            int minor_version = o.readShort();
            int major_version = o.readShort();
            System.out.println(String.format("magic: %d, minor_version: %d, majoir_version: %d", magic, minor_version, major_version));

        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }
    }
}
