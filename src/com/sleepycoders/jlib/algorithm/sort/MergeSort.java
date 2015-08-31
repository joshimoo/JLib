package com.sleepycoders.jlib.algorithm.sort;

import java.util.Arrays;

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
     */
    public static <T extends Comparable<? super T>> void sort(T[] data) {
        sort(data, 0, data.length - 1);
    }

    /**
     * merge Sort the area between low (inclusive) and high (inclusive)
     * @param data source and destination
     * @param low lower index inclusive
     * @param high upper index inclusive
     */
    public static <T extends Comparable<? super T>> void sort(T[] data, int low, int high) {
        // Base Case - 1 Element List is always sorted
        if (low >= high) { return; }
        int mid = low + (high - low) / 2;
        sort(data, low, mid);
        sort(data, mid + 1, high);
        merge(data, low, mid, high);
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
    private static <T extends Comparable<? super T>> void merge(T[] data, int low, int mid, int high) {
        T[] left = Arrays.copyOfRange(data, low, mid + 1);
        T[] right = Arrays.copyOfRange(data, mid + 1, high + 1);

        for (int i = low, l = 0, r = 0; i <= high; i++) {
            if (l < left.length && r < right.length) {
                if (left[l].compareTo(right[r]) > 0) { data[i] = right[r++]; }
                else { data[i] = left[l++]; }
            } else if (l < left.length) {
                data[i] = left[l++];
            } else if(r < right.length) {
                data[i] = right[r++];
            }
        }
    }
}
