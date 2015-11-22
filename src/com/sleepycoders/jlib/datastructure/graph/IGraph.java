package com.sleepycoders.jlib.datastructure.graph;

/**
 * @author Joshua Moody (joshimoo@hotmail.de)
 */
public interface IGraph<TVertex> {
    // Edges
    boolean containsVertex(TVertex v);
    boolean addVertex(TVertex v);
    boolean removeVertex(TVertex v);

    // Vertices
    boolean containsEdge(TVertex v1, TVertex v2);
    boolean addEdge(TVertex v1, TVertex v2);
    boolean removeEdge(TVertex v1, TVertex v2);
}
