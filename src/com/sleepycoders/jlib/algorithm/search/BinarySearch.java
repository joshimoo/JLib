package com.sleepycoders.jlib.algorithm.search;
import java.util.Comparator;

/**
 * @author Joshua Moody (joshimoo@hotmail.de)
 */
public final class BinarySearch {

    public static final int NOT_FOUND = -1;

    /**
     * Don't let anyone instantiate this class.
     */
    private BinarySearch() {}

    public static <T extends Comparable<? super T>> int search(T[] data, T elem) {
        int low = 0;
        int high = data.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int c = elem.compareTo(data[mid]);

            if (c < 0) { high = mid - 1; }
            else if (c > 0) { low = mid + 1; }
            else { return mid; }
        }

        return NOT_FOUND;
    }

    public static <T> int search(T[] data, T elem, Comparator<? super T> cmp) {
        int low = 0;
        int high = data.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int c = cmp.compare(elem, data[mid]);

            if (c < 0) { high = mid - 1; }
            else if (c > 0) { low = mid + 1; }
            else { return mid; }
        }

        return NOT_FOUND;
    }
}
