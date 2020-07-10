package dev.punchcafe.netrunners.field;

import dev.punchcafe.netrunners.card.Card;
import dev.punchcafe.netrunners.card.GenericSystemCard;
import dev.punchcafe.netrunners.card.SystemCard;
import dev.punchcafe.netrunners.graph.Edge;
import dev.punchcafe.netrunners.graph.EdgeValue;
import dev.punchcafe.netrunners.graph.GraphNode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class CardTile implements GraphNode<CardTile> {

    //TODO: consider removing
    private static List<Class<? extends Card>> LEGAL_CLASSES = List.of(SystemCard.class, GenericSystemCard.class);

    private Map<EdgeValue, Edge<CardTile>> edges = new HashMap<>();

    private FieldPosition<CardTile> fieldPosition;

    private Card card;

    void setFieldPosition(int row, int index){
        fieldPosition = new FieldPosition<>(this, row, index);
    }

    void setCard(Card card){
        if(!LEGAL_CLASSES.contains(card.getClass())){
            throw new RuntimeException();
        }
        this.card = card;
    }


    void clearCard(){
        card = null;
    }

    Card getCard(){
        return this.card;
    }

    @Override
    public FieldPosition<CardTile> getFieldPosition() {
        return fieldPosition;
    }

    @Override
    public List<Edge> getEdges() {
        return null;
    }

    @Override
    public Edge getEdge(EdgeValue value) {
        return edges.get(value);
    }

    public void setEdge(Edge edge, EdgeValue value){
        edges.put(value, edge);
    }

    public String toString(){
        final String firstLines = "---------------------------\n"+
                String.format("Field Position: %d, %d\n", fieldPosition.getRow(), fieldPosition.getIndex());
        StringBuilder sb = new StringBuilder();
        System.out.println("Edges:");
        System.out.println(edges);
        for(EdgeValue value : EdgeValue.values()){
            Edge<CardTile> edge = edges.get(value);
            if(edge != null){
                FieldPosition<CardTile> position = edge.getOther(this).getFieldPosition();
                sb.append(String.format("Edge: %s, position: %d, %d\n", value.toString(), position.getRow(), position.getIndex()));
            }
        }
        return firstLines + sb.toString();
    }

}
