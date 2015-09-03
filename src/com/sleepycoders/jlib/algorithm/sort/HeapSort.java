package com.sleepycoders.jlib.algorithm.sort;

/**
 * HeapSort implementation, does the heap in place in the data array.
 * @author Joshua Moody (joshimoo@hotmail.de)
 */
public final class HeapSort {

    /**
     * Don't let anyone instantiate this class.
     */
    private HeapSort() {}


    /**
     * HeapSort, builds a max-heap inplace then sorts that max-heap step by step
     * While maintaining the heap-invariant
     */
    public static <T extends Comparable<? super T>> void sort(T[] data) {

        // Create a max heap, so that the max element is at the root (data[0])
        heapify(data);

        // The following loop maintains the invariants that a[0:end] is a heap and every element
        // beyond end is greater than everything before it (so a[end:count] is in sorted order)
        for (int endIndex = data.length - 1; endIndex > 0; endIndex--) {
            // a[0] is the root and largest value. The swap moves it in front of the sorted elements.
            Utility.swap(data, 0, endIndex);

            // the swap ruined the heap property, so restore it for all elements in front of the sorted elements (endIndex -1)
            shiftDown(data, 0, endIndex - 1);
        }
    }


    /**
     * Transforms the passed data into a max/min heap
     */
    private static <T extends Comparable<? super T>> void heapify(T[] data) {

        // start is assigned the index of the last parent node,
        // since leafs already full fill the max-heap property
        for (int startIndex = parent(data.length - 1); startIndex >= 0; startIndex--) {

            // shift down the node at startIndex to the proper place,
            // such that all nodes below the startIndex are in heap order
            shiftDown(data, startIndex, data.length - 1);
        }
    }

    /**
     * Fixes Single violation of a max/min heap
     */
    private static <T extends Comparable<? super T>> void shiftDown(T[] data, int startIndex, int endIndex) {

        // While the root has at least one child
        int root = startIndex;
        while (leftChild(root) <= endIndex) {

            int leftChild = leftChild(root);
            int rightChild = rightChild(root);
            int swap = root;

            // If the left/right child is bigger then swap, make them the new swap
            if (data[swap].compareTo(data[leftChild]) < 0) { swap = leftChild; }
            if (rightChild <= endIndex && data[swap].compareTo(data[rightChild]) < 0) { swap = rightChild; }

            // Update the root element, till we have nothing left to swap
            if (swap != root) {
                Utility.swap(data, root, swap);
                root = swap;
            } else { return; }
        }
    }


    // Indexers
    private static int leftChild(int i) { return (2 * i) + 1; }
    private static int rightChild(int i) { return (2 * i) + 2; }
    private static int parent(int i) { return (i - 1) / 2; }
}