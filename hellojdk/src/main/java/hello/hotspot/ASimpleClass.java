package hello.hotspot;

/**
 * Created by min on 17-2-7.
 * 用此类的 .class 文件作为来源分析 class 文件结构
 */
public class ASimpleClass {
    public static final String TAG = "helloworld";
    String name = "unknown";

    public ASimpleClass() {}

    public ASimpleClass(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public void sayHello() {
        System.out.println("Hello " + this.name);
    }

    public static void main(String[] args) {
        ASimpleClass inst = new ASimpleClass("foo");
        inst.sayHello();
    }
}
