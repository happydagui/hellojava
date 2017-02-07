package hello.patterns;

/**
 * Created by min on 17-1-16.
 */

public class MySingleton {
    public static void main(String[] args) {
        TheSingleton s = TheSingleton.INSTANCE;
        s.doSomething();

        TheSingleton s2 = TheSingleton.INSTANCE;

        System.out.println(s == s2);
    }
}
enum TheSingleton {
    INSTANCE;

    public void doSomething() {
        System.out.println("it is another thing.");
    }
}
