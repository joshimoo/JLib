package com.sleepycoders.jlib.algorithm.graph;

import com.sleepycoders.jlib.datastructure.graph.DirectedDenseGraph;
import com.sleepycoders.jlib.datastructure.graph.IGraph;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

/**
 * Tests Breath First Search on an IGraph Datastructure
 * @author Joshua Moody (joshimoo@hotmail.de)
 */
public class BreadthFirstSearchTest {

    IGraph<String> graph;
    BreadthFirstSearch<String> searcher;
    List<String> nodes = Arrays.asList("A", "B", "C", "D", "E", "F", "G", "X", "Y", "Z");

    public IGraph<String> createGraph(int capacity) {
        return new DirectedDenseGraph<>(capacity);
    }

    public BreadthFirstSearch<String> createSearcher() {
        return new BreadthFirstSearch<>();
    }

    @Before
    public void setUp() throws Exception {
        graph = createGraph(nodes.size() * 2);
        searcher = createSearcher();
        nodes.stream().forEach(graph::addVertex);

        // we add edges between
        // A <-> B
        graph.addEdge(nodes.get(0), nodes.get(1));
        graph.addEdge(nodes.get(1), nodes.get(0));

        // A <-> C
        graph.addEdge(nodes.get(0), nodes.get(2));
        graph.addEdge(nodes.get(2), nodes.get(0));

        // A -> D
        graph.addEdge(nodes.get(0), nodes.get(3));

        // B -> C
        graph.addEdge(nodes.get(1), nodes.get(2));

        // C -> D
        graph.addEdge(nodes.get(2), nodes.get(3));

        // D <-> E
        graph.addEdge(nodes.get(3), nodes.get(4));

        // D <-> F
        graph.addEdge(nodes.get(3), nodes.get(5));

        // F <-> G
        graph.addEdge(nodes.get(5), nodes.get(6));

        // X, Y, Z are a separate island
        // X <-> Y
        graph.addEdge(nodes.get(nodes.size() - 3), nodes.get(nodes.size() - 2));

        // Y -> Z
        graph.addEdge(nodes.get(nodes.size() - 2), nodes.get(nodes.size() - 1));
    }

    @Test
    public void testContainsPath() throws Exception {
        // A <-> C ? TRUE
        assertTrue(searcher.containsPath(graph, nodes.get(0), nodes.get(2)));

        // A  -> G ? TRUE
        assertTrue(searcher.containsPath(graph, nodes.get(0), nodes.get(6)));

        // G  -> A ? FALSE // DIRECTED GRAPH
        assertFalse(searcher.containsPath(graph, nodes.get(6), nodes.get(0)));

        // Z -> ALL ? FALSE // ISLAND
        for (int i = 0; i < nodes.size() - 1 ; i++) {
            assertFalse(searcher.containsPath(graph, nodes.get(nodes.size() - 1), nodes.get(i)));
        }
    }

    @Test
    public void testContainsPathWithMaximumDistance() throws Exception {
        // A <-> C, 1 ? TRUE
        assertTrue(searcher.containsPath(graph, nodes.get(0), nodes.get(2), 1));

        // A <-> C, 0 ? FALSE
        assertFalse(searcher.containsPath(graph, nodes.get(0), nodes.get(2), 0));

        // A  -> G, 3 ? TRUE
        assertTrue(searcher.containsPath(graph, nodes.get(0), nodes.get(6), Integer.MAX_VALUE));
        assertTrue(searcher.containsPath(graph, nodes.get(0), nodes.get(6), 3));

        // A  -> G, 2 ? FALSE
        assertFalse(searcher.containsPath(graph, nodes.get(0), nodes.get(6), 2));

        // G  -> A, ? FALSE // DIRECTED GRAPH
        assertFalse(searcher.containsPath(graph, nodes.get(6), nodes.get(0), 0));
        assertFalse(searcher.containsPath(graph, nodes.get(6), nodes.get(0), Integer.MAX_VALUE));
    }

    @Test
    public void testCalculatePathsFrom() throws Exception {
        Map<String, String> cameFrom = searcher.calculatePathsFrom(graph, "X");
        Map<String, String> expected = new HashMap<>();
        expected.put("X", null); // "X" has no predecessor
        expected.put("Y", "X");
        expected.put("Z", "Y");

        // using maps equals method
        // TODO: check with a more complicated sub graph
        assertEquals(expected, cameFrom);
    }

    @Test
    public void testGetPath() throws Exception {
        // using Lists.equals method, which is defined as:
        // two lists are defined to be equal if they contain the same elements in the same order
        // TODO: consider using hamCrest instead of equals

        // A -> D -> F -> G // since it's the shortest from A to G
        List<String> path = searcher.getPath(graph, nodes.get(0), nodes.get(6));
        List<String> expected = Arrays.asList("A", "D", "F", "G");
        assertEquals(expected, path);

        // G -> A does not exists // directed graph
        path = searcher.getPath(graph, "G", "A");
        expected = Collections.emptyList();
        assertEquals(expected, path);

        // empty since no path from "X" to "A"
        path = searcher.getPath(graph, "X", "A");
        expected = Collections.emptyList();
        assertEquals(expected, path);
    }
}