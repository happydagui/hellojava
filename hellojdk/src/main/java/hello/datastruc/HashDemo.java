package hello.datastruc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

/**
 * 1. Key 相同的含义， key 对象equals方法返回为真，hash(key)也是相等
 * 2. HashMap vs HashTable:
 * 1) a null key is permited for hashmap, but hashtable
 * 2) hashtable is thread-safe, but hashmap
 * 3) intial capacity： 11 (hashtable) vs 16 (hashmap)
 * Created by min on 17-1-16.
 */
public class HashDemo {
    static final Logger logger = LoggerFactory.getLogger(HashDemo.class);

    public static void main(String[] args) {
        testHashMap();
//        testHashTable();
    }

    public static void testHashMap() {
        logger.info("Calling ({})", "testHashMap");
        Map map = new HashMap();
        Person p1 = new Person("min",20);
        Person p2 = new Person("yaping", 20);

        map.put(p1, "wuhan");
        map.put(p2, "changsha");
        map.put(null, "this is a null value.");

        System.out.println(map.get(p1) == "wuhan"); // true
        System.out.println(map.get(p2) == "changsha");  // true

        p1.setName("xiaomin");
        // Person 对象是基于name 产生hashcode，修改了name属性，用原来的key就找不到了
        System.out.println(map.containsKey(p1)); // false

        Person p1_1 = new Person("xiaomin", 48);
        System.out.println(p1.hashCode());
        System.out.println(p1_1.hashCode());

        // 未 put 前，同样name的两个person不能get，说明
        // 首先 key 要 equal， 且 hashcode 要相等
        System.out.println(map.containsKey(p1_1)); // false

        map.put(p1_1, "wuhan, hubei");
        System.out.println(map.get(p1_1) == "wuhan, hubei"); // true

        p2.setAge(21);
        System.out.println(map.containsKey(p2)); // true
    }

    public static void testHashTable() {
        Map map = new Hashtable();
        Person p1 = new Person("min",20);
        Person p2 = new Person("yaping", 20);

        map.put(p1, "wuhan");
        map.put(p2, "changsha");

        // java.lang.NullPointerException
        map.put(null, "this is a null value.");

        System.out.println(map.get(p1) == "wuhan"); // true
        System.out.println(map.get(p2) == "changsha");  // true

        p1.setName("xiaomin");
        // Person 对象是基于name 产生hashcode，修改了name属性，用原来的key就找不到了
        System.out.println(map.containsKey(p1)); // false

        Person p1_1 = new Person("xiaomin", 48);
        System.out.println(p1.hashCode());
        System.out.println(p1_1.hashCode());

        // 未 put 前，同样name的两个person不能get，说明
        // 首先 key 要 equal， 且 hashcode 要相等
        System.out.println(map.containsKey(p1_1)); // false

        map.put(p1_1, "wuhan, hubei");
        System.out.println(map.get(p1_1) == "wuhan, hubei"); // true

        p2.setAge(21);
        System.out.println(map.containsKey(p2)); // true
    }
}

class Person {
    String name;
    int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    /**
     * This is typically implemented by converting the internal
     * address of the object into an integer
     * 默认 hashcode 是采用对象的内部地址
     * @see Object
     * @return
     */
    @Override
    public int hashCode() {
        return this.name.hashCode();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
