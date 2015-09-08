package com.sleepycoders.jlib.algorithm.sort;

import com.sleepycoders.jlib.util.Arrays;

import java.util.Comparator;

/**
 * CombSorts basic idea is to eliminate turtles, or small values near the end of the list,
 * The shrink factor has a great effect on the efficiency of comb sort
 * The default shrink factor is 1.3f which was empirically determined to be the best
 * For a similar adaptation but for InsertionSort @see ShellSort
 * @author Joshua Moody (joshimoo@hotmail.de)
 */
public final class CombSort {

    /**
     * Don't let anyone instantiate this class.
     */
    private CombSort() {}


    /**
     * Lacey and Box empirically found (by testing Combsort on over 200,000 random lists)
     * the shrink factor of 1.3 to be the best.
     */
    private static final float defaultShrinkFactor = 1.3f;


    /**
     * CombSorts basic idea is to eliminate turtles, or small values near the end of the list
     * By utilizing BubbleSort with different gap sizes, with a final pass with a gap of 1
     * This takes advantage of BubbleSorts adaptive nature on partial sorted data
     * Uses the natural order for sorting c1.compareTo(c2)
     */
    public static <T extends Comparable<? super T>> void sort(T[] data) {
        sort(data, defaultShrinkFactor, Comparator.<T>naturalOrder());
    }

    /**
     * CombSorts basic idea is to eliminate turtles, or small values near the end of the list
     * By utilizing BubbleSort with different gap sizes, with a final pass with a gap of 1
     * This takes advantage of BubbleSorts adaptive nature on partial sorted data
     * Uses the natural order for sorting c1.compareTo(c2)
     * @param shrink he shrink factor has a great effect on the efficiency of comb sort
     */
    public static <T extends Comparable<? super T>> void sort(T[] data, float shrink) {
        sort(data, shrink, Comparator.<T>naturalOrder());
    }

    /**
     * CombSorts basic idea is to eliminate turtles, or small values near the end of the list
     * By utilizing BubbleSort with different gap sizes, with a final pass with a gap of 1
     * This takes advantage of BubbleSorts adaptive nature on partial sorted data
     */
    public static <T> void sort(T[] data, Comparator<? super T> cmp) {
        sort(data, defaultShrinkFactor, cmp);
    }

    /**
     * CombSorts basic idea is to eliminate turtles, or small values near the end of the list
     * By utilizing BubbleSort with different gap sizes, with a final pass with a gap of 1
     * This takes advantage of BubbleSorts adaptive nature on partial sorted data
     * @param shrink he shrink factor has a great effect on the efficiency of comb sort
     */
    public static <T> void sort(T[] data, float shrink, Comparator<? super T> cmp) {
        assert data != null && cmp != null && shrink > 0;
        int gap = data.length;
        boolean sorted = false;

        while (gap > 1 || !sorted) {
            // Each Iteration the gap will decrease by the shrink factor
            // The first iteration, the gap will be data.size / shrink
            // Which leads to this formula: data.size / (shrink ^ i)
            gap = (int)(gap / shrink);
            if (gap < 1) { gap = 1; }

            // We have a sorted list, unless we had to swap an element
            // This optimization allows the sort to exit early.
            sorted = true;

            for (int i = 0; i + gap < data.length; i++)  {
                if (cmp.compare(data[i], data[i + gap]) > 0) {
                    Arrays.swap(data, i, i + gap);
                    sorted = false;
                }
            }
        }
    }
}
