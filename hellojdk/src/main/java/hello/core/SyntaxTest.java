package hello.core;

import hello.core.sub.Human;

import java.util.*;

/**
 * Created by min on 17-2-9.
 * vm args: -ea
 * 知识点１：　子类继承父类的非 private 静态成员；通过子类访问父类的静态成员不会导致子类的静态初始化（非主动调用）
 * 知识点２：　通过Arrays.asList　返回的ArrayList是固定大小的(Returns a fixed-size list backed by the specified array.)，
 * 不能使用 add/remove　方法，但是可以使用　set　方法
 * 知识点３： java 1.5增加了可变参数。可变参数必须位于最后一项（所以最多只可能有一个可变参数），使用 type... args　声明，
 * 　　编译器为该可变参数创建一个数组
 * 知识点４：　Integer 使用构造函数生成的对象不相等，使用整数字面量赋值给包装类，会调用 Integer.valueOf方法，在-128到
 * 　　配置环境属性之间的包装对象使用缓存的值，而不是重新new 一个。
 * 　　@see IntegerSample
 * 知识点５： ArrayList　的　contains 方法内部是调用了　equals 方法，要确保该方法满足自反、传递、对称特征(effective java)
 * 知识点：可用inst.staticMethod 访问静态方法，编译可以通过
 */
public class SyntaxTest {
    public static void main(String[] args) {
        if (true || ques() == 1) { // 短路运算，不会执行第二个条件
            System.out.println("hello world");
        }
        ques20170215();
//        ques20170210();
//        ques20170209();
//        ques20170206();
//        ques20170116();
//        ques20170110();
//        ques20170105();
//        ques20170104();
//         ques20161228_plus_1229();
//         ques20161226();
        // ques20161214();
//        ques20161201();
//        ques();

        System.out.println(new Temp().hello());
    }
    static class Temp {
        double hello() {
           try {
               return divide(10, 0);
           } catch (Exception e) {
               e.printStackTrace();
           } finally {
               return -1;
           }
        }


        private double divide(int x, int y) {
            return x / y;
        }
    }

    static void ques20170215() {
        Child child = new Child();
        child.test();
    }

    static void ques20170210() {
        String str = new String("world");
        char[] ch= {'H', 'e', 'l', 'l', 'o'};
        Ques0210.change(str, ch);
        System.out.println(str); // world
        System.out.println(Arrays.toString(ch));// [C, e, l, l, o]
    }

    static void ques20170209() {
        Integer i = new Integer(0);
        Ques0209.add(i);
        System.out.println(i.intValue()); // 0
    }


    static void ques20170206() {
        Child ch = new Child();
        // 如果父子类不在一个package中，是无法通过实例访问父类的protected方法的.
        ch.method();

        class Man extends hello.core.sub.Human {

        }
        new Man().apublicmethod();
//        new Man().aprotectedmethod();
        new Human().apublicmethod();
//        new Human().aprotectedmethod();
    }
    static void ques20170120() {
        List<String> list0 = Arrays.asList("张三", "李四");
        // java.lang.UnsupportedOperationException
        // list0.add("王五");
        list0.set(0, "zhangshan");
        assert "zhangshan".equals(list0.get(0));

        List<String> list = new ArrayList<String>(Arrays.asList("张三", "李四"));
        list.add("王五");
        assert list.size() == 3;
    }


    static void ques20170116() {
        class Dog {
            String name;

            public Dog(String name) {
                super();
                this.name = name;
            }

            @Override
            public String toString() {
                return "name: " + name;
            }
        }
        class BigDog extends Dog {
            String color;

            public BigDog(String name, String color) {
                super(name);
                this.color = color;
            }

            @Override
            public String toString() {
                return "name: " + name + ", color=" + color;
            }
        }
        System.out.println(new BigDog("big dog", "black"));
    }

    static void ques20170112() {
        Number[] numbers = new Integer[10];
        // List<Number> list = new ArrayList<Integer>(); // compiler error.
    }

    static void ques20170111() {
        final Person p1 = new Person("zhangsan");
        final Person p2 = new Person("lisi");

        //p1 = new Person("wangwu"); // compiler error,
        p2.setName("wangwu");
    }

    static void ques20170110() {
        Person person = new Person("zhangsan");
        System.out.println("Before: " + person);
        Person.update(person);
        System.out.println("After: " + person);
    }

    static void ques20170109() {
        // 泛型类不能用来实例化对象
        new Ques0109<String>();
    }

    static void ques20170106() {
        class Ques0106 {
            public void method01(String... strings) {}
            // 语法错误： 可变参数必须是最后一个参数
            // public void method02(String... strings, int... ints) {}
        }
    }

    static void ques20170105() {
        List<String> list = new ArrayList<String>();
        list.add("a");

        List<String> list2 = new ArrayList<String>(list);
        List<String> list3 = list.subList(0, list.size());

        assert list.equals(list2);
        assert list.equals(list3);
    }

    static void ques20170104() {
        class Obj {
            private String id;
            public Obj(String id) {
                this.id = id;
            }

            @Override
            public boolean equals(Object obj) {
                if (this == obj) return true;

                // may be NullPointerException
                if (obj instanceof Obj && this.id.equals(((Obj) obj).id)) {
                    return true;
                }
                return false;
            }

            @Override
            public int hashCode() {
                return 123;
            }
        }
        Obj obj01 = new Obj("张三"), obj02 = new Obj("李四"), obj03 = new Obj("李四");
        Set<Obj> set = new HashSet<Obj>();
        set.add(obj01);
        set.add(obj02);
        set.add(obj03);
        // set.add(new Obj(null)); // java.lang.NullPointerException

        assert set.size() == 2;
    }

    static void ques20170103() {
        System.out.println("你好".replaceAll("你", "")); // 你
        System.out.println("$好".replaceAll("$", "")); // $你好
    }

    static void ques20161230() {
        /* 数组初始化　*/
//        int array = {1, 2, 3};
        int array2[] = {1,2,3};
        int[] array3 = {1,2,3};
//        int array4[] = new int{1,2,3};
        int array5[] = new int[] {1,2,3};
//        int array = [1,2,3];
    }

    static void ques20161220() {
        // 直接调用构造函数可能不相等
        Integer num1 = new Integer(100);
        Integer num2 = new Integer(100);
        assert num1 != num2;
        assert num1 > num2 == false;
        assert num1 < num2 == false;


        Integer num11 = new Integer(1000);
        Integer num21 = new Integer(1000);
        assert num11 != num21;
        assert num11 > num21 == false;
        assert num11 < num21 == false;

        // 把整数直接赋值给包装类，会调用 Integer.valueOf 方法，在 IntegerCache.low 至 IntegerCache.high 之间的整数
        // 会使用缓存的实例，通过环境参数　java.lang.Integer.IntegerCache.high 配置高位
        // low: -128, high　取 127和配置值的较大者
        Integer c = 100, d = 100;
        assert (c == d) == true;

        Integer e = 1000, f = 1000;
        assert (e == f) == false;

        assert c != num1;
    }

    static void ques20161228_plus_1229() {
        class Student {
            private String stuId;

            public Student(String stuId) {
                super();
                this.stuId = stuId;
            }

            public String getStuId() {
                return stuId;
            }

            public void setStuId(String stuId) {
                this.stuId = stuId;
            }

            @Override
            public boolean equals(Object obj) {
                if (obj instanceof Student) {
                    Student other = (Student) obj;
                    return stuId.equalsIgnoreCase(other.getStuId().trim());
                }
                return false;
            }
        }
        Student stu01 = new Student("001"), stu02 = new Student("001 ");
        List<Student> students = new ArrayList<Student>();
        students.add(stu01);
        students.add(stu02);
        System.out.println(null instanceof Student); // false
        System.out.println(stu01 == null); // false
        System.out.println(stu01 == stu02); // false
        System.out.println(stu01.equals(stu02)); // true
        assert students.size() == 2;
        System.out.println(students.contains(stu01)); // true
        System.out.println(students.contains(stu02)); // NOTICE: false　

        // 分析： contains 内部调用的是 indexof然后是equals
        // 上面定义的　equals　违反了对称性

        Student stu_null = new Student(null);
        students.add(stu_null);
        assert students.size() == 3;

        // java.lang.NullPointerException
        System.out.println(students.contains(stu_null));
    }

    static void ques20161226() {
        System.out.println(Ques1226.num); // 2
    }

    static void ques20161227() {}

    static void ques20161214() {
        // TODO
        final List<String> tickets = new ArrayList<String>();
        for (int i = 0; i < 1000; i++) {
            tickets.add("Ticket#" + i);
        }
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                public void run() {
                    while (true) {
                        try {
                            String e  = tickets.remove(0);
                            System.out.println(Thread.currentThread().getName() + ": Removed #" + e);
                        } catch (IndexOutOfBoundsException e) {
                            break;
                        }
                    }
                }
            }).start();
        }
        /* 出现重复卖票，呵呵
        Thread-0: Removed #Ticket#0
        Thread-4: Removed #Ticket#2
        Thread-1: Removed #Ticket#0
        Thread-0: Removed #Ticket#3
        ...
         */
    }

    static void ques20161201() {
        new Ques1201().sum(1, 1);
    }

    static int ques() {
        System.out.println(Child.UID);
        /*
        static block in  parent
        12
         */

        System.out.println(Arrays.toString(new int[] {1, 2, 3}));
        System.out.println(new Child().UID);
        return 1;
    }

    void helloworld() {

    }
}


class Parent {
    static int UID = 12;
    static {
        System.out.println("static block in  parent");
    }
    protected void method() {
        System.out.println(Parent.class.getName() + "#method");
    }
    final void afinalmethod() {
        System.out.println("it is a final method of Parent");
    }

}

class Child extends Parent {
    static {
        System.out.println("static block in child");
    }

    void test() {
        System.out.println("call final method in parent.");
        afinalmethod();
    }
//    void afinalmethod() {}
}

class Ques0210 {
    public static void change(String str, char[] ch) {
        str = "changed";
        ch[0] = 'C';
    }
}

class Ques0209 {
    public static void add(Integer i) {
        int value = i.intValue();
        value += 10;
        i = new Integer(value);
    }
}
class Ques0123 {
    final int num;
    {
        num = 0;
    }
    public Ques0123() {
        System.out.println(num + 10);
    }
}

class Ques1226 {
    static {num = 1;}
    public static int num = 2;
}

class Ques1227 {
    {
        c = 1;
    }
    int c = 2;
//    int a = b; // 会有编译错误
    int b = 3;
}

class Ques1201 {
    // how to when calling `sum(1, 2)`
    public void sum(int num1, int num2) {
        int temp = num1 + num2;
        System.out.println("without variable arguments");
    }

    public void sum(int num1, int... num2) {
        int temp = num1;
        for (int n: num2) {
            temp += n;
        }
        System.out.println("with variable auguments");
    }
}

class Ques0109<T> {
//    T t = new T(); // compiler error
//    T[] array = new T[3]; // compiler error
    List<T> list = new ArrayList<T>();
}

class Ques0113 {
//    private final String id; // compiler error
    private String id;
    public Ques0113() {
        System.out.println("constructor");
    }

    public void setId(String id) {
        this.id = id;
    }
}

class Person {
    String name;
    public Person(String name) {this.name = name;}

    public void setName(String name) {
        this.name = name;
    }

    static void update(Person p) {
        p = new Person("lisi");
    }
}