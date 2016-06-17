package com.sleepycoders.jlib.algorithm.graph;

import com.sleepycoders.jlib.datastructure.graph.IGraph;

import java.util.List;

/**
 * Created by joshi on 17.06.2016.
 */
public interface IGraphSearch<TVertex> {
    boolean containsPath(IGraph<TVertex> graph, TVertex src, TVertex dst);
    List<TVertex> getPath(IGraph<TVertex> graph, TVertex src, TVertex dst);
}
