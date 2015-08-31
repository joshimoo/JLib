package com.sleepycoders.jlib.algorithm.sort;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Utility Test Code for common Sort functionality
 * @author Joshua Moody (joshimoo@hotmail.de)
 */
public class UtilityTest {

    @Test
    public void testSwapArray() throws Exception {
        Integer[] actual = new Integer[] {0,1};
        Integer[] expected = new Integer[] {1,0};
        Utility.swap(actual, 0, 1);
        assertThat(actual, is(expected));
    }

    @Test
    public void testSwapList() throws Exception {
        List<Integer> actual = Arrays.asList(0,1);
        List<Integer> expected = Arrays.asList(1,0);
        Utility.swap(actual, 0, 1);
        assertThat(actual, is(expected));
    }
}