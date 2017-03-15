package suggestions.generictype;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by min on 17-3-14.
 * Notes: 无法推导时，强制泛型的实际类型
 * @see java.util.Collections
 * public static <T> void copy(List<? super T> dest, List<? extends T> src)
 */
public class Suggestion096 {
    public static void main(String[] args) {
        List<Number> list = new ArrayList<>();
        read2(list);

        readAndWrite(list);
        write(list, new Integer(12));
    }

    private static <E> void read(List<? super E> list) {
//        for (E e : list) { // 肯定不行，E 的父类不一定能cast成E
//
//        }
        for (Object e: list) {
            // do something
        }
    }

    // 读操作使用 extends 声明下界
    private static <E> void read2(List<? extends E> list) {
        for (E e: list) {
            // do something
        }
    }

    // 写操作使用 super 声明上界
    // 表明可以加入任何Number的子类做为成员，自然可以加入整数，浮点数等
    private static <T> void write(List<? super T> list, T t) {
        list.add(t);
    }

    // won't compile
    //private static <T> void write2(List<? extends T> list) {
    //    list.add(23);
    //}

    // 读和写，不能用super或者extends
    private static <T> void readAndWrite(List<T> list)  {
        // do something
    }
}


