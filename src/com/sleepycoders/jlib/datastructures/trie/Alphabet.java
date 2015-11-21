package com.sleepycoders.jlib.datastructures.trie;

/**
 * @author Joshua Moody (joshimoo@hotmail.de)
 */
public enum Alphabet {
    LETTERS("abcdefghijklmnopqrstuvwxyz"),
    NUMBERS("0123456789"),
    BINARY("01"),
    NONCONSECUTIVE("a1b2c3") {
        @Override
        public byte getIndex(char c) {
            return (byte) alphabet.indexOf(c);
        }
    };


    public final String alphabet;

    Alphabet(String validChars) {
        this.alphabet = validChars;
        consecutiveBase = Character.getNumericValue(validChars.charAt(0));
    }

    private final int consecutiveBase;
    private byte getConsecutiveIndex(char c) {
        return (byte) (Character.getNumericValue(c) - consecutiveBase);
    }

    /**
     * the default index function is Case Insensitive for A-Z,a-z
     * So for letters that are a different case it will still generate a valid index
     */
    public byte getIndex(char c) {
        return getConsecutiveIndex(c);
    }

    public int size() {
        return alphabet.length();
    }

    public byte[] toInt(String word) {
        byte[] indexes = new byte[word.length()];
        for (int i = 0; i < indexes.length; i++) {
            indexes[i] = getIndex(word.charAt(i));
        }
        return indexes;
    }
}
