package com.sleepycoders.jlib.algorithm.graph;
import com.sleepycoders.jlib.datastructure.graph.IGraph;

import java.util.*;

/**
 * Breath First Search on an IGraph Datastructure
 * - can be used to see if their is a path from x to y
 * - can be used to calculate all paths from a single position (single src, all dst)
 * - can be used to calculate all paths to a single position (all src, single dst)
 * - can be used to find all paths with a maximum distance (limited movement range)
 * @author Joshua Moody (joshimoo@hotmail.de)
 */
public class BreadthFirstSearch<TVertex> implements IGraphSearch<TVertex> {

    /**
     * runs a BFS search to see if their is any path from src to dst
     */
    @Override
    public boolean containsPath(IGraph<TVertex> graph, TVertex src, TVertex dst) {
        // no path will ever be bigger than MAX_VALUE since it would wrap around.
        // therefore it's guaranteed to consider all paths
        return containsPath(graph, src, dst, Integer.MAX_VALUE);
    }

    /**
     * runs a BFS search to see if their is a path from src to dst with length <= maxDistance
     */
    public boolean containsPath(IGraph<TVertex> graph, TVertex src, TVertex dst, int maxDistance) {
        // queue (fifo) to keep track of which nodes still need to be seen
        Queue<TVertex> toVisit = new LinkedList<>();
        Map<TVertex, Integer> distance = new HashMap<>();

        // add our src, position to the queue and seen set
        toVisit.add(src);
        distance.put(src, 0);
        while(!toVisit.isEmpty()) {
            TVertex current = toVisit.poll();
            int distanceToCurrent = distance.get(current);

            // check exit conditions
            if(distanceToCurrent > maxDistance) { return false; }
            if(current.equals(dst)) { return true; }

            // we add the neighbour already to the seen set,
            // so we don't end up adding him multiple times to the queue,
            graph.neighbours(current).stream().filter(v -> !distance.containsKey(v)).forEach(v -> {
                        toVisit.add(v);
                        distance.put(v, distanceToCurrent + 1);
                    }
            );
        }

        // no path, with maxDistance
        return false;
    }

    /**
     * calculates all paths from src
     * the returned map is structured
     * so that you can go from each Vertex
     * that has a path to src by following it's predecessor
     *
     * example:
     * reconstruct a path from a node to our src node by:
     * walking the map from node-->map[node]--> map[map[node]] ...
     * till we reach the src node
     */
    public Map<TVertex, TVertex> calculatePathsFrom(IGraph<TVertex> graph, TVertex src) {
        // queue (fifo) to keep track of which nodes still need to be seen
        Queue<TVertex> toVisit = new LinkedList<>();
        Set<TVertex> seen = new HashSet<>();
        Map<TVertex, TVertex> paths = new HashMap<>();

        // add our src, position to the queue and seen set
        toVisit.add(src);
        seen.add(src);
        paths.put(src, null); // src has no predecessor
        while(!toVisit.isEmpty()) {
            TVertex current = toVisit.poll();

            // we add the neighbour already to the seen set,
            // so we don't end up adding him multiple times to the queue,
            graph.neighbours(current).stream().filter(v -> !seen.contains(v)).forEach(v -> {
                        toVisit.add(v);
                        seen.add(v);

                        // neighbour v's predecessor is the current node
                        // since we want all paths from src, this will allow us to
                        // reconstruct a path from each node to our dst node by:
                        // walking the map from dst-->map[dst]--> map[map[dst]] ...
                        // till we reach the src node
                        paths.put(v, current);
                    }
            );
        }

        return paths;
    }

    /***
     * returns a path from src to dst or an empty path if none exists
     */
    @Override
    public List<TVertex> getPath(IGraph<TVertex> graph, TVertex src, TVertex dst) {

        // we calculate all paths from src,
        // then we can start at dst and follow the elements that lead to src
        // this works even for directed graphs, (even if the reverse direction is not set)
        // since we evaluate the graph in the correct direction src --> dst
        // and now we just evaluate that path backwards
        Map<TVertex, TVertex> paths = calculatePathsFrom(graph, src);
        List<TVertex> path = new ArrayList<>();

        // make sure their is a path between src -> dst
        if (!paths.containsKey(dst)) { return path; }

        // add path segments till we reach src
        TVertex next = dst;
        while(next != null) {
            path.add(next);
            next = paths.get(next);
        }

        // it's probably quicker to reverse this array list once
        // instead of having to carry the linked list extra iteration cost
        // TODO: this depends on if multiple units consume this path etc
        // TODO: profile this, if it becomes an issue
        Collections.reverse(path);
        return path;
    }


}
