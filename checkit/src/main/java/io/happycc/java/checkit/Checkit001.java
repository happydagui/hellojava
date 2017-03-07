package io.happycc.java.checkit;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ArrayUtils;

import java.io.*;
import java.util.*;

/**
 * Created by min on 17-2-26.
 * vmargs: -ea
 */
public class Checkit001 {
    public static void main(String[] args) {
        Checkit001 inst = new Checkit001();
        inst.test01();
        inst.test002_inputstream_to_string();
        inst.test003_array_to_list();
        inst.test004_iteror_map();
        inst.test06_array_contains();
        inst.test08_string_to_int();
    }

    /**
     * @see [§15.26.2 Compound Assignment Operators](http://docs.oracle.com/javase/specs/jls/se8/html/jls-15.html#jls-15.26.2)
     * 对复合赋值表达式来说，E1 op= E2 (诸如 i += j; i -= j 等等)，其实是等同于 E1 = (T)((E1) op (E2))，
     * 其中，T是E1这个元素的类型
     */
    public void test01() {
        int i = 5;
        long j = 10;

        i += j; // 可以编译通过

//        i = i + j; // 编译不通过
        i = (int) (i + j ); // will work
    }

    public void test002_inputstream_to_string() {
        final String astring = "hello world\nsecond line";
        final String filename = "test002.dat";
        OutputStream os = null;
        try {
            os = new BufferedOutputStream(new FileOutputStream(new File(filename)));
            os.write(astring.getBytes("utf-8"));
            os.flush();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // 方法1: 使用 Apache Commons io
        InputStream is = null;
        try {
            is = new BufferedInputStream(new FileInputStream(new File(filename)));
            String result = IOUtils.toString(is, "utf-8");
            assert astring.equals(result);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        // 方法2:　使用原生库
        try {
            is = new BufferedInputStream(new FileInputStream(new File(filename)));
            Scanner scanner = new Scanner(is).useDelimiter("\\A");
            String result = scanner.hasNext() ? scanner.next(): "";
            assert astring.equals(result);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        // System.out.println("\A"); // \A
        // TODO: \\A　是个啥？
    }

    /**
     * public static <T> List<T> asList(T... a)
     * 传入数组或者，列出每个元素都可以，返回的是定长列表，可以修改元素，但不能增加或删除
     */
    public void test003_array_to_list() {
        List result = Arrays.asList("a", "c", "d");
//        result.add("e"); // 错误：　asList　返回固定长度的列表

        String[] source = new String[] {"hello", "world"};
        // 方法1
        result = new ArrayList(Arrays.asList(source));
        result.add("foo bar");
        assert result.size() == 3;

        //　方法2
        result = new ArrayList();
        Collections.addAll(result, source);
        assert result.size() == 2;
    }


    public void test004_iteror_map() {
        Map<String, Integer> source = new HashMap<String, Integer>();
        source.put("hello", 1);
        source.put("world", 2);

        // 方法1: 需要访问键值对时最有效
        for (Map.Entry<String, Integer> entry : source.entrySet()) {

        }

        for (String key : source.keySet()) {
            // 通过key得到value值更耗时（这个方法在所有实现map接口的map中比方法#1慢20%-200%）
            source.get(key); // 不建议，较慢
        }
        for (Integer value : source.values()) {

        }

        // 需要边迭代边删除的话，需要使用迭代器，而且必须在调用 next() 之后
        for (Iterator<Map.Entry<String, Integer>> it = source.entrySet().iterator(); it.hasNext();) {
            it.next();
            it.remove();
        }

        assert source.size() == 0;
    }

    public void test06_array_contains() {
        final String[] VALUES = new String[] {"AB","BC","CD","AE"};
        // 优雅的写法1
        assert Arrays.asList(VALUES).contains("BC");
        assert !Arrays.asList(VALUES).contains("BCD");

        // 优雅的写法2
        assert ArrayUtils.contains(VALUES, "BC");
        assert !ArrayUtils.contains(VALUES, "BCD");

        // 普通写法
        for (String s: VALUES) {
            if ("BC".equals(s)) {
                // ...
            }
        }

        // 如果数组本身有序
//        int pos = Arrays.binarySearch(VALUES, "AB");
    }

    public void test08_string_to_int() {
        assert 1234 == Integer.parseInt("1234");
        assert 26 == Integer.parseInt("1a", 16);
    }

    public void tt() {
        OutputStream os = System.out;
        try {
            os.write("hello world".getBytes("utf-8"));
            os.write("测试".getBytes("utf-8"));
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
