package dev.punchcafe.netrunners.field;

import dev.punchcafe.netrunners.card.Card;

import java.util.List;

public class Field {

    FieldRow[] rows;

    public Field(){
        rows = new FieldRow[9];
        for(int i = -4; i < 5; i++){
            rows[i+4] = new FieldRow(5 - Math.abs(i));
            System.out.println(i+4);
            System.out.println(5 - Math.abs(i));
        }
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
