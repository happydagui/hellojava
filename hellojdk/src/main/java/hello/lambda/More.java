package hello.lambda;

import org.junit.Test;

/**
 * Created by min on 17-3-7.
 * 要点：
 * 1. 使用构造函数作为接口传递
 * 2. 匿名函数包创造新的作用域，可以访问类的成员和作用域内的变量，但不能修改
 */
public class More {
    private String desc = "More";
    @Test
    public void test1() {
        // 使用构造函数传递接口
        Factory<Dog> factory = Dog::new;
        Dog dog = factory.create("I am a dog", 10);

        // 使用传统方法
        final String foo = "bar";
        Factory<Dog> factory1 = new Factory<Dog>() {
            @Override
            public Dog create(String name, int age) {
                // 可以访问类的成员
                System.out.println(desc);

                // 可以访问函数体内的变量，编译器会自动为变量添加 final 修饰符
                System.out.println(foo);
                // 修改变量是不允许的
                // foo = foo.replace("bar", "balabala");
                return new Dog(name, age);
            }
        };
        Dog dog1 = factory1.create("another dog", 12);
    }
}

class Animal {
    private String name;
    private int age;

    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }
    public void behavior() {}
}

class Bird extends Animal {
    public Bird(String name, int age) {
        super(name, age);
    }

    @Override
    public void behavior() {
        System.out.println("fly fly fly");
    }
}

class Dog extends Animal {
    public Dog(String name, int age) {
        super(name, age);
    }

    @Override
    public void behavior() {
        System.out.println("wao wao wao");
    }
}

@FunctionalInterface
interface Factory<T extends Animal> {
    T create(String name, int age);
}
