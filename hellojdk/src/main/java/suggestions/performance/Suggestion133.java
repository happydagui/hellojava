package suggestions.performance;

/**
 * Created by min on 17-3-14.
 * Notes: 若非必要，不要使用克隆
 */
public class Suggestion133 {
    public static void main(String[] args) {
        final int total = 10 * 10000;
        int loop = 0;
        long start = System.nanoTime();
        for (loop = 0; loop < total; loop++) {
            new Apple();
        }
        long mid = System.nanoTime();

        Apple apple = new Apple();
        for (loop = 0; loop < total; loop++) {
            apple.clone();
        }
        long last = System.nanoTime();

        System.out.println("With new: " + (mid - start));
        System.out.println("With clone: " + (last - mid));
    }

    static class Apple implements Cloneable {
        @Override
        protected Object clone() {
            try {
                return super.clone();
            } catch (CloneNotSupportedException ex) {
                throw new Error();
            }
        }
    }
}
