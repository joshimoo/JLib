package com.sleepycoders.jlib.algorithm.sort;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Recursive MergeSort Implementation with an iterative merge routine
 * That requires O(n) extra Memory
 * @author Joshua Moody (joshimoo@hotmail.de)
 */
public final class MergeSort {

    /**
     * Don't let anyone instantiate this class.
     */
    private MergeSort() {}


    /**
     * merge Sorts the complete data Array
     * uses a merge Routine with O(n) extra memory
     * Runtime Worst-Case: O(n*log(n))
     * Uses the natural order for sorting c1.compareTo(c2)
     */
    public static <T extends Comparable<? super T>> void sort(T[] data) {
        sort(data, Comparator.<T>naturalOrder());
    }

    /**
     * merge Sorts the complete data Array
     * uses a merge Routine with O(n) extra memory
     * Runtime Worst-Case: O(n*log(n))
     */
    public static <T> void sort(T[] data, Comparator<? super T> cmp) {
        sort(data, cmp, 0, data.length - 1);
    }

    /**
     * merge Sort the area between low (inclusive) and high (inclusive)
     * @param data source and destination
     * @param low lower index inclusive
     * @param high upper index inclusive
     */
    public static <T> void sort(T[] data, Comparator<? super T> cmp, int low, int high) {
        assert data != null && cmp != null;

        // Base Case - 1 Element List is always sorted
        if (low >= high) { return; }
        int mid = low + (high - low) / 2;
        sort(data, cmp, low, mid);
        sort(data, cmp, mid + 1, high);
        merge(data, cmp, low, mid, high);
    }

    /**
     * merge Routine, with O(n) extra memory
     * Left Segment = low to mid;
     * Right Segment = mid + 1 to high;
     * @param data source and destination
     * @param low lower index inclusive
     * @param mid mid index part of lower segment inclusive
     * @param high upper index inclusive
     */
    private static <T> void merge(T[] data, Comparator<? super T> cmp, int low, int mid, int high) {
        assert data != null && cmp != null;

        T[] left = Arrays.copyOfRange(data, low, mid + 1);
        T[] right = Arrays.copyOfRange(data, mid + 1, high + 1);

        for (int i = low, l = 0, r = 0; i <= high; i++) {
            if (l < left.length && r < right.length) {
                if (cmp.compare(left[l], right[r]) > 0) { data[i] = right[r++]; }
                else { data[i] = left[l++]; }
            } else if (l < left.length) {
                data[i] = left[l++];
            } else if(r < right.length) {
                data[i] = right[r++];
            }
        }
    }
}
