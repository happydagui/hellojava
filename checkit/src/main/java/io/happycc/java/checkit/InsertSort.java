package io.happycc.java.checkit;

import java.util.Arrays;

/**
 * Created by min on 17-3-3.
 */
public class InsertSort {
    public static void sort(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            // 将 a[i] 插入到 a[0] - a[i-1] 的已排序数组中
            for (int j = i; j > 0 && a[j - 1].compareTo(a[j]) > 0; j--) {
                exch(a, j, j - 1); // 前面大，就交换，一直到前面的小就结束
            }
        }
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable s = a[i];
        a[i] = a[j];
        a[j] = s;
    }

    public static void main(String[] args) {
        String[] source = new String[]{"K", "R", "A", "T", "E", "L", "E", "P", "U", "I", "M", "Q", "C", "X", "O", "S"};
        sort(source);
        System.out.println(Arrays.toString(source));
    }


}
