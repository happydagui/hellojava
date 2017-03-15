package hello;

import org.junit.Test;

import java.util.*;

/**
 * -ea
 * Created by min on 17-3-13.
 */
public class JavaPitfall {

    @Test
    public void primitiveWithArray() {
        /**
         * 基本类型数组使用 Arrays.asList　陷阱
         * public static <T> List<T> asList(T... a) {
         *  return new ArrayList<>(a);
         * }
         * 1. 基本类型的数组只能匹配一个 T　元素
         * 2. 所有基本类型都有整个问题
         * 3. 解决方法：　使用包装类
         */
        int[] data = {1, 2, 3, 4, 5};
        List arr = Arrays.asList(data); // 这里asList把整个数组当成了一个元素
                                        // data 匹配了一个 T
        // Notice: 1 not 5
        assert arr.size() == 1;
        assert data.equals(arr.get(0));
        System.out.println(arr.get(0).getClass()); // class [I

        Integer[] dataWrapped = {1, 2, 3, 4, 5};
        List<Integer> results = Arrays.asList(dataWrapped);   // 这个每个元素匹配了一个 T
        assert results.size() == 5;
        for (Integer e : results) {
            System.out.println(e);
        }
    }

    @Test
    public void loopCollections() {
        // ArrayList 实现了 RandomAccess 接口，随机访问更快
        // LinkedList　未实现 RandomAccess，链表结构顺序访问更快
        // 记住　forEach 访问是顺序访问，内部是迭代器
        loopArrayListWithForeach(); // about 34ms
        loopArrayListWithFor();     // about 20ms
        loopLinkedListWithForeach();// about 20ms
        // loopLinkedListWithFor();    // too long
    }

    @Test
    public void compareList() {
        // 列表比较只要长度相等，元素相等
        ArrayList<String> array = new ArrayList<>();
        array.add("A");

        Vector<String > vector = new Vector<>();
        vector.add("A");

        assert array.equals(vector);
    }

    @Test
    public void subList() {
        // 子列表是原列表的子视图，所有操作都是在原列表上的操作
        List<String> c = new ArrayList<>();
        c.add("A");
        c.add("B");

        List<String> c1 = new ArrayList<>(c);

        List<String> c2 = c.subList(0, c.size());
        c2.add("C");

        assert !c.equals(c1);
        assert c.equals(c2);
        assert c.size() == 3;
    }

    @Test
    public void subList2() {
        // a fix-size list
        List<Integer> datasource = Collections.nCopies(100, 1);
        List<Integer> data = new ArrayList<>(datasource);

        // 推荐使用　subList　处理局部列表
        data.subList(20, 30).clear();   // 最简单的 one line 代码
        assert  data.size() == 90;

        for (int i = 20; i < 30; i++) {
            if (i < data.size()) {
                data.remove(i);
            }
        }
        assert data.size() == 80;
    }

    @Test
    public void subList3() {
        // 创建子视图后若修改了原来的列表，会出现并发修改异常
        // 通过源码可发现　subList　内部状态不一致就会出现该异常
        // 在最后一个对原列表的写操作之后在生成子列表，
        // 然后传递一个只读的列表给外部使用

        List<String> data = new ArrayList<>();
        data.add("A");
        data.add("B");
        data.add("C");

        List<String> sub = data.subList(0, data.size());
        //data.add("D");
        //assert data.size() == 4;
        // 创建子视图后若修改了原来的列表，会出现并发修改异常
        // java.util.ConcurrentModificationException
        // assert sub.size() == 4;
        // java.util.ConcurrentModificationException
        // assert "A".equals(sub.get(0));

        // 一旦生成子列表后，就不要修改原列表了，可以
        // 通过 Collections.unmodifiableList 传递结果给其他人调用，
        data = Collections.unmodifiableList(data);
        // doReadSomething(data);
        // 修改操作在子列表上操作即可
        // doReadAndWriteSomething(sub);
        sub.add("D");
        assert data.size() == 4;
    }

    public void loopArrayListWithForeach() {
        int total = 800000;
        List<Integer> scores = new ArrayList<>(total);
        for (int i = 0; i < total; i++) {
            scores.add(new Random().nextInt(150));
        }
        long start = System.currentTimeMillis();
        int sum = 0;
        for (Integer e : scores) {
            sum += e;
        }
        System.out.println(sum / (scores.size() * 1.0));
        System.out.println("Elapse: " + (System.currentTimeMillis() - start));
    }
    public void loopArrayListWithFor() {
        int total = 800000;
        List<Integer> scores = new ArrayList<>(total);
        for (int i = 0; i < total; i++) {
            scores.add(new Random().nextInt(150));
        }
        long start = System.currentTimeMillis();
        int sum = 0;
        for (int i = 0, size = scores.size(); i < size; i++) {
            sum += scores.get(i);
        }
        System.out.println(sum / (scores.size() * 1.0));
        System.out.println("Elapse: " + (System.currentTimeMillis() - start));
    }

    public void loopLinkedListWithForeach() {
        int total = 800000;
        List<Integer> scores = new LinkedList<>();
        for (int i = 0; i < total; i++) {
            scores.add(new Random().nextInt(150));
        }
        long start = System.currentTimeMillis();
        int sum = 0;
        for (Integer e : scores) {
            sum += e;
        }
        System.out.println(sum / (scores.size() * 1.0));
        System.out.println("Elapse: " + (System.currentTimeMillis() - start));
    }

    @Deprecated
    public void loopLinkedListWithFor() {
        int total = 800000;
        List<Integer> scores = new LinkedList<>();
        for (int i = 0; i < total; i++) {
            scores.add(new Random().nextInt(150));
        }
        long start = System.currentTimeMillis();
        int sum = 0;
        for (int i = 0, size = scores.size(); i < size / 10; i++) {
            sum += scores.get(i);
            System.out.print(".");
        }
        System.out.println();
        System.out.println(sum / (scores.size() * 1.0));
        System.out.println("Elapse: " + (System.currentTimeMillis() - start));
    }
}
