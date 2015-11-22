package com.sleepycoders.jlib.datastructure.graph;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;

/**
 * @author Joshua Moody (joshimoo@hotmail.de)
 */
public class DirectedDenseGraphTest {
    private final int capacity = 10;
    Random r;
    IGraph<String> graph;
    List<String> nodes = Arrays.asList("A","B","C","D");

    @Before
    public void setUp() throws Exception {
        r = new Random();
        graph = new DirectedDenseGraph<>(capacity);
        nodes.stream().forEach(graph::addVertex);
        for (int i = 0; i < nodes.size(); i++) {
            for (int j = i + 1; j < nodes.size(); j++) {
                graph.addEdge(nodes.get(i), nodes.get(j));
            }
        }
    }

    @Test
    public void testContainsVertex() throws Exception {
        assertTrue(graph.containsVertex(nodes.get(0)));
        assertFalse(graph.containsVertex("NOT_IN_GRAPH"));
    }

    @Test
    public void testAddVertex() throws Exception {
        String duplicate =  nodes.get(0);
        String node = "E";

        assertFalse(graph.containsVertex(node));
        assertTrue(graph.addVertex(node));
        assertTrue(graph.containsVertex(node));

        assertTrue(graph.containsVertex(duplicate));
        assertFalse(graph.addVertex(duplicate));
        assertTrue(graph.containsVertex(duplicate));
    }

    @Test
    public void testRemoveVertex() throws Exception {
        String invalid = "NOT_IN_GRAPH";
        String node = nodes.get(0);

        assertTrue(graph.containsVertex(node));
        assertTrue(graph.removeVertex(node));
        assertFalse(graph.containsVertex(node));

        assertFalse(graph.containsVertex(invalid));
        assertFalse(graph.removeVertex(invalid));
        assertFalse(graph.containsVertex(invalid));
    }

    @Test
    public void testContainsEdge() throws Exception {
        assertTrue(graph.containsEdge(nodes.get(0), nodes.get(1)));
        assertFalse(graph.containsEdge(nodes.get(0), nodes.get(0)));
        assertFalse(graph.containsEdge("NOT_IN_GRAPH", "NOT_IN_GRAPH"));
        assertFalse(graph.containsEdge(
                nodes.get(nodes.size() - 1),
                nodes.get(r.nextInt(nodes.size())))
        );
    }

    @Test
    public void testAddEdge() throws Exception {
        String src = nodes.get(nodes.size() - 1);
        String valid = nodes.get(0);
        String duplicate = valid;
        String invalid = "NOT_IN_GRAPH";

        assertFalse(graph.containsEdge(src, valid));
        assertFalse(graph.containsEdge(src, invalid));

        assertTrue(graph.addEdge(src, valid));
        assertTrue(graph.containsEdge(src, valid));
        assertFalse(graph.addEdge(src, duplicate));

        assertFalse(graph.addEdge(src, invalid));
        assertFalse(graph.containsEdge(src, invalid));
    }

    @Test
    public void testRemoveEdge() throws Exception {
        String src = nodes.get(0);
        String rem = nodes.get(1);
        String invalid = "NOT_IN_GRAPH";

        assertTrue(graph.containsEdge(src, rem));
        assertTrue(graph.removeEdge(src, rem));

        assertFalse(graph.containsEdge(src, rem));
        assertFalse(graph.removeEdge(src, rem));

        assertFalse(graph.removeEdge(src, invalid));
        assertFalse(graph.removeEdge(invalid, src));
        assertFalse(graph.removeEdge(invalid, invalid));
    }

    @Test
    public void testToString() throws Exception {
        System.out.print(graph);
    }
}