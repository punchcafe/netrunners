package dev.punchcafe.netrunners.field;

import dev.punchcafe.netrunners.card.Card;
import dev.punchcafe.netrunners.card.OriginNode;
import dev.punchcafe.netrunners.graph.Edge;
import dev.punchcafe.netrunners.graph.EdgeValue;
import dev.punchcafe.netrunners.player.Player;

//TODO: makae generic Field<TYPE> so can be re-used by renderer.
public class Field {

    FieldRow[] rows;

    //TODO: add support for surfacing nodes

    public Field(){
        //TODO: consider replacing internal structure with graph
        rows = new FieldRow[9];
        for(int i = -4; i < 5; i++){
            int rowNumber = i+4;
            FieldRow row = new FieldRow(5 - Math.abs(i));
            for(int i_t = 0; i_t < row.size(); i_t++){
                //Assign position numbers to tiles
                row.getCardTile(i_t).setFieldPosition(rowNumber, i_t);
            }
            rows[rowNumber] = row;
            System.out.println(rowNumber);
        }
        //TODO: iterate through and assign graph Tiles
        for(int i = 0; i < rows.length; i++){
            assignHorizontal(rows[i]);
            if(i < rows.length -1){
                System.out.println(rows[i]);
                System.out.println(rows[i+1]);
                assignDown(rows[i], rows[i+1]);
            }
        }

        //Print
        for(int i = 0; i < rows.length; i++){
            for(int j = 0; j<rows[i].size(); j++){
                System.out.println(rows[i].getCardTile(j));
            }
        }
        /**
         * Assign from the top down, i.e.
         *    Node
         *    /   \
         *    Assign Horizontal, assign down
         */
    }

    public void assignHorizontal(FieldRow row){
        for(int i = 0; i < row.size()-1; i++){
            // -1 to avoid assigning on the last node
            //Assign to the right
            final Edge<CardTile> edge = new Edge(row.getCardTile(i), row.getCardTile(i+1));
            row.getCardTile(i).setEdge(edge, EdgeValue.RIGHT);
            row.getCardTile(i+1).setEdge(edge, EdgeValue.LEFT);
        }
    };

    public void assignDown(FieldRow topRow, FieldRow bottomRow){
        if(topRow.size() < bottomRow.size()){
            for(int i = 0; i < topRow.size(); i++){
                //TODO: make this a method which specifies in deriving GraphNodes from fields?

                // Assign down, diverging
                // Bottom left will be the same row index, bottom right will be +1
                final CardTile subject = topRow.getCardTile(i);
                final CardTile bottomLeft = bottomRow.getCardTile(i);
                final CardTile bottomRight = bottomRow.getCardTile(i + 1);
                final Edge<CardTile> left = new Edge<>(subject, bottomLeft);
                final Edge<CardTile> right = new Edge<>(subject, bottomRight);
                subject.setEdge(left, EdgeValue.BOTTOM_LEFT);
                bottomLeft.setEdge(left, EdgeValue.TOP_RIGHT);
                subject.setEdge(right, EdgeValue.BOTTOM_RIGHT);
                bottomRight.setEdge(right, EdgeValue.TOP_LEFT);
            }
        } else if(topRow.size() > bottomRow.size()) {
            for(int i = 1; i < topRow.size() -1; i++){
                //TODO: refactor into pretty
                //We ignore the first and last since they will have empty left and right values
                final CardTile subject = topRow.getCardTile(i);
                final CardTile bottomLeft = bottomRow.getCardTile(i-1);
                final CardTile bottomRight = bottomRow.getCardTile(i);
                final Edge<CardTile> left = new Edge<>(subject, bottomLeft);
                final Edge<CardTile> right = new Edge<>(subject, bottomRight);
                subject.setEdge(left, EdgeValue.BOTTOM_LEFT);
                bottomLeft.setEdge(left, EdgeValue.TOP_RIGHT);
                subject.setEdge(right, EdgeValue.BOTTOM_RIGHT);
                bottomRight.setEdge(right, EdgeValue.TOP_LEFT);
            }
            final CardTile firstTile = topRow.getCardTile(0);
            final CardTile lastTile = topRow.getCardTile(topRow.size() -1);
            final CardTile firstBottomTile = bottomRow.getCardTile(0);
            final CardTile lastBottomTile = bottomRow.getCardTile(bottomRow.size() - 1);

            final Edge<CardTile> firstEdge = new Edge<>(firstTile, firstBottomTile);
            final Edge<CardTile> lastEdge = new Edge<>(lastTile, lastBottomTile);

            firstTile.setEdge(firstEdge, EdgeValue.BOTTOM_RIGHT);
            firstBottomTile.setEdge(firstEdge, EdgeValue.TOP_LEFT);
            lastTile.setEdge(lastEdge, EdgeValue.BOTTOM_LEFT);
            lastBottomTile.setEdge(lastEdge, EdgeValue.TOP_RIGHT);
            //assign down, converging
            //Bottom left will be -1, bottom right will the same
        }
    }

    public FieldPosition<OriginNode> getPlayerOriginNode(Player player){
        //TODO: implement this
        return null;
    }

    public int numberOfRows(){
        return rows.length;
    }

    public int getRowLength(int rowNumber){
        return rows[rowNumber].size();
    }

    public FieldRow getFieldRow(int row){
        return rows[row];
    }

    public Card getCard(int row, int index){
        return this.rows[row].getCard(index);
    }

    public void layCard(Card card, int row, int index){
        this.rows[row].setCard(card, index);
    };
}
