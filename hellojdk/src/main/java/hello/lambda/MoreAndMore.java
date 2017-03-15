package hello.lambda;

import org.junit.Test;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * 使用内置函数式接口　
 * 1. Predicate\<T\>　输入一个值，返回布尔值
 * 2. Function\<T, R\>　输入一个值，返回一个值
 * 3. Supperir\<T\> 无输入，返回值
 * 4. Consumer\<T\> 输入一个值，无返回
 * Created by min on 17-3-7.
 */
public class MoreAndMore {
    @Test
    public void testPredicate() {
        // @FunctionalInterface
        // public interface Predicate<T> {
        // boolean test(T t);
        // 接受一个参数，返回一个布尔值
        Predicate<String> predicate = str -> {
            return str.length() > 2;
        };
        assert predicate.test("hello world");
        assert predicate.test("1") == false;
    }

    @Test
    public void testSupplierAndConsumer() {
        // 无输入参数，返回值
        Supplier<String> supplier = () -> "hello world";
        assert "hello world".equals(supplier.get());

        // 输入一个参数，无返回值
        Consumer<Integer> consumer = (p) -> {
            System.out.println("Consuming " + p);
        };
        consumer.accept(100);
    }

    @Test
    public void testFunction() {
        //　接受输入，返回输出
        Function<String, Integer> function = Integer::valueOf;
        assert function.apply("123") == 123;
    }
}
