package com.sleepycoders.jlib.algorithm.sort;

import java.util.Comparator;

/**
 * SelectionSort Implementation
 * @author Joshua Moody (joshimoo@hotmail.de)
 */
public final class SelectionSort {

    /**
     * Don't let anyone instantiate this class.
     */
    private SelectionSort() {}

    /**
     * SelectionSort is a simple algorithm that partitions the data array into a sorted and unsorted part,
     * Then foreach iteration finds the min Element in the unsorted part and appends it to the sorted part.
     * Uses the natural order for sorting c1.compareTo(c2)
     */
    public static <T extends Comparable<? super T>> void sort(T[] data) {
        sort(data, Comparator.<T>naturalOrder());
    }

    /**
     * SelectionSort is a simple algorithm that partitions the data array into a sorted and unsorted part,
     * Then foreach iteration finds the min Element in the unsorted part and appends it to the sorted part.
     */
    public static <T> void sort(T[] data, Comparator<? super T> cmp) {
        for (int i = 0; i < data.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < data.length ; j++) {
                if (cmp.compare(data[j], data[minIndex]) < 0) {
                    minIndex = j;
                }
            }

            Utility.swap(data, i, minIndex);
        }
    }

    // region Recursive Implementation - package internal, don't use
    /**
     * Recursive SelectionSort Implementation, this is just for demonstration
     * The performance characteristics of the recursive implementation are a lot worse then the iterative version.
     * Even tough the asymptotic complexity stays the same :)
     * Uses the natural order for sorting c1.compareTo(c2)
     * @deprecated use the iterative SelectionSort.sort implementation instead
     */
    @Deprecated
    static <T extends Comparable<? super T>> void sortRec(T[] data) {
        sortRec(data, Comparator.<T>naturalOrder());
    }

    /**
     * Recursive SelectionSort Implementation
     * @deprecated use the iterative SelectionSort.sort implementation instead
     */
    @Deprecated
    static <T> void sortRec(T[] data, Comparator<? super T> cmp) {
        sortRec(data, cmp, 0);
    }

    @Deprecated
    private static <T> void sortRec(T[] data, Comparator<? super T> cmp, int index) {
        // Base Cases - we are outside of our data array
        if (index >= data.length) { return; }
        Utility.swap(data, index, minIndexRec(data, cmp, index));
        sortRec(data, cmp, index + 1);
    }

    @Deprecated
    private static <T> int minIndexRec(T[] data, Comparator<? super T> cmp, int startIndex) {
        return minIndexRec(data, cmp, startIndex, startIndex);
    }

    @Deprecated
    private static <T> int minIndexRec(T[] data, Comparator<? super T> cmp, int i, int min) {
        // Base Cases - we are outside of our data array
        if (i >= data.length) { return min; }
        return cmp.compare(data[i], data[min]) < 0 ? minIndexRec(data, cmp, i + 1, i) : minIndexRec(data, cmp, i + 1, min);
    }
    // endregion
}
