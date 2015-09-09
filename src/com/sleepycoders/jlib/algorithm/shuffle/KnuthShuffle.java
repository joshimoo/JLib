package com.sleepycoders.jlib.algorithm.shuffle;

import com.sleepycoders.jlib.util.Arrays;

import java.util.Random;

/**
 * Fisher–Yates Shuffle (Knuth) O(n) implementation
 * @author Joshua Moody (joshimoo@hotmail.de)
 */
public final class KnuthShuffle {
    public static <T> void shuffle(T[] data) {
        shuffle(data, new Random());
    }
    
    public static <T> void shuffle(T[] data, Random rng) {
        assert data != null && rng != null;
        for (int i = data.length - 1; i > 0; i--) {
            // upperbound is i (inclusive)
            Arrays.swap(data, rng.nextInt(i + 1), i);
        }
    }
}
