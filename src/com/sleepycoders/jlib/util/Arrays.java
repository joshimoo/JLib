package com.sleepycoders.jlib.util;

/**
 * Arrays Utility Class
 * @author Joshua Moody (joshimoo@hotmail.de)
 */
public final class Arrays {

    /**
     * Don't let anyone instantiate this class.
     */
    private Arrays() {}

    // region Swap
    public static void swap(byte[] data, int left, int right) {
        byte tmp = data[left];
        data[left] = data[right];
        data[right] = tmp;
    }

    public static void swap(short[] data, int left, int right) {
        short tmp = data[left];
        data[left] = data[right];
        data[right] = tmp;
    }

    public static void swap(int[] data, int left, int right) {
        int tmp = data[left];
        data[left] = data[right];
        data[right] = tmp;
    }

    public static void swap(long[] data, int left, int right) {
        long tmp = data[left];
        data[left] = data[right];
        data[right] = tmp;
    }

    public static void swap(float[] data, int left, int right) {
        float tmp = data[left];
        data[left] = data[right];
        data[right] = tmp;
    }

    public static void swap(double[] data, int left, int right) {
        double tmp = data[left];
        data[left] = data[right];
        data[right] = tmp;
    }

    public static void swap(boolean[] data, int left, int right) {
        boolean tmp = data[left];
        data[left] = data[right];
        data[right] = tmp;
    }

    public static void swap(char[] data, int left, int right) {
        char tmp = data[left];
        data[left] = data[right];
        data[right] = tmp;
    }

    public static <T> void swap(T[] data, int left, int right) {
        T tmp = data[left];
        data[left] = data[right];
        data[right] = tmp;
    }
    // endregion

    // region Reverse
    public static void reverse(byte[] data) {
        int mid = data.length / 2;
        int end = data.length - 1;
        for (int i = 0; i < mid; i++) {
            swap(data, i, end - i);
        }
    }

    public static void reverse(short[] data) {
        int mid = data.length / 2;
        int end = data.length - 1;
        for (int i = 0; i < mid; i++) {
            swap(data, i, end - i);
        }
    }

    public static void reverse(int[] data) {
        int mid = data.length / 2;
        int end = data.length - 1;
        for (int i = 0; i < mid; i++) {
            swap(data, i, end - i);
        }
    }

    public static void reverse(long[] data) {
        int mid = data.length / 2;
        int end = data.length - 1;
        for (int i = 0; i < mid; i++) {
            swap(data, i, end - i);
        }
    }

    public static void reverse(float[] data) {
        int mid = data.length / 2;
        int end = data.length - 1;
        for (int i = 0; i < mid; i++) {
            swap(data, i, end - i);
        }
    }

    public static void reverse(double[] data) {
        int mid = data.length / 2;
        int end = data.length - 1;
        for (int i = 0; i < mid; i++) {
            swap(data, i, end - i);
        }
    }

    public static void reverse(boolean[] data) {
        int mid = data.length / 2;
        int end = data.length - 1;
        for (int i = 0; i < mid; i++) {
            swap(data, i, end - i);
        }
    }

    public static void reverse(char[] data) {
        int mid = data.length / 2;
        int end = data.length - 1;
        for (int i = 0; i < mid; i++) {
            swap(data, i, end - i);
        }
    }

    public static <T> void reverse(T[] data) {
        int mid = data.length / 2;
        int end = data.length - 1;
        for (int i = 0; i < mid; i++) {
            swap(data, i, end - i);
        }
    }
    //endregion
}
