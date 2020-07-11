package dev.punchcafe.netrunners.field.graph;

import java.util.List;


/**
 * Wrapping the field as a graph will allow easier traversal when calculating things which need to be connected.
 * It abstracts the coordinate maths for working out edges
 */
public interface GraphNode<T> {

    List<Edge<T>> getEdges();

    Edge<T> getEdge(EdgeValue value);
}
