package com.sleepycoders.jlib.algorithm.shuffle;

import com.sleepycoders.jlib.util.Arrays;

import java.util.Random;

/**
 * The difference beetwen a Knuth and Sattolo Shuffle,
 * Is that the Sattolo Shuffle, is current index exclusive
 * And therefore results in a permutation that always consists of a single cycle
 * @author Joshua Moody (joshimoo@hotmail.de)
 */
public final class SattoloShuffle {

    /**
     * Don't let anyone instantiate this class.
     */
    private SattoloShuffle() {}


    /**
     * Uses the default System PRNG
     * Guarantees that each item is moved at least once
     */
    public static <T> void shuffle(T[] data) {
        shuffle(data, new Random());
    }

    /**
     * The Randomness is higly tied to the used PRNG
     * And the used internal state bits, where applicable.
     * For example to perfectly shuffle a deck of 52 cards, Would require 52! ? 2225.6 possible permutations.
     * It's impossible for a generator with less than 226 bits of internal state to produce all the possible permutations of a 52-card deck.
     * @param rng a pre initialized PRNG
     */
    public static <T> void shuffle(T[] data, Random rng) {
        assert data != null && rng != null;
        for (int i = data.length - 1; i > 0; i--) {
            // upperbound is (i-1) (inclusive), therefore every item will be moved at least once
            Arrays.swap(data, rng.nextInt(i), i);
        }
    }
}
