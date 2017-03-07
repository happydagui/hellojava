package io.happycc.java.checkit;

import java.util.Arrays;

/**
 * Created by min on 17-3-3.
 */
public class QuickSort {
    public static void sort(Comparable[] a) {
        // shuffle
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (lo >= hi) return;
        int j = partion(a, lo, hi); // 选择一个位置，分成左右两个子序列，左边都比中间小，右边都比中间大
        sort(a, 0, j - 1);
        sort(a, j + 1, hi);
    }

    private static int partion(Comparable[] a, int lo, int hi) {
        int i = lo, j = hi + 1;
        Comparable v = a[lo]; // 这里是可以斟酌的地方，暂时选择第一个
        while (true) {
            while (a[++i].compareTo(v) < 0) {
                if (i == hi) break;
            }
            while (a[--j].compareTo(v) > 0) {
                if (j == lo) break;
            }
            if (i >= j) break;
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable s = a[i];
        a[i] = a[j];
        a[j] = s;
    }

    public static void main(String[] args) {
        String[] source = new String[] {"K", "R", "A", "T", "E", "L", "E", "P", "U", "I", "M", "Q", "C", "X", "O", "S"};
        sort(source);
        System.out.println(Arrays.toString(source));
    }


}
