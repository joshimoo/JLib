package com.sleepycoders.jlib.algorithm.sort;

import java.util.IntSummaryStatistics;
import java.util.stream.IntStream;

/**
 * CountingSort implementation, which also works with negative numbers
 * @author Joshua Moody (joshimoo@hotmail.de)
 */
public final class CountingSort {
    public static final int MAX_RANGE = (int) Math.pow(2, 24); // 64 mb of memory

    /**
     * Don't let anyone instantiate this class.
     */
    private CountingSort() {}

    /**
     * Sorts integers, linearly by counting how many instances there are of each.
     * maximum range = 2^24
     * worst case memory: 2^24 * size(int) ~> 64 mb
     */
    public static void sort(int[] data) {
        assert data != null;
        IntSummaryStatistics stats = IntStream.of(data).summaryStatistics();
        sort(data, stats.getMin(), stats.getMax());
    }

    /**
     * Sorts integers, linearly by counting how many instances there are of each.
     * for optimization we remap the range from (min,max) to (0, max-min)
     * This should save a bit of memory, in which min and max start very high
     * This also allows us to work with negative numbers :)
     */
    public static void sort(int[] data, int min, int max) {
        assert data != null;

        // Count how many items of each there are
        long size = ((long)max - min) + 1;
        if (size >= MAX_RANGE) {
            throw new ArithmeticException("The current CountingSort implementation, only supports a range of: " + MAX_RANGE + " while the requested range, is: " + size);
        }
        int[] buckets = new int[(int)size];
        IntStream.of(data).map( key -> key - min).forEach(key -> buckets[key]++);

        // Now output the counted elements in sorted order
        int outputIndex = 0;
        for (int i = 0; i < buckets.length; i++) {
            int number = (i + min);
            for (int j = 0; j < buckets[i]; j++) {
                data[outputIndex++] = number;
            }
        }
    }
}
