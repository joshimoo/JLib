package com.sleepycoders.jlib.algorithm.sort;

import com.sleepycoders.jlib.util.Arrays;

import java.util.Comparator;

/**
 * InsertionSort Implementation
 * @author Joshua Moody (joshimoo@hotmail.de)
 */
public final class InsertionSort {

    /**
     * Don't let anyone instantiate this class.
     */
    private InsertionSort() {}

    /**
     * InsertionSort partitions the data array into a sorted and unsorted part,
     * Then foreach iteration takes the next elem from the unsorted part and
     * Inserts that element into the correct spot in the sorted part
     * Uses the natural order for sorting c1.compareTo(c2)
     */
    public static <T extends Comparable<? super T>> void sort(T[] data) {
        sort(data, Comparator.<T>naturalOrder());
    }

    /**
     * InsertionSort partitions the data array into a sorted and unsorted part,
     * Then foreach iteration takes the next elem from the unsorted part and
     * Inserts that element into the correct spot in the sorted part
     */
    public static <T> void sort(T[] data, Comparator<? super T> cmp) {
        for (int i = 1; i < data.length; i++) {
            T x = data[i];
            int j = i;

            // shift array right to make space for x
            while (j > 0 && cmp.compare(data[j - 1], x) > 0) {
                data[j] = data[j - 1];
                j--;
            }

            // Insert element into correct position
            data[j] = x;
        }
    }

    // region SwapBased Implementation - package internal, don't use

    /**
     * Swaps elements till target is in the right slot, instead of shifting
     * Nicer code, but more overhead then shifting
     * Uses the natural order for sorting c1.compareTo(c2)
     * @deprecated use shift based implementation instead
     */
    @Deprecated
    static <T extends Comparable<? super T>> void sortViaSwapping(T[] data) {
        sortViaSwapping(data, Comparator.<T>naturalOrder());
    }

    /**
     * Swaps elements till target is in the right slot, instead of shifting
     * Nicer code, but more overhead then shifting
     * @deprecated use shift based implementation instead
     */
    @Deprecated
    static <T> void sortViaSwapping(T[] data, Comparator<? super T> cmp) {
        for (int i = 1; i < data.length; i++) {
            for (int j = i; j > 0 && cmp.compare(data[j - 1], data[j]) > 0 ; j--) {
                Arrays.swap(data, j - 1, j);
            }
        }
    }

    // endregion

    // region Recursive Implementation - package internal, don't use
    /**
     * Recursive InsertionSort Implementation, this is just for demonstration
     * Doing a recursive InsertionSort on an array based data set makes no sense
     * Uses the natural order for sorting c1.compareTo(c2)
     * @deprecated use the iterative InsertionSort.sort implementation instead
     */
    @Deprecated
    static <T extends Comparable<? super T>> void sortRec(T[] data) {
        sortRec(data, Comparator.<T>naturalOrder());
    }

    /**
     * Recursive InsertionSort Implementation, this is just for demonstration
     * Doing a recursive InsertionSort on an array based data set makes no sense
     * @deprecated use the iterative InsertionSort.sort implementation instead
     */
    @Deprecated
    static <T> void sortRec(T[] data, Comparator<? super T> cmp) {
        sortRec(data, cmp, 1);
    }

    @Deprecated
    private static <T> void sortRec(T[] data, Comparator<? super T> cmp, int current) {
        // Base Case - we are outside the array
        if (current >= data.length) { return; }
        insertRec(data, cmp, current, current - 1);
        sortRec(data, cmp, current + 1);
    }

    @Deprecated
    private static <T> void insertRec(T[] data, Comparator<? super T> cmp, int elem, int current) {
        // Base Case - we are outside the array
        if (current < 0) { return; }

        if (cmp.compare(data[current], data[elem]) > 0) {
            Arrays.swap(data, current, elem);
            insertRec(data, cmp, current, current - 1);
        }
    }
    // endregion
}
