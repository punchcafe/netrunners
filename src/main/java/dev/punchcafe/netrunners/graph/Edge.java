package dev.punchcafe.netrunners.graph;

import dev.punchcafe.netrunners.card.Card;

public class Edge<T> {

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
            System.out.println("warning!");
            System.out.println(this);
            return null;
        }
    }

    T node1;
    T node2;
}
