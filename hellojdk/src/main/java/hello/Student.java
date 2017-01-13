package hello;

import java.io.*;
import java.util.List;

/**
 * Created by min on 17-1-11.
 */
public class Student implements Serializable {
    String name;
    int age;
    double score;

    public Student(String name, int age, double score) {
        this.name = name;
        this.age = age;
        this.score =score;
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

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public static void main(String[] args) throws IOException {
        Student source = new Student("xiaomin", 38, 191.0);

        // target file lies in <project_root>/student.dat
        ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("student.dat")));
        try {
            out.writeObject(source);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            out.close();
        }
        System.out.println("file written finished");

        ObjectInputStream oin = new ObjectInputStream(new BufferedInputStream(new FileInputStream("student.dat")));

        Student read = null;
        try {
            read = (Student) oin.readObject();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        finally {
            oin.close();
        }

        System.out.println("source == read is " + (source == read)); // false
        System.out.println("source name equals read name is " + source.getName().equals(read.getName()));  // true
    }

    public static void writeStudents(List<Student> students, String filename) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(filename)));
        try {
            out.writeInt(students.size());
            for (Student student : students) {
                out.writeObject(student);
            }
        } finally {
            out.close();
        }
    }
}
