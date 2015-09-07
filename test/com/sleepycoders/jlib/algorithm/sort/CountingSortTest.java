package com.sleepycoders.jlib.algorithm.sort;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * CountingSort Test Code that achieves 100% coverage
 * @author Joshua Moody (joshimoo@hotmail.de)
 */
public class CountingSortTest {
    @Test
    public void testSort() throws Exception {
        int[] actual = new int[] {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
        int[] expected = new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        CountingSort.sort(actual);

        // two lists are defined to be equal if they contain the same elements in the same order.
        assertThat(actual, is(expected));
    }

    @Test
    public void testSortEmpty() throws Exception {
        int[] actual = new int[0];
        int[] expected = new int[0];
        CountingSort.sort(actual);
        assertThat(actual, is(expected));
    }

    @Test
    public void testSortSingleElement() throws Exception {
        int[] actual = new int[] {1};
        int[] expected = new int[] {1};
        CountingSort.sort(actual);
        assertThat(actual, is(expected));
    }

    @Test
    public void testSortAlreadySorted() throws Exception {
        int[] actual = new int[] {1,2,3,4};
        int[] expected = new int[] {1,2,3,4};
        CountingSort.sort(actual);
        assertThat(actual, is(expected));
    }

    @Test
    public void testSortDuplicate() throws Exception {
        int[] actual = new int[] {1, 1, 1, 1};
        int[] expected = new int[] {1, 1, 1, 1};
        CountingSort.sort(actual);
        assertThat(actual, is(expected));
    }

    @Test(expected = ArithmeticException.class)
    public void testSortOutsideRangeThrow() throws Exception {
        // This will lead to the maximum, memory consumption
        // Therefore we will not want to process this.
        int[] actual = new int[] {Integer.MIN_VALUE, CountingSort.MAX_RANGE};
        int[] expected = new int[] {Integer.MIN_VALUE, CountingSort.MAX_RANGE};
        CountingSort.sort(actual);
        assertThat(actual, is(expected));
    }

    // TODO: Consider using Junit4 Parametrized Tests
}