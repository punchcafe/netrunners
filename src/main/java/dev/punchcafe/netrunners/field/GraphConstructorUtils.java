package dev.punchcafe.netrunners.field;

import dev.punchcafe.netrunners.field.graph.Edge;
import dev.punchcafe.netrunners.field.graph.EdgeValue;

public class GraphConstructorUtils {


    static void assignDown(FieldRow topRow, FieldRow bottomRow){
        if(topRow.size() < bottomRow.size()){
            for(int i = 0; i < topRow.size(); i++){
                //TODO: make this a method which specifies in deriving GraphNodes from fields?

                // Assign down, diverging
                // Bottom left will be the same row index, bottom right will be +1
                final Tile subject = topRow.getTile(i);
                final Tile bottomLeft = bottomRow.getTile(i);
                final Tile bottomRight = bottomRow.getTile(i + 1);
                final Edge<Tile> left = new Edge<>(subject, bottomLeft);
                final Edge<Tile> right = new Edge<>(subject, bottomRight);
                subject.setEdge(left, EdgeValue.BOTTOM_LEFT);
                bottomLeft.setEdge(left, EdgeValue.TOP_RIGHT);
                subject.setEdge(right, EdgeValue.BOTTOM_RIGHT);
                bottomRight.setEdge(right, EdgeValue.TOP_LEFT);
            }
        } else if(topRow.size() > bottomRow.size()) {
            for(int i = 1; i < topRow.size() -1; i++){
                //TODO: refactor into pretty
                //We ignore the first and last since they will have empty left and right values
                final Tile subject = topRow.getTile(i);
                final Tile bottomLeft = bottomRow.getTile(i-1);
                final Tile bottomRight = bottomRow.getTile(i);
                final Edge<Tile> left = new Edge<>(subject, bottomLeft);
                final Edge<Tile> right = new Edge<>(subject, bottomRight);
                subject.setEdge(left, EdgeValue.BOTTOM_LEFT);
                bottomLeft.setEdge(left, EdgeValue.TOP_RIGHT);
                subject.setEdge(right, EdgeValue.BOTTOM_RIGHT);
                bottomRight.setEdge(right, EdgeValue.TOP_LEFT);
            }
            final Tile firstTile = topRow.getTile(0);
            final Tile lastTile = topRow.getTile(topRow.size() -1);
            final Tile firstBottomTile = bottomRow.getTile(0);
            final Tile lastBottomTile = bottomRow.getTile(bottomRow.size() - 1);

            final Edge<Tile> firstEdge = new Edge<>(firstTile, firstBottomTile);
            final Edge<Tile> lastEdge = new Edge<>(lastTile, lastBottomTile);

            firstTile.setEdge(firstEdge, EdgeValue.BOTTOM_RIGHT);
            firstBottomTile.setEdge(firstEdge, EdgeValue.TOP_LEFT);
            lastTile.setEdge(lastEdge, EdgeValue.BOTTOM_LEFT);
            lastBottomTile.setEdge(lastEdge, EdgeValue.TOP_RIGHT);
            //assign down, converging
            //Bottom left will be -1, bottom right will the same
        }
    }

    public static void assignHorizontal(FieldRow row){
        for(int i = 0; i < row.size()-1; i++){
            // -1 to avoid assigning on the last node
            //Assign to the right
            final Edge<Tile> edge = new Edge(row.getTile(i), row.getTile(i+1));
            row.getTile(i).setEdge(edge, EdgeValue.RIGHT);
            row.getTile(i+1).setEdge(edge, EdgeValue.LEFT);
        }
    };
}
