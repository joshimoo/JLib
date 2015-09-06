package com.sleepycoders.jlib.util;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Arrays Utility Test Code for shared functionality
 * @author Joshua Moody (joshimoo@hotmail.de)
 */
public class ArraysTest {

    // region Swap
    @Test
    public void testSwapByte() throws Exception {
        byte[] actual = new byte[] {0,1};
        byte[] expected = new byte[] {1,0};
        Arrays.swap(actual, 0, 1);
        assertThat(actual, is(expected));
    }

    @Test
    public void testSwapShort() throws Exception {
        short[] actual = new short[] {0,1};
        short[] expected = new short[] {1,0};
        Arrays.swap(actual, 0, 1);
        assertThat(actual, is(expected));
    }

    @Test
    public void testSwapInt() throws Exception {
        int[] actual = new int[] {0,1};
        int[] expected = new int[] {1,0};
        Arrays.swap(actual, 0, 1);
        assertThat(actual, is(expected));
    }

    @Test
    public void testSwapLong() throws Exception {
        long[] actual = new long[] {0,1};
        long[] expected = new long[] {1,0};
        Arrays.swap(actual, 0, 1);
        assertThat(actual, is(expected));
    }

    @Test
    public void testSwapFloat() throws Exception {
        float[] actual = new float[] {0,1};
        float[] expected = new float[] {1,0};
        Arrays.swap(actual, 0, 1);
        assertThat(actual, is(expected));
    }

    @Test
    public void testSwapDouble() throws Exception {
        double[] actual = new double[] {0,1};
        double[] expected = new double[] {1,0};
        Arrays.swap(actual, 0, 1);
        assertThat(actual, is(expected));
    }

    @Test
    public void testSwapBool() throws Exception {
        boolean[] actual = new boolean[] {false,true};
        boolean[] expected = new boolean[] {true,false};
        Arrays.swap(actual, 0, 1);
        assertThat(actual, is(expected));
    }

    @Test
    public void testSwapChar() throws Exception {
        char[] actual = new char[] {0,1};
        char[] expected = new char[] {1,0};
        Arrays.swap(actual, 0, 1);
        assertThat(actual, is(expected));
    }

    @Test
    public void testSwap() throws Exception {
        Integer[] actual = new Integer[] {0,1};
        Integer[] expected = new Integer[] {1,0};
        Arrays.swap(actual, 0, 1);
        assertThat(actual, is(expected));
    }
    // endregion

    // region Reverse
    @Test
    public void testReverseByte() throws Exception {
        byte[] actual = new byte[] {0,1,2};
        byte[] expected = new byte[] {2,1,0};
        Arrays.reverse(actual);
        assertThat(actual, is(expected));
    }

    @Test
    public void testReverseShort() throws Exception {
        short[] actual = new short[] {0,1,2};
        short[] expected = new short[] {2,1,0};
        Arrays.reverse(actual);
        assertThat(actual, is(expected));
    }

    @Test
    public void testReverseInt() throws Exception {
        int[] actual = new int[] {0,1,2};
        int[] expected = new int[] {2,1,0};
        Arrays.reverse(actual);
        assertThat(actual, is(expected));
    }

    @Test
    public void testReverseFloat() throws Exception {
        float[] actual = new float[] {0,1,2};
        float[] expected = new float[] {2,1,0};
        Arrays.reverse(actual);
        assertThat(actual, is(expected));
    }

    @Test
    public void testReverseDouble() throws Exception {
        double[] actual = new double[] {0,1,2};
        double[] expected = new double[] {2,1,0};
        Arrays.reverse(actual);
        assertThat(actual, is(expected));
    }

    @Test
    public void testReverseLong() throws Exception {
        long[] actual = new long[] {0,1,2};
        long[] expected = new long[] {2,1,0};
        Arrays.reverse(actual);
        assertThat(actual, is(expected));
    }

    @Test
    public void testReverseBool() throws Exception {
        boolean[] actual = new boolean[] {false,true,true};
        boolean[] expected = new boolean[] {true,true,false};
        Arrays.reverse(actual);
        assertThat(actual, is(expected));
    }

    @Test
    public void testReverseChar() throws Exception {
        char[] actual = new char[] {'a','b','c'};
        char[] expected = new char[] {'c','b','a'};
        Arrays.reverse(actual);
        assertThat(actual, is(expected));
    }

    @Test
    public void testReverse() throws Exception {
        Object a = new Object();
        Object b = new Object();
        Object c = new Object();
        Object[] actual = new Object[] {a,b,c};
        Object[] expected = new Object[] {c,b,a};
        Arrays.reverse(actual);
        assertThat(actual, is(expected));
    }
    // endregion
}