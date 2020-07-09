package dev.punchcafe.netrunners.field;

import dev.punchcafe.netrunners.card.Card;

import java.util.List;

public class Field {

    FieldRow[] rows;

    public Field(){
        rows = new FieldRow[9];
        for(int i = -4; i < 5; i++){
            rows[i+4] = new FieldRow(5 - Math.abs(i));
        }
    }

    public void layCard(Card card, int row, int index){
        this.rows[row].setCard(card, index);
    };
}
