package hello.hotspot;

/**
 * Created by min on 17-2-6.
 */
public class ClassInitializingTest {
    public static int k = 0;
    public static ClassInitializingTest t1 = new ClassInitializingTest("t1");
    public static ClassInitializingTest t2 = new ClassInitializingTest("t2");
    public static int i = print("i");
    public static int n = 99;
    public int j = print("j");

    {
        print("构造");
    }

    static {
        print("静态");
    }

    public ClassInitializingTest(String str) {
        System.out.println((++k) + ":" + str + "    i=" + i + " n=" + n);
        ++i;
        ++n;
    }

    public static int print(String str) {
        System.out.println((++k) + ":" + str + "    i=" + i + " n=" + n);
        ++n;
        return ++i;
    }

    public static void main(String[] args) {
        ClassInitializingTest t = new ClassInitializingTest("init");
    }
}
/*
// 实例化静态变量 t1，每次实例化一个对象，由于静态初始化已经开始，不再重新设置静态变量，从实例变量 j　开始
。依次是实例变量和实例块（按声明顺序），构造函数
1:j    i=0 n=0
2:构造    i=1 n=1
3:t1    i=2 n=2

// 实例化静态变量 t2
4:j    i=3 n=3
5:构造    i=4 n=4
6:t2    i=5 n=5

// 实例化静态变量 i
7:i    i=6 n=6
// 实例化静态块
8:静态    i=7 n=99

// 实例化 init
9:j    i=8 n=100
10:构造    i=9 n=101
11:init    i=10 n=102
 */