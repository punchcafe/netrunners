package dev.punchcafe.netrunners.graph;

import dev.punchcafe.netrunners.card.Card;
import dev.punchcafe.netrunners.field.FieldPosition;

import java.util.List;

//TODO: add to card tiles
//TODO: wrap field in graph

/**
 * Wrapping the field as a graph will allow easier traversal when calculating things which need to be connected.
 * It abstracts the coordinate maths for working out edges
 */
public interface GraphNode<T> {

    FieldPosition<T> getFieldPosition();

    List<Edge> getEdges();

    Edge getEdge(EdgeValue value);
}
