package com.sleepycoders.jlib.algorithm.sort;

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
     */
    public static <T extends Comparable<? super T>> void sort(T[] data) {
        for (int i = 1; i < data.length; i++) {
            T x = data[i];
            int j = i;

            // shift array right to make space for x
            while (j > 0 && data[j - 1].compareTo(x) > 0) {
                data[j] = data[j - 1];
                j--;
            }

            // Insert element into correct position
            data[j] = x;
        }
    }

    /**
     * Swaps elements till target is in the right slot, instead of shifting
     * Nicer code, but more overhead then shifting
     * @deprecated use shift based implementation instead
     */
    @Deprecated
    static <T extends Comparable<? super T>> void sortViaSwapping(T[] data) {
        for (int i = 1; i < data.length; i++) {
            for (int j = i; j > 0 && data[j - 1].compareTo(data[j]) > 0 ; j--) {
                Utility.swap(data, j-1, j);
            }
        }
    }

    // region Recursive Implementation - package internal, don't use
    /**
     * Recursive InsertionSort Implementation, this is just for demonstration
     * Doing a recursive InsertionSort on an array based data set makes no sense
     * @deprecated use the iterative InsertionSort.sort implementation instead
     */
    @Deprecated
    static <T extends Comparable<? super T>> void sortRec(T[] data) {
        sortRec(data, 1);
    }

    @Deprecated
    private static <T extends Comparable<? super T>> void sortRec(T[] data, int current) {
        // Base Case - we are outside the array
        if (current >= data.length) { return; }
        insertRec(data, current, current - 1);
        sortRec(data, current + 1);
    }

    @Deprecated
    private static <T extends Comparable<? super T>> void insertRec(T[] data, int elem, int current) {
        // Base Case - we are outside the array
        if (current < 0) { return; }

        if (data[current].compareTo(data[elem]) > 0) {
            Utility.swap(data, current, elem);
            insertRec(data, current, current - 1);
        }
    }
    // endregion
}
