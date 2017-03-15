package hello;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by min on 17-3-13.
 */
public class JavaBestPractice {

    @Test
    public void sort() {
        // 1. 使用 Comparator 替代 Comparable 来实现无侵入的定制排序
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1001, "zhao"));
        employees.add(new Employee(1010, "qian"));
        employees.add(new Employee(1006, "sun"));
        employees.add(new Employee(1004, "li"));
        employees.add(new Employee(1005, "wang"));
        employees.add(new Employee(1003, "wang"));

//        Collections.sort(employees, Comparator.comparingInt(Employee::getId)); // or
        Collections.sort(employees, (source, dest)->source.id - dest.id);
        assert "qian".equals(employees.get(employees.size() - 1).getName());

        Collections.sort(employees, Collections.reverseOrder(Comparator.comparingInt(Employee::getId)));
        assert "zhao".equals(employees.get(employees.size() - 1).getName());

//        Collections.sort(employees, (src, dest)->src.getName().compareTo(dest.getName())); // or
        Collections.sort(employees, Comparator.comparing(Employee::getName));

        assert "zhao".equals(employees.get(employees.size() - 1).getName());

        Collections.sort(employees, Comparator.comparing(Employee::getName).thenComparing(Employee::getId));

        assert "zhao".equals(employees.get(employees.size() - 1).getName());

//        for (int i = 0; i < employees.size(); i++) {
//            System.out.println(employees.get(i));
//        }
    }

    @Test
    public void collec() {
        List<String> list1 = new ArrayList<>();
        list1.add("A");
        list1.add("B");

        List<String> list11 = new ArrayList<>(list1);
        List<String> list21 = new ArrayList<>(list1);
        List<String> list31 = new ArrayList<>(list1);
        List<String> list41 = new ArrayList<>(list1);

        List<String> list2 = new ArrayList<>();
        list2.add("C");
        list2.add("B");

        assert list1.addAll(list2); //changed,　并集
        assert "[A, B, C, B]".equals(list1.toString());

        assert list11.retainAll(list2); // changed, 交集
        assert list11.size() == 1;
        assert "[B]".equals(list11.toString());

        assert list21.removeAll(list2); // changed, 差集
        assert list2.size() == 2;
        assert "[A]".equals(list21.toString());

        list31.removeAll(list2); // 先删除交集
        list31.addAll(list2);      // 再加入
        assert list31.size() == 3; // 无重复的并集（加入list31和list2中无重复元素）
        assert "[A, C, B]".equals(list31.toString());

        List<String> other = new ArrayList<>();
        other.add("C");
        other.add("B");
        other.add("B");
        list41.removeAll(other); // A,B - C,B,B = A
        list41.addAll(other);       // A + C,B,B = A,C,B,B
        assert list41.size() == 4;
        assert "[A, C, B, B]".equals(list41.toString());

        //　如果不保留原来集合和目标集合中所有的重复元素，需要使用 HashSet
    }
}
class Employee {
    int id;
    String name;
//
//    public Employee() {
//    }

    public Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
