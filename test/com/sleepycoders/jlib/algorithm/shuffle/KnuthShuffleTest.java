package com.sleepycoders.jlib.algorithm.shuffle;

import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * @author Joshua Moody (joshimoo@hotmail.de)
 */
public class KnuthShuffleTest {

    @Test(timeout = 250)
    public void testShuffle() throws Exception {
        // hmm, in knuth shuffle the array can be the same.
        // since it's inclusive, so it's hard to write a good unit test.
        Integer[] actual = new Integer[] {1, 2, 3, 4};
        Integer[] expected = new Integer[] {1, 2, 3, 4};
        KnuthShuffle.shuffle(actual);
        assertThat(Arrays.asList(actual), hasItems(expected));
    }

    @Test(timeout = 250)
    public void testShuffleEmpty() throws Exception {
        Integer[] actual = new Integer[0];
        Integer[] expected = new Integer[0];
        KnuthShuffle.shuffle(actual);
        assertThat(actual, is(expected));
    }

    @Test(timeout = 250)
    public void testShuffleCustomRng() throws Exception {
        Integer[] actual = new Integer[] {1,2,3,4};
        Integer[] expected = new Integer[] {2,3,4,1};
        KnuthShuffle.shuffle(actual, new LeftRotateRandom());
        assertThat(actual, is(expected));
    }

    /**
     * Will always return 0 together with a knuth shuffle
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