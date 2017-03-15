package suggestions.generictype;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by min on 17-3-14.
 * Notes: 无法推导时，强制泛型的实际类型
 */
public class Suggestion095 {
    public static void main(String[] args) {
        List<String> list1 = ArrayUtils.asList("A", "B");

        List list2 = ArrayUtils.asList(); // 不安全
        list2.add(120);
        list2.add("AB");

        List list3 = ArrayUtils.asList(100, 10.0); // 不安全
        list3.add(120);
        list3.add("AB");

        // 强制泛型参数类型：　显式指明泛型类型，如果无法通过参数推导
        List<Integer> list21 = ArrayUtils.<Integer>asList();
        // list21.add(100.0); //won't compile
        list21.add(100);

        // 强制泛型参数类型：　显式指明泛型类型，如果无法通过参数推导
        List<Number> list31 = ArrayUtils.<Number>asList(100, 100.0);

        List<String> m = (new Foo<String>()).list;
    }
}

class ArrayUtils {
    public static final <T> List<T> asList(T... t) {
        List<T> list = new ArrayList<>();
        Collections.addAll(list, t);
        return list;
    }
}
class Foo<T> {
    // private T t = new T(); // won't compile
    // private T[] t2 = new T[5]; // won't compile
    List<T> list = new ArrayList<T>(); // ok
}
