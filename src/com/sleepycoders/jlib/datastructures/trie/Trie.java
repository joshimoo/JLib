package com.sleepycoders.jlib.datastructures.trie;

import java.util.Collection;

/**
 * @author Joshua Moody (joshimoo@hotmail.de)
 */
public class Trie {
    private boolean isWord;
    private Trie[] children;
    private int childCount;
    private final Alphabet alphabet;

    public boolean isWord() {
        return isWord;
    }

    public boolean hasChildren() {
        return childCount > 0;
    }

    public Trie(Alphabet alphabet) {
        this.alphabet = alphabet;
        children = new Trie[alphabet.size()];
    }

    public Trie(Alphabet alphabet, Collection<String> words) {
        this(alphabet);
        for (String w : words) { add(w); }
    }


    public boolean add(String word) {
        Trie node = this;
        for (int i = 0; i < word.length(); i++) {
            byte index = alphabet.getIndex(word.charAt(i));
            if (index < 0 || index > alphabet.size() - 1) {
                throw new IllegalArgumentException("word contains, invalid characters");
            }

            // traverse the trie and add missing nodes
            Trie child = node.children[index];
            if (child == null) {
                child = new Trie(alphabet);
                node.children[index] = child;
                node.childCount++;
            }

            node = child;
        }

        // Word is already in the trie
        if (node.isWord) { return false; }
        node.isWord = true;
        return true;
    }

    public boolean isPrefix(String prefix) {
        Trie node = getNode(prefix);
        return node != null && node.childCount > 0;
    }

    public boolean contains(String word) {
        Trie node = getNode(word);
        return node != null && node.isWord;
    }

    public Trie getNode(String word) {
        Trie node = this;
        for (int i = 0; i < word.length(); i++) {
            byte index = alphabet.getIndex(word.charAt(i));
            if (index < 0 || index > alphabet.size() - 1) {
                throw new IllegalArgumentException("word contains, invalid characters");
            }

            // Does the word exist?
            node = node.children[index];
            if (node == null) { return null; }
        }

        return node;
    }
}

