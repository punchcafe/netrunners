package dev.punchcafe.netrunners.field;

import dev.punchcafe.netrunners.card.Card;

class FieldRow {
    private final CardTile[] tiles;

    public FieldRow(int size){
        tiles = new CardTile[size];
        for(int i = 0; i < tiles.length; i++){
            tiles[i] = new CardTile();
        }
    }

    public void setCard(Card card, int index){
        if(index >= tiles.length){
            throw new RuntimeException();
        }
        tiles[index].setCard(card);
    }
}
