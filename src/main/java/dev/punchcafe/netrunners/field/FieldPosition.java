package dev.punchcafe.netrunners.field;

import dev.punchcafe.netrunners.card.Card;

public class FieldPosition<T> {
    T card;
    int row;
    int index;

    public FieldPosition(T card, int row, int index){
        this.card = card;
        this.row = row;
        this.index = index;
    }

    public int getRow(){
        return this.row;
    }

    public int getIndex(){
        return this.index;
    }

    public T getCard(){
        return this.card;
    }
}
