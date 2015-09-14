package com.sleepycoders.jlib.algorithm.sort;

import com.sleepycoders.jlib.util.Arrays;

import java.util.Comparator;

/**
 * Recursive QuickSort Implementation
 * @author Joshua Moody (joshimoo@hotmail.de)
 */
public final class QuickSort3Way {

    /**
     * Don't let anyone instantiate this class.
     */
    private QuickSort3Way() {}


    /**
     * QuickSort3Way works like a regular @see QuickSort
     * except with a partition function that partitions the data, so that
     * data[low...left-1] < pivot = data[left...right] < data[right+1...high]
     * Since each element will be used as the pivot at least once
     * This guarantees that whole data will be sorted
     * Uses the natural order for sorting c1.compareTo(c2)
     */
    public static <T extends Comparable<? super T>> void sort(T[] data) {
        sort(data, Comparator.<T>naturalOrder());
    }

    /**
     * QuickSort3Way works like a regular @see QuickSort
     * except with a partition function that partitions the data, so that
     * data[low...left-1] < pivot = data[left...right] < data[right+1...high]
     * Since each element will be used as the pivot at least once
     * This guarantees that whole data will be sorted
     */
    public static <T> void sort(T[] data, Comparator<? super T> cmp) {
        sort(data, cmp, 0, data.length - 1);
    }

    /**
     * QuickSort the area between low (inclusive) and high (inclusive)
     * @param data source and destination
     * @param low lower index inclusive
     * @param high upper index inclusive
     */
    public static <T> void sort(T[] data, Comparator<? super T> cmp, int low, int high) {
        assert data != null && cmp != null && low >= 0 && high < data.length;

        // Base Case - 1 Element List is always sorted
        if (low >= high) { return; }

        // Partition the Array so that low < left <= right < high
        T pivot = data[high];
        int left = low;
        int right = high;
        int i = low;
        while (i <= right) {
            int c = cmp.compare(data[i], pivot);
            if (c < 0) { Arrays.swap(data, left++, i++); }
            else if (c > 0) { Arrays.swap(data, i, right--); }
            else { i++; }
        }

        // Now sor the left and right partitions recursively
        // data[low...left-1] < pivot = data[left...right] < data[right+1...high].
        sort(data, cmp, low, left - 1);
        sort(data, cmp, right + 1, high);
    }
}
