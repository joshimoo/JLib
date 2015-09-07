package com.sleepycoders.jlib.algorithm.sort;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * HeapSort Test Code
 * @author Joshua Moody (joshimoo@hotmail.de)
 */
public class HeapSortTest {

    @Test
    public void testSort() throws Exception {
        Integer[] actual = new Integer[] {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
        Integer[] expected = new Integer[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        // TODO: Add shuffle
        HeapSort.sort(actual);

        // two lists are defined to be equal if they contain the same elements in the same order.
        assertThat(actual, is(expected));
    }

    @Test
    public void testSortEmpty() throws Exception {
        Integer[] actual = new Integer[0];
        Integer[] expected = new Integer[0];
        HeapSort.sort(actual);
        assertThat(actual, is(expected));
    }

    @Test
    public void testSortSingleElement() throws Exception {
        Integer[] actual = new Integer[] {1};
        Integer[] expected = new Integer[] {1};
        HeapSort.sort(actual);
        assertThat(actual, is(expected));
    }

    @Test
    public void testSortAlreadySorted() throws Exception {
        Integer[] actual = new Integer[] {1,2,3,4};
        Integer[] expected = new Integer[] {1,2,3,4};
        HeapSort.sort(actual);
        assertThat(actual, is(expected));
    }

    @Test
    public void testSortDuplicate() throws Exception {
        Integer[] actual = new Integer[] {1, 1, 1, 1};
        Integer[] expected = new Integer[] {1, 1, 1, 1};
        HeapSort.sort(actual);
        assertThat(actual, is(expected));
    }

    // TODO: Consider using Junit4 Parametrized Tests
}