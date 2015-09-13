package com.sleepycoders.jlib.algorithm.sort;

import com.sleepycoders.jlib.util.Arrays;

import java.util.Collections;
import java.util.Comparator;

/**
 * Recursive QuickSort Implementation
 * @author Joshua Moody (joshimoo@hotmail.de)
 */
public final class QuickSort {

    /**
     * Don't let anyone instantiate this class.
     */
    private QuickSort() {}


    /**
     * QuickSort works by recursively partitioning the data
     * So that data[left <= pivot <= right],
     * Since each element will be used as the pivot at least once
     * This guarantees that whole data will be sorted
     * Uses the natural order for sorting c1.compareTo(c2)
     */
    public static <T extends Comparable<? super T>> void sort(T[] data) {
        sort(data, Comparator.<T>naturalOrder());
    }

    /**
     * QuickSort works by recursively partitioning the data
     * So that data[left <= pivot <= right],
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

        // Partition the Array so that low <= mid <= high
        int pivot = partition(data, cmp, low, high);
        sort(data, cmp, low, pivot - 1);
        sort(data, cmp, pivot + 1, high);
    }

    /**
     * partition routine
     * which partitions the data so that [low <= pivot <= high]
     * @param data source and destination
     * @param low lower index inclusive
     * @param high upper index inclusive
     */
    private static <T> int partition(T[] data, Comparator<? super T> cmp, int low, int high) {
        assert data != null && cmp != null;

        // select last element as pivot
        T pivot = data[high];
        int pivotPortion = low;

        for (int i = low; i < high ; i++) {

            // compare each element against pivot to determine,
            // whether they belong left or right of pivot
            if (cmp.compare(data[i], pivot) <= 0) {
                Arrays.swap(data, pivotPortion++, i);
            }
        }

        // Now swap the pivot with the the element
        // immediately after the left partition
        Arrays.swap(data, pivotPortion, high);
        return pivotPortion;
    }
}
