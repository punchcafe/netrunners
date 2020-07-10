package dev.punchcafe.netrunners.field;

import dev.punchcafe.netrunners.card.Card;

public class FieldRow {
    private final CardTile[] tiles;

    public FieldRow(int size){
        tiles = new CardTile[size];
        for(int i = 0; i < tiles.length; i++){
            tiles[i] = new CardTile();
        }
    }

    public int size(){
        return tiles.length;
    }

    public CardTile getCardTile(int index){
        return tiles[index];
    }

    public Card getCard(int index){
        if(index >= tiles.length){
            throw new RuntimeException();
        }
        return tiles[index].getCard();
    }

    public void setCard(Card card, int index){
        if(index >= tiles.length){
            throw new RuntimeException();
        }
        tiles[index].setCard(card);
    }
}
