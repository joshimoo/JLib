package com.sleepycoders.jlib.algorithm.sort;

import com.sleepycoders.jlib.algorithm.shuffle.KnuthShuffle;
import org.junit.Test;

import java.util.Comparator;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * ShellSort Test Code that achieves 100% coverage
 * @author Joshua Moody (joshimoo@hotmail.de)
 */
public class ShellSortTest {

    @Test(timeout = 250)
    public void testSort() throws Exception {
        Integer[] actual = new Integer[] {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
        Integer[] expected = new Integer[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        KnuthShuffle.shuffle(actual);
        ShellSort.sort(actual);

        // two lists are defined to be equal if they contain the same elements in the same order.
        assertThat(actual, is(expected));
    }

    @Test(timeout = 250)
    public void testSortEmpty() throws Exception {
        Integer[] actual = new Integer[0];
        Integer[] expected = new Integer[0];
        ShellSort.sort(actual);
        assertThat(actual, is(expected));
    }

    @Test(timeout = 250)
    public void testSortSingleElement() throws Exception {
        Integer[] actual = new Integer[] {1};
        Integer[] expected = new Integer[] {1};
        ShellSort.sort(actual);
        assertThat(actual, is(expected));
    }

    @Test(timeout = 250)
    public void testSortAlreadySorted() throws Exception {
        Integer[] actual = new Integer[] {1,2,3,4};
        Integer[] expected = new Integer[] {1,2,3,4};
        ShellSort.sort(actual);
        assertThat(actual, is(expected));
    }

    @Test(timeout = 250)
    public void testSortDuplicate() throws Exception {
        Integer[] actual = new Integer[] {1, 1, 1, 1};
        Integer[] expected = new Integer[] {1, 1, 1, 1};
        ShellSort.sort(actual);
        assertThat(actual, is(expected));
    }

    @Test(timeout = 250)
    public void testSortReverse() throws Exception {
        Integer[] actual = new Integer[] {1,2,3,4};
        Integer[] expected = new Integer[] {4,3,2,1};
        KnuthShuffle.shuffle(actual);
        ShellSort.sort(actual, Comparator.<Integer>reverseOrder());
        assertThat(actual, is(expected));
    }

    @Test(timeout = 250)
    public void testSortCustomGaps() throws Exception {
        Integer[] actual = new Integer[] {1,2,3,4};
        Integer[] expected = new Integer[] {1,2,3,4};
        // Sedgewick, 1986 gap sequence
        Integer[] gaps = new Integer[] {109,41,19,5,1};
        ShellSort.sort(actual, gaps);
        assertThat(actual, is(expected));
    }

    @Test(timeout = 250)
    public void testSortCustomGapsReverse() throws Exception {
        Integer[] actual = new Integer[] {1,2,3,4};
        Integer[] expected = new Integer[] {4,3,2,1};
        // Sedgewick, 1986 gap sequence
        Integer[] gaps = new Integer[] {109,41,19,5,1};
        ShellSort.sort(actual, gaps, Comparator.<Integer>reverseOrder());
        assertThat(actual, is(expected));
    }

    @Test(timeout = 250, expected = IllegalArgumentException.class)
    public void testSortInvalidGaps() throws Exception {
        Integer[] actual = new Integer[] {1,2,3,4};
        Integer[] expected = new Integer[] {4,3,2,1};
        // The gap-sequence needs to contain a 1
        Integer[] gaps = new Integer[] {4,2,0};
        ShellSort.sort(actual, gaps);
        assertThat(actual, is(expected));
    }

    // TODO: Consider using Junit4 Parametrized Tests
}