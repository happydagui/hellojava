package hello.oop;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by min on 17-3-8.
 */
public class OOPTest {
    @Test
    public void test1() {

        List<String> ls1 = new ArrayList<String>();

        // 这是一个匿名类
        List<String> ls2 = new ArrayList<String>() {};

        // 这也是一个匿名类，最里面的{}是匿名类的构造块
        List<String> ls3 = new ArrayList<String>() {{}};

        assert "java.util.ArrayList".equals(ls1.getClass().getName());
        assert "hello.oop.OOPTest$1".equals(ls2.getClass().getName());
        assert "hello.oop.OOPTest$2".equals(ls3.getClass().getName());
    }
}
