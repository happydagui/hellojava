package hello.hotspot;

/**
 * Created by min on 17-2-8.
 * 使用　hsdb 查看对象位置
 * cd src/main/java
 * jdb -XX:+UserSerialGC -Xmx10m
 * > stop in hello.hotspot.HSDBMain.fn
 * > run hello.hotspot.HSDBMain
 * > next
 *
 * （一个就可以了，再输入几次next程序就退出了）
 *
 * 启动另一个终端
 * sudo java -cp .:$JAVA_HOME/lib/sa-jdi.jar sun.jvm.hotspot.HSDB
 */
class Test {
    static String version = "1.0";
    String name;
    int id;

    Test(String name, int id) {
        System.out.println("id: " + id + ", name=" + name);
        this.name = name;
        this.id = id;
    }

    static void fn() {

    }
    void fn2() {}
}
public class HSDBMain {
    static Test t1 = new Test("java", 1); // 1
    private Test t2 = new Test("java", 2); // 2

    public void fn() {
        Test t3 = new Test("java",  3); // 3
    }

    public static void main(String[] args) {
        new HSDBMain().fn();
    }
}
