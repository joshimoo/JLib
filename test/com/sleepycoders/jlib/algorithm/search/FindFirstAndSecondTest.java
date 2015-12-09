package com.sleepycoders.jlib.algorithm.search;

import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

/**
 * @author Joshua Moody (joshimoo@hotmail.de)
 */
public class FindFirstAndSecondTest {
    Random r = new Random();
    int start = 0;
    int end = 0;

    @Before
    public void setUp() throws Exception {
        r = new Random();
        start = r.nextInt(64) - r.nextInt(64);
        end = start + 1 + r.nextInt(64);
    }



    private List<Integer> createDuplicateTestRange(int start, int end) {
        List<Integer> range = createTestRange(start, end);
        List<Integer> duplicates = new ArrayList<>(range);
        duplicates.addAll(range);
        Collections.shuffle(duplicates);
        return duplicates;
    }

    private List<Integer> createTestRange(int start, int end) {

        List<Integer> range = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
        Collections.shuffle(range);
        return range;
    }

    @Test
    public void testRun1000Times() throws Exception {
        for (int i = 0; i < 1000 ; i++) {
            setUp();
            testFindMax();
            testFindMaxDuplicates();
            testFindFirstAndSecondMax();
            testFindFirstAndSecondMaxDuplicates();

            testFindMin();
            testFindMinDuplicates();
            testFindFirstAndSecondMin();
            testFindFirstAndSecondMinDuplicates();
        }
    }

    @Test
    public void testFindSingleElement() throws Exception {
        List<Integer> range = createTestRange(1, 1);
        assertThat(FindFirstAndSecond.findMax(range), is(1));
    }


    @Test
    public void testFindMax() throws Exception {
        List<Integer> range = createTestRange(start, end);
        assertThat(FindFirstAndSecond.findMax(range), is(end));
    }

    @Test
    public void testFindMaxDuplicates() throws Exception {
        List<Integer> range = createTestRange(start, end);
        assertThat(FindFirstAndSecond.findMax(range), is(end));
    }

    @Test
    public void testFindFirstAndSecondMax() throws Exception {
        List<Integer> range = createTestRange(start, end);
        List<Integer> expected = Arrays.asList(end, end-1);
        assertThat(FindFirstAndSecond.findFirstAndSecondMax(range), is(expected));
    }

    @Test
    public void testFindFirstAndSecondMaxDuplicates() throws Exception {
        List<Integer> range = createDuplicateTestRange(start, end);
        List<Integer> expected = Arrays.asList(end, end);
        assertThat(FindFirstAndSecond.findFirstAndSecondMax(range), is(expected));
    }

    @Test
    public void testFindMin() throws Exception {
        List<Integer> range = createTestRange(start, end);
        assertThat(FindFirstAndSecond.findMin(range), is(start));
    }

    @Test
    public void testFindMinDuplicates() throws Exception {
        List<Integer> range = createDuplicateTestRange(start, end);
        assertThat(FindFirstAndSecond.findMin(range), is(start));
    }

    @Test
    public void testFindFirstAndSecondMin() throws Exception {
        List<Integer> range = createTestRange(start, end);
        List<Integer> expected = Arrays.asList(start, start + 1);
        assertThat(FindFirstAndSecond.findFirstAndSecondMin(range), is(expected));
    }

    @Test
    public void testFindFirstAndSecondMinDuplicates() throws Exception {
        List<Integer> range = createDuplicateTestRange(start, end);
        List<Integer> expected = Arrays.asList(start, start);
        assertThat(FindFirstAndSecond.findFirstAndSecondMin(range), is(expected));
    }

    @Test
    public void testFindFirstAndSecond() throws Exception {

    }
}