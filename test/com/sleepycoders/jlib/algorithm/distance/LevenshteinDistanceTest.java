package com.sleepycoders.jlib.algorithm.distance;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;


/**
 * LevenshteinDistance Test Code
 * @author Joshua Moody (joshimoo@hotmail.de)
 */
public class LevenshteinDistanceTest {

    @Test(timeout = 250)
    public void testDistanceDelete() throws Exception {
        String src = "abcd";
        String dst = "abc";
        assert src.length() > dst.length();
        assertThat(LevenshteinDistance.distance(src, dst), is(src.length() - dst.length()));
    }

    @Test(timeout = 250)
    public void testDistanceInsert() throws Exception {
        String src = "abc";
        String dst = "abcd";
        assert dst.length() > src.length();
        assertThat(LevenshteinDistance.distance(src, dst), is(dst.length() - src.length()));
    }

    @Test(timeout = 250)
    public void testDistanceSubstitution() throws Exception {
        String src = "abcd";
        String dst = "abXd";
        assert !src.equals(dst);
        assertThat(LevenshteinDistance.distance(src, dst), is(1));
    }

    @Test(timeout = 250)
    public void testDistanceSame() throws Exception {
        String src = "abcd";
        String dst = "abcd";
        assert src.equals(dst);
        assertThat(LevenshteinDistance.distance(src, dst), is(0));
    }

    @Test(timeout = 250)
    public void testDistanceBothEmpty() throws Exception {
        String src = "";
        String dst = "";
        assert src.isEmpty() && dst.isEmpty();
        assertThat(LevenshteinDistance.distance("", ""), is(0));
    }

    @Test(timeout = 250)
    public void testDistanceSrcEmpty() throws Exception {
        String src = "";
        String dst = "abcd";
        assert src.isEmpty();
        assertThat(LevenshteinDistance.distance(src, dst), is(dst.length()));
    }

    @Test(timeout = 250)
    public void testDistanceDstEmpty() throws Exception {
        String src = "abcdefgh";
        String dst = "";
        assert dst.isEmpty();
        assertThat(LevenshteinDistance.distance(src, dst), is(src.length()));
    }

    @Test(timeout = 250)
    public void testDistanceBothNull() throws Exception {
        String src = null;
        String dst = null;
        assertThat(LevenshteinDistance.distance(src, dst), is(0));
    }

    @Test(timeout = 250)
    public void testDistanceSrcNull() throws Exception {
        String src = null;
        String dst = "ABCD";
        assertThat(LevenshteinDistance.distance(src, dst), is(dst.length()));
    }

    @Test(timeout = 250)
    public void testDistanceDstNull() throws Exception {
        String src = "ABCD";
        String dst = null;
        assertThat(LevenshteinDistance.distance(src, dst), is(src.length()));
    }

    @Test(timeout = 250)
    public void testDistanceCustomCostFactors() throws Exception {
        String src = "abcd";
        String dst = "abXd";
        assert src.length() == dst.length();
        int deletionCost = 1;
        int insertionCost = 1;
        int substitutionCost = 5;

        // This will prefer deleting and insertion the character instead of substituting it.
        // Since the cost factor of these two operations is smaller than that of substituting
        assertThat(
                LevenshteinDistance.distance(src, dst, deletionCost, insertionCost, substitutionCost),
                is(Math.min(deletionCost + insertionCost, substitutionCost))
        );
    }

    @Test(timeout = 250)
    public void testDistanceRec() throws Exception {
        String src = "abcd";
        String dst = "abc";
        assert src.length() > dst.length();
        assertThat(LevenshteinDistance.distanceRec(src, dst), is(src.length() - dst.length()));
    }
}