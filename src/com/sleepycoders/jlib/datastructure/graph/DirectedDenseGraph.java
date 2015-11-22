package com.sleepycoders.jlib.datastructure.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Joshua Moody (joshimoo@hotmail.de)
 */
public class DirectedDenseGraph<TVertex> implements IGraph<TVertex> {

    final TVertex EMPTY_VERTEX_SLOT = null;
    final int EMPTY_EDGE_SLOT = 0;
    final int DEFAULT_EDGE_WEIGHT = 1;

    int vertexCapacity;
    int vertexCount;
    public int vertexCount() {
        return vertexCount;
    }

    int edgeCount;
    public int edgeCount() { return edgeCount; }

    List<TVertex> vertices;
    int[][] adjacencyMatrix;

    public DirectedDenseGraph(int capacity) {
        vertexCapacity = capacity;
        vertexCount = 0;
        edgeCount = 0;
        vertices = new ArrayList<>(capacity);
        adjacencyMatrix = new int[capacity][capacity];

        // initalize to empty values
        for (int row = 0; row < adjacencyMatrix.length ; row++) {
            for (int col = 0; col < adjacencyMatrix[row].length; col++) {
                adjacencyMatrix[row][col] = EMPTY_EDGE_SLOT;
            }
        }

        for (int i = 0; i < vertexCapacity; i++) {
            vertices.add(EMPTY_VERTEX_SLOT);
        }
    }


    // Vertices
    public boolean containsVertex(TVertex v) {
        return containsVertex(vertices.indexOf(v));
    }
    private boolean containsVertex(int v) {
        return v != -1 && vertices.get(v) != EMPTY_VERTEX_SLOT;
    }

    public boolean addVertex(TVertex v) {

        // is their space in the graph?
        if (vertexCount >= vertexCapacity) { return false; }

        // does the graph already contain this vertex?
        if (containsVertex(v)) { return false; }

        // find a free spot
        int freeIndex = vertices.indexOf(EMPTY_VERTEX_SLOT);
        vertices.set(freeIndex, v);
        vertexCount++;
        return true;
    }

    public boolean removeVertex(TVertex v) {

        // is the graph empty?
        if (vertexCount == 0) { return false; }

        // lazily remove vertex
        int index = vertices.indexOf(v);
        if (index == -1) { return false; }
        vertices.set(index, EMPTY_VERTEX_SLOT);
        vertexCount--;

        // remove edges
        for (int i = 0; i < vertexCapacity; i++) {
            removeEdge(index, i);
            removeEdge(i, index);
        }

        return true;
    }


    // Edges
    public boolean containsEdge(TVertex src, TVertex dst) {
        return containsEdge(vertices.indexOf(src), vertices.indexOf(dst));
    }
    private boolean containsEdge(int srcIndex, int dstIndex) {
        return srcIndex != -1 && dstIndex != -1 && adjacencyMatrix[srcIndex][dstIndex] != EMPTY_EDGE_SLOT;
    }

    public boolean addEdge(TVertex src, TVertex dst) {
        return addEdge(vertices.indexOf(src), vertices.indexOf(dst));
    }
    private boolean addEdge(int srcIndex, int dstIndex) {
        if (srcIndex == -1 || dstIndex == -1 || containsEdge(srcIndex, dstIndex)) { return false; }
        adjacencyMatrix[srcIndex][dstIndex] = DEFAULT_EDGE_WEIGHT;
        edgeCount++;
        return true;
    }

    public boolean removeEdge(TVertex src, TVertex dst) {
        return removeEdge(vertices.indexOf(src), vertices.indexOf(dst));
    }
    private boolean removeEdge(int srcIndex, int dstIndex) {
        if (srcIndex == -1 || dstIndex == -1 || !containsEdge(srcIndex, dstIndex)) { return false; }
        adjacencyMatrix[srcIndex][dstIndex] = EMPTY_EDGE_SLOT;
        edgeCount--;
        return true;
    }

    public List<TVertex> neighbours(TVertex src) {
        List<TVertex> destinations = new ArrayList<TVertex>();
        int srcIndex = vertices.indexOf(src);
        if (srcIndex == -1) { return destinations; }

        for (int i = 0; i < vertexCapacity; i++) {
            if (containsEdge(srcIndex, i)) { destinations.add(vertices.get(i)); }
        }

        return destinations;
    }

    /**
     * @return a human-readable string of the graph.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (TVertex src : vertices) {

            // source vertex
            if (src == EMPTY_VERTEX_SLOT) { continue; }
            sb.append("\r\n").append(src).append(": [");

            // add destinations
            for (TVertex dst : neighbours(src)) { sb.append(dst).append(','); }
            if (sb.charAt(sb.length() - 1) == ',') { sb.deleteCharAt(sb.length() - 1); }
            sb.append("]");
        }

        return sb.toString();
    }
}
