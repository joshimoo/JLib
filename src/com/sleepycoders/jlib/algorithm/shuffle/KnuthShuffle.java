package com.sleepycoders.jlib.algorithm.shuffle;

import com.sleepycoders.jlib.util.Arrays;

import java.util.Random;

/**
 * FisherYates Shuffle (Knuth) O(n) implementation
 * @author Joshua Moody (joshimoo@hotmail.de)
 */
public final class KnuthShuffle {

    /**
     * Don't let anyone instantiate this class.
     */
    private KnuthShuffle() {}

    /**
     * Uses the default System PRNG
     */
    public static <T> void shuffle(T[] data) {
        shuffle(data, new Random());
    }

    /**
     * The Randomness is higly tied to the used PRNG
     * And the used internal state bits, where applicable.
     * For example to perfectly shuffle a deck of 52 cards, Would require 52! ≈ 2225.6 possible permutations.
     * It's impossible for a generator with less than 226 bits of internal state to produce all the possible permutations of a 52-card deck.
     * @param rng a pre initialized PRNG
     */
    public static <T> void shuffle(T[] data, Random rng) {
        assert data != null && rng != null;
        for (int i = data.length - 1; i > 0; i--) {
            // upperbound is i (inclusive)
            Arrays.swap(data, rng.nextInt(i + 1), i);
        }
    }
}
