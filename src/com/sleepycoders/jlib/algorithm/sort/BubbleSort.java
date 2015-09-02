package com.sleepycoders.jlib.algorithm.sort;

/**
 * BubbleSort Implementation
 * @author Joshua Moody (joshimoo@hotmail.de)
 * @deprecated Prefer other Sorting Algorithms instead of BubbleSort (Quick,Tim,Merge,Insertion)
 */
public class BubbleSort {

    /**
     * Don't let anyone instantiate this class.
     */
    private BubbleSort() {}


    /**
     * Adaptive BubbleSort, will terminate early if already sorted
     * Runtime will depend on sorted percentage, with worst-case of O(n^2)
     */
    public static <T extends Comparable<? super T>> void sort(T[] data) {

        boolean sorted = false;
        int alreadySorted = 0;

        while(!sorted) {

            // We have a sorted list, unless we had to swap an element
            // This optimization allows the sort to exit early.
            sorted = true;

            for (int i = 1; i < data.length - alreadySorted; i++) {
                if (data[i - 1].compareTo(data[i]) > 0) {
                    Utility.swap(data, i - 1, i);
                    sorted = false;
                }
            }

            // Another optimization, which only looks at the unsorted part
            // We finished this run, so we don't have to touch this element anymore
            alreadySorted++;
        }
    }



    // region Recursive Implementation - package internal, don't use
    /**
     * BubbleSort
     * @deprecated don't use BubbleSort
     */
    @Deprecated
    static <T extends Comparable<? super T>> void sortRec(T[] data) {
        sortRec(data, 1, 0);
    }

    @Deprecated
    private static <T extends Comparable<? super T>> void sortRec(T[] data, int current, int alreadySorted) {
        // continue sorting the current run
        if (current < data.length - alreadySorted) {
            if (data[current - 1].compareTo(data[current]) > 0) { Utility.swap(data, current - 1, current); }
            sortRec(data, current + 1, alreadySorted);
        } else if (alreadySorted < data.length - 1) {
            // Base Cases - we finished sorting, this run, increase sorted partition by 1
            sortRec(data, 1, alreadySorted + 1);
        }

        // Base Case - Array is completely sorted
    }
    // endregion
}
