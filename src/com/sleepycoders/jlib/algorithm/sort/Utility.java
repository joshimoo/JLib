package com.sleepycoders.jlib.algorithm.sort;

import java.util.List;

/**
 * Sorting Utility Class
 * @author Joshua Moody (joshimoo@hotmail.de)
 */
final class Utility {

    /**
     * Don't let anyone instantiate this class.
     */
    private Utility() {}

    static <T> void swap(T[] data, int left, int right) {
        T tmp = data[left];
        data[left] = data[right];
        data[right] = tmp;
    }

    static <T> void swap(List<T> data, int left, int right) {
        T tmp = data.get(left);
        data.set(left, data.get(right));
        data.set(right, tmp);
    }

}
