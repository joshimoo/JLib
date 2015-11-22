package com.sleepycoders.jlib.datastructure.graph;

import java.util.*;

/**
 * @author Joshua Moody (joshimoo@hotmail.de)
 */
public class DirectedSparseGraph<TVertex> implements IGraph<TVertex> {
    final TVertex EMPTY_VERTEX_SLOT = null;

    int vertexCapacity;
    int vertexCount;
    public int vertexCount() {
        return vertexCount;
    }

    int edgeCount;
    public int edgeCount() { return edgeCount; }

    List<TVertex> vertices;
    Map<TVertex, List<TVertex>> edges;

    public DirectedSparseGraph(int capacity) {
        vertexCapacity = capacity;
        vertexCount = 0;
        edgeCount = 0;
        edges = new HashMap<TVertex, List<TVertex>>(capacity);
        vertices = new ArrayList<TVertex>(capacity);

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
        edges.put(v, new LinkedList<TVertex>());
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

        // remove outgoing and incoming edges
        edgeCount -= edges.remove(v).size();
        for ( List<TVertex> incoming : edges.values()) {
            if (incoming.remove(v)) { edgeCount--; }
        }
        return true;
    }


    public boolean containsEdge(TVertex src, TVertex dst) {
        return edges.containsKey(src) && edges.get(src).contains(dst);
    }


    public boolean addEdge(TVertex src, TVertex dst) {

        // are these nodes of the graph?
        if (!containsVertex(src) || !containsVertex(dst)) { return false; }

        // graph already contains edge
        if (containsEdge(src, dst)) { return false; }

        // add the new edge
        edges.get(src).add(dst);
        edgeCount++;
        return true;
    }


    public boolean removeEdge(TVertex src, TVertex dst) {

        // are these nodes of the graph?
        if (!containsVertex(src) || !containsVertex(dst)) { return false; }

        // graph already contains edge
        if (!containsEdge(src, dst)) { return false; }

        edges.get(src).remove(dst);
        edgeCount--;
        return true;
    }

    public List<TVertex> neighbours(TVertex src) {
        List<TVertex> destinations = new ArrayList<TVertex>();
        if (containsVertex(src)) { destinations.addAll(edges.get(src)); }
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
