package dev.punchcafe.netrunners.field;

import dev.punchcafe.netrunners.field.graph.Edge;
import dev.punchcafe.netrunners.field.graph.EdgeValue;
import dev.punchcafe.netrunners.field.graph.GraphNode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Tile<T> implements GraphNode<Tile> {

    private Map<EdgeValue, Edge<Tile>> edges = new HashMap<>();

    private T contents;

    void setContents(T obj){
        this.contents = obj;
    }

    void clearContents(){
        this.contents = null;
    }

    public T getContents(){
        return this.contents;
    }

    @Override
    public List<Edge<Tile>> getEdges() {
        return List.copyOf(edges.values());
    }

    @Override
    public Edge getEdge(EdgeValue value) {
        return edges.get(value);
    }

    void setEdge(Edge edge, EdgeValue value){
        edges.put(value, edge);
    }

    public String toString(){
        /*
        StringBuilder sb = new StringBuilder();
        System.out.println("Edges:");
        System.out.println(edges);
        for(EdgeValue value : EdgeValue.values()){
            Edge<Tile> edge = edges.get(value);
            if(edge != null){
                FieldPosition<Tile> position = edge.getOther(this).getFieldPosition();
                sb.append(String.format("Edge: %s, position: %d, %d\n", value.toString(), position.getRow(), position.getIndex()));
            }
        }
        return firstLines + sb.toString();
         */
        return "";
    }

}
