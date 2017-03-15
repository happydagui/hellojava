package hello.lambda;

import org.junit.Test;

/**
 * Created by min on 17-3-7.
 * 要点：
 * 1. 如果需要一个函数式接口，可以按照传统方式使用匿名类也可以按照lambda方法传递
 * 2. 如果需要一个普通接口，可以按照传统方式使用匿名类也可以按照lambda方法传递
 * 3. 给函数式接口实例赋值，可以是任意匹配的方法，如类的实例方法，类的静态方法都可以
 * 4. 可以使用 lambda 表达式代替的地方 idea 编辑器会给出友好提示
 */
public class Howto {

    @Test
    public void hello2() {
        // JDK　源码
        // @FunctionalInterface
        // public interface Runnable { ... }

        // lambda 的写法
        Thread t = new Thread(() -> {
            System.out.println("In the thread");
        });

        // 传统的写法
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("In the thread 1");
            }
        });
        t.start();
        t1.start();
    }

    @Test
    public void hello3() {
        Printable<String> p1 = new Printable<String>() {
            @Override
            public String printme(String s) {
                return "[string]" + s;
            }
        };
        assert "[string]hello world".equals(p1.printme("hello world"));

        Printable<String > p12 = (str)-> "[string]" + str;
        assert "[string]hello world".equals(p12.printme("hello world"));

        FunctionalPrintable<String> p2 = (str)-> "[string]" + str;
        assert "[string]hello world".equals(p2.printme("hello world"));

        FunctionalPrintable<String> p22 = new FunctionalPrintable<String>() {
            @Override
            public String printme(String s) {
                return "[string]" + s;
            }
        };
        assert "[string]hello world".equals(p22.printme("hello world"));
    }

    @Test
    public void hello4() {
        FunctionalPrintable<String> p2 = this::printme;
        assert "[string]hello world".equals(p2.printme("hello world"));

        FunctionalPrintable<Integer> p21 = this::printme;
        assert "[int]120".equals(p21.printme(120));

        FunctionalPrintable<String> p212 = Howto::printmeStatic;
        assert "[string]hello world".equals(p212.printme("hello world"));

    }

    String printme(String s) {
        return "[string]" + s;
    }

    String printme(int i) {
        return "[int]" + i;
    }

    static String printmeStatic(String s) {
        return "[string]" + s;
    }
}
interface Printable<T> {
    String printme(T t);
}

@FunctionalInterface
interface FunctionalPrintable<T> {
    String printme(T t);
}
