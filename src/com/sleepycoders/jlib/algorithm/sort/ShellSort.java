package com.sleepycoders.jlib.algorithm.sort;

import com.sleepycoders.jlib.util.Arrays;

import java.util.Comparator;

/**
 * ShellSort Implementation
 * @author Joshua Moody (joshimoo@hotmail.de)
 */
public final class ShellSort {

    /**
     * Don't let anyone instantiate this class.
     */
    private ShellSort() {}

    /**
     * Marcin Ciura's gap sequence
     */
    private static final Integer[] defaultGapSequence = new Integer[] { 701, 301, 132, 57, 23, 10, 4, 1 };

    /**
     * ShellSort runs multiple InsertionSorts with a custom gap between elements
     * With a a final pass with a gap size of 1
     * This takes advantage of InsertionSorts adaptive nature on partial sorted data
     * Marcin Ciura's gap sequence is used by default
     */
    public static <T extends Comparable<? super T>> void sort(T[] data) {
        sort(data, defaultGapSequence, Comparator.<T>naturalOrder());
    }

    /**
     * ShellSort runs multiple InsertionSorts with a custom gap between elements
     * With a a final pass with a gap size of 1
     * This takes advantage of InsertionSorts adaptive nature on partial sorted data
     * @param gaps must be descendingly sorted and contain the element 1
     */
    public static <T extends Comparable<? super T>> void sort(T[] data, Integer[] gaps) {
        sort(data, gaps, Comparator.<T>naturalOrder());
    }


    /**
     * ShellSort runs multiple InsertionSorts with a custom gap between elements
     * With a a final pass with a gap size of 1
     * This takes advantage of InsertionSorts adaptive nature on partial sorted data
     * Marcin Ciura's gap sequence is used by default
     */
    public static <T> void sort(T[] data, Comparator<? super T> cmp) {
        sort(data, defaultGapSequence, cmp);
    }

    /**
     * ShellSort runs multiple InsertionSorts with a custom gap between elements
     * With a a final pass with a gap size of 1
     * This takes advantage of InsertionSorts adaptive nature on partial sorted data
     * @param gaps must be descendingly sorted and contain the element 1
     */
    public static <T> void sort(T[] data, Integer[] gaps, Comparator<? super T> cmp) {
        assert data != null && cmp != null && gaps != null && gaps.length > 0;
        if (!java.util.Arrays.asList(gaps).contains(1)) {
            throw new IllegalArgumentException("The gap-sequence needs to contain, 1 as the last element");
        }

        // Make sure that the gaps are decreasingly sorted
        java.util.Arrays.sort(gaps, Comparator.<Integer>reverseOrder());

        for (int gap : gaps) {

            // Insertion Sort, with a specified gap between elements
            // TODO: consider refactoring this into InsertionSort
            for (int i = gap; i < data.length; i++) {
                // Swap if left side is bigger, exits the loop if it's not
                for (int j = i; j >= gap && (cmp.compare(data[j - gap], data[j]) > 0); j -= gap) {
                    Arrays.swap(data, j - gap, j);
                }
            }
        }
    }

}
