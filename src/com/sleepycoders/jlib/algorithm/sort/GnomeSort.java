package com.sleepycoders.jlib.algorithm.sort;

import com.sleepycoders.jlib.util.Arrays;

import java.util.Comparator;

/**
 * GnomeSort Implementation
 * @author Joshua Moody (joshimoo@hotmail.de)
 */
public final class GnomeSort {

    /**
     * Don't let anyone instantiate this class.
     */
    private GnomeSort() {}


    /**
     * GnomeSort is another BubbleSort relative
     * It works, by partitioning the data, into a [partial sorted | unsorted set]
     * After having gone through the whole data, the partial sorted set is guaranteed to be totally sorted
     * Uses the natural order for sorting c1.compareTo(c2)
     */
    public static <T extends Comparable<? super T>> void sort(T[] data) {
        sort(data, Comparator.<T>naturalOrder());
    }

    /**
     * GnomeSort is another BubbleSort relative
     * It works, by partitioning the data, into a [partial sorted | unsorted set]
     * After having gone through the whole data, the partial sorted set is guaranteed to be totally sorted
     * The worst-case runtime is O(n^2)
     */
    public static <T> void sort(T[] data, Comparator<? super T> cmp) {
        assert data != null && cmp != null;

        int pos = 1;
        while(pos < data.length) {

            // First find, the next item to swap
            if (cmp.compare(data[pos - 1], data[pos]) <= 0) {
                pos++;
            } else {
                // Then push it down, into it's correct spot
                Arrays.swap(data, pos - 1, pos);
                if (pos > 1) { pos--; }
            }
        }
    }
}
