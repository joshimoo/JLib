package com.sleepycoders.jlib.algorithm.sort;

import com.sleepycoders.jlib.util.Arrays;

import java.util.Comparator;

/**
 * CocktailSort Implementation
 * @author Joshua Moody (joshimoo@hotmail.de)
 */
public final class CocktailSort {

    /**
     * Don't let anyone instantiate this class.
     */
    private CocktailSort() {}


    /**
     * Cocktail Sort improves on BubbleSorts practical performance,
     * By sorting both ends of the data (low-sorted,unsorted,high-sorted)
     * The worst-case runtime is still O(n^2)
     * Uses the natural order for sorting c1.compareTo(c2)
     */
    public static <T extends Comparable<? super T>> void sort(T[] data) {
        sort(data, Comparator.<T>naturalOrder());
    }

    /**
     * Cocktail Sort improves on BubbleSorts practical performance,
     * By sorting both ends of the data (low-sorted,unsorted,high-sorted)
     * The worst-case runtime is still O(n^2)
     */
    public static <T> void sort(T[] data, Comparator<? super T> cmp) {
        assert data != null && cmp != null;
        boolean sorted = false;
        int low = 0;
        int high = data.length - 1;

        while(!sorted) {

            // We have a sorted list, unless we had to swap an element
            // This optimization allows the sort to exit early.
            sorted = true;

            for (int i = low; i < high; i++) {
                if (cmp.compare(data[i], data[i + 1]) > 0) {
                    Arrays.swap(data, i, i + 1);
                    sorted = false;
                }
            }

            // We can exit early, if our up pass did not need to swap
            if(sorted) { return; }

            // Adjust upper bound
            high--;

            // Now sort down one element
            for (int i = high; i > low; i--) {
                if (cmp.compare(data[i], data[i - 1]) < 0) {
                    Arrays.swap(data, i, i - 1);
                    sorted = false;
                }
            }

            // Adjust lower bound
            low++;
        }
    }
}
