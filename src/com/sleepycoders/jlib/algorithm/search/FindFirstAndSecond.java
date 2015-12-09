package com.sleepycoders.jlib.algorithm.search;

import java.util.*;

/**
 * A generic solution, to find the first and second element in the minimum amount of element compares
 * @author Joshua Moody (joshimoo@hotmail.de)
 */
public final class FindFirstAndSecond {

    /**
     * Utility Class
     * Don't let anyone instantiate this class.
     */
    private FindFirstAndSecond() {}


    public static <T extends Comparable<? super T>> T findMax(List<T> data) {
        assert data != null;
        return findFirstAndSecondMax(data).get(0);
    }

    public static <T extends Comparable<? super T>> List<T> findFirstAndSecondMax(List<T> data) {
        assert data != null;
        return findFirstAndSecond(data, Comparator.<T>naturalOrder());
    }

    public static <T extends Comparable<? super T>> T findMin(List<T> data) {
        assert data != null;
        return findFirstAndSecondMin(data).get(0);
    }

    public static <T extends Comparable<? super T>> List<T> findFirstAndSecondMin(List<T> data) {
        assert data != null;
        return findFirstAndSecond(data, Comparator.<T>reverseOrder());
    }


    private static <T> List<T> findFirstAndSecond(List<T> data, Comparator<T> cmp) {
        assert data != null && cmp != null;

        List<T> results = new LinkedList<T>();
        Map<T, List<T>> map = new HashMap<>();
        T current = findRec(data, cmp, map, 0, data.size() - 1);
        List<T> losers = map.get(current);
        results.add(current);
        if(losers != null) { results.add(findRec(losers, cmp, map, 0, losers.size() - 1)); }
        return results;
    }


    private static <T> T findRec(List<T> data, Comparator<T> cmp, Map<T, List<T>> map, int low, int high) {
        assert data != null && cmp != null;

        // Base Case - 1 Element -> this will trigger the comparison upwards
        if (low >= high) { return data.get(high); } // TODO: are we counting these compares or only cmp calls?

        int mid = low + (high - low) / 2;
        T left = findRec(data, cmp, map, low, mid);
        T right = findRec(data, cmp, map, mid + 1, high);

        /**
         * For the Interview Question:
         * we could skip the cmp call and only use the regular check, which would bring it down to 1 cmp call per level
         * I could remove the contains calls, and pre allocate the Hashmaps List since it would just require one pass through the data, and no compares.
         */
        if(cmp.compare(left, right) >= 0) { // for equal elements we always take left
            if (!map.containsKey(left)) { map.put(left, new ArrayList<T>()); }
            map.get(left).add(right);
            return left;
        } else { // take right
            if (!map.containsKey(right)) { map.put(right, new ArrayList<T>()); }
            map.get(right).add(left);
            return right;
        }
    }

}
