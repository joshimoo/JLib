package com.sleepycoders.jlib.algorithm.search;

import org.junit.Assert;
import org.junit.Test;

import java.util.Comparator;

/**
 * @author Joshua Moody (joshimoo@hotmail.de)
 */
public class BinarySearchTest {

    @Test(timeout = 250)
    public void testSearch() throws Exception {
        Integer[] data = new Integer[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        int expected = 5;
        Assert.assertEquals(expected, BinarySearch.search(data, data[expected]));
    }

    @Test(timeout = 250)
    public void testSearchNotFound() throws Exception {
        Integer[] data = new Integer[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        Assert.assertEquals(BinarySearch.NOT_FOUND, BinarySearch.search(data, 10));
    }

    @Test(timeout = 250)
    public void testSearchDesc() throws Exception {
        Integer[] data = new Integer[] { 9, 8, 7, 6, 5, 4, 3, 2, 1, 0 };
        int expected = 5;
        int actual = BinarySearch.search(data, data[expected], Comparator.<Integer>reverseOrder());
        Assert.assertEquals(expected, actual);
    }

    @Test(timeout = 250)
    public void testSearchDescNotFound() throws Exception {
        Integer[] data = new Integer[] { 9, 8, 7, 6, 5, 4, 3, 2, 1, 0 };
        Assert.assertEquals(BinarySearch.NOT_FOUND, BinarySearch.search(data, 10, Comparator.<Integer>reverseOrder()));
    }

    @Test(timeout = 250)
    public void testSearchCustomComparer() throws Exception {
        Person[] data = new Person[] {new Person("A", "A", 0), new Person("A", "B", 1), new Person("A", "B", 2), new Person("X", "X", 3) };

        int expected = 1;
        int actual = BinarySearch.search(data, data[1], (p1, p2) -> {
                    int c = p1.getFirstName().compareTo(p2.getFirstName());
                    if (c == 0) {
                        c = p1.getLastName().compareTo(p2.getLastName());
                        if (c == 0) {
                            c = p1.getAge() > p2.getAge() ? 1 : p1.getAge() < p2.getAge() ? -1 : 0;
                        }
                    }

                    return c;
                }
        );
        Assert.assertEquals(expected, actual);
    }

    private class Person {
        private String firstName;
        private String lastName;
        private int age;

        public String getFirstName() { return firstName; }
        public String getLastName() { return lastName; }
        public int getAge() { return age; }

        public Person(String firstName, String lastName, int age) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.age = age;
        }

    }
}