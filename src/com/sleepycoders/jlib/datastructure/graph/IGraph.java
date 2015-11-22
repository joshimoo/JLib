package com.sleepycoders.jlib.datastructure.graph;

import java.util.List;

/**
 * @author Joshua Moody (joshimoo@hotmail.de)
 */
public interface IGraph<TVertex> {

    // Vertices
    int vertexCount();
    boolean containsVertex(TVertex v);
    boolean addVertex(TVertex v);
    boolean removeVertex(TVertex v);

    // Edges
    int edgeCount();
    boolean containsEdge(TVertex v1, TVertex v2);
    boolean addEdge(TVertex v1, TVertex v2);
    boolean removeEdge(TVertex v1, TVertex v2);

    // Utility
    List<TVertex> neighbours(TVertex v);
}
