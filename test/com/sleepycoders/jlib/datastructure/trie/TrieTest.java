package com.sleepycoders.jlib.datastructure.trie;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Joshua Moody (joshimoo@hotmail.de)
 */
public class TrieTest {
    private Trie root;

    @Before
    public void setUp() throws Exception {
        List<String> words = Arrays.asList("ab", "abcd", "xyz");
        root = new Trie(Alphabet.LETTERS, words);
    }

    @Test
    public void testIsWord() throws Exception {
        assertTrue(root.getNode("ab").isWord());
        assertFalse(root.getNode("abc").isWord());
    }

    @Test
    public void testHasChildren() throws Exception {
        assertTrue(root.getNode("ab").hasChildren());
        assertFalse(root.getNode("xyz").hasChildren());
    }

    @Test
    public void testAdd() throws Exception {
        assertTrue(root.add("hello"));
        assertFalse(root.add("hello"));
        assertTrue(root.add("abc"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddThrows() throws Exception {
        root.add("123 - NumbersAreNotInTheAlphabet");
    }

    @Test
    public void testIsPrefix() throws Exception {
        assertTrue(root.isPrefix("ab"));
        assertFalse(root.isPrefix("abcd"));
    }

    @Test
    public void testContains() throws Exception {
        assertTrue(root.contains("xyz"));
        assertFalse(root.contains("false"));
    }

    @Test
    public void testGetNode() throws Exception {
        assertNull(root.getNode("null"));
        assertEquals(root, root.getNode(""));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetNodeThrows() throws Exception {
        root.getNode("123 - NumbersAreNotInTheAlphabet");
    }
}