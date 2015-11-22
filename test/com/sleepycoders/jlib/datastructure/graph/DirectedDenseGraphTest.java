package com.sleepycoders.jlib.datastructure.graph;

/**
 * @author Joshua Moody (joshimoo@hotmail.de)
 */
public class DirectedDenseGraphTest extends AbstractGraphTest {

    @Override
    public IGraph<String> createInstance(int capacity) {
        return new DirectedDenseGraph<>(capacity);
    }
}