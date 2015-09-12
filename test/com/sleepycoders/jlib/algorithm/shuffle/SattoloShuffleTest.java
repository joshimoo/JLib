package com.sleepycoders.jlib.algorithm.shuffle;

import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

/**
 * @author Joshua Moody (joshimoo@hotmail.de)
 */
public class SattoloShuffleTest {

    @Test(timeout = 250)
    public void testShuffle() throws Exception {
        // In a Satollo Shuffle the Array cannot be the same, since each item moves at least once
        Integer[] actual = new Integer[] {1, 2, 3, 4};
        Integer[] expected = new Integer[] {1, 2, 3, 4};
        SattoloShuffle.shuffle(actual);
        assertThat(Arrays.asList(actual), hasItems(expected));
        assertThat(Arrays.asList(actual), not(expected));
    }

    @Test(timeout = 250)
    public void testShuffleEmpty() throws Exception {
        Integer[] actual = new Integer[0];
        Integer[] expected = new Integer[0];
        SattoloShuffle.shuffle(actual);
        assertThat(actual, is(expected));
    }

    @Test(timeout = 250)
    public void testShuffleCustomRng() throws Exception {
        Integer[] actual = new Integer[] {1,2,3,4};
        Integer[] expected = new Integer[] {2,3,4,1};
        SattoloShuffle.shuffle(actual, new LeftRotateRandom());
        assertThat(actual, is(expected));
    }

    /**
     * Will always return 0 together with a sattolo shuffle
     * This will lead to a left rotation of the array by 1
     */
    private class LeftRotateRandom extends Random {
        private int[] pool = new int[] {0};
        private int index = 0;

        @Override
        public int nextInt(int bound) {
            return pool[index++ % pool.length] % bound;
        }
    }
}