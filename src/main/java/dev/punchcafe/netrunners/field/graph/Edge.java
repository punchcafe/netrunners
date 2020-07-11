package dev.punchcafe.netrunners.field.graph;

public class Edge<T> {

    private T node1;
    private T node2;

    public Edge(T node1, T node2){
        this.node1 = node1;
        this.node2 = node2;
    }

    public T getOther(T subject){
        if(subject == this.node1){
            return this.node2;
        } else if(subject == this.node2){
            return this.node1;
        } else {
            return null;
        }
    }
}
