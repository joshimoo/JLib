package com.sleepycoders.jlib.algorithm.sort;

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
     */
    public static <T extends Comparable<? super T>> void sort(T[] data) {
        for (int i = 0; i < data.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < data.length ; j++) {
                if (data[j].compareTo(data[minIndex]) < 0) {
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
     * @deprecated use the iterative SelectionSort.sort implementation instead
     */
    @Deprecated
    static <T extends Comparable<? super T>> void sortRec(T[] data) {
        sortRec(data, 0);
    }

    @Deprecated
    private static <T extends Comparable<? super T>> void sortRec(T[] data, int index) {
        // Base Cases - we are outside of our data array
        if (index >= data.length) { return; }
        Utility.swap(data, index, minIndexRec(data, index));
        sortRec(data, index + 1);
    }

    @Deprecated
    private static <T extends Comparable<? super T>> int minIndexRec(T[] data, int startIndex) {
        return minIndexRec(data, startIndex, startIndex);
    }

    @Deprecated
    private static <T extends Comparable<? super T>> int minIndexRec(T[] data, int i, int min) {
        // Base Cases - we are outside of our data array
        if (i >= data.length) { return min; }
        return data[i].compareTo(data[min]) < 0 ? minIndexRec(data, i + 1, i) : minIndexRec(data, i + 1, min);
    }
    // endregion
}
