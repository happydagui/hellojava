package hello.hotspot;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * Created by min on 17-2-8.
 * 使用unsafe分配本机内存,本机直接内存溢出
 * VM Args：-Xmx20M-XX：MaxDirectMemorySize=10M
 */
public class DirectMemoryOOM {
    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) throws Exception {
        Field field = Unsafe.class.getDeclaredFields()[0];
        field.setAccessible(true);
        Unsafe unsafe = (Unsafe) field.get(null);

        while (true) {
            unsafe.allocateMemory(_1MB);
        }
    }
}
