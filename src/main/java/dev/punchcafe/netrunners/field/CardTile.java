package dev.punchcafe.netrunners.field;

import dev.punchcafe.netrunners.card.Card;
import dev.punchcafe.netrunners.card.SystemCard;

import java.util.List;

class CardTile {

    private static List<Class<? extends Card>> LEGAL_CLASSES = List.of(SystemCard.class);

    Card card;

    void clearCard(){
        card = null;
    }

    void setCard(Card card){
        if(!LEGAL_CLASSES.contains(card.getClass())){
            throw new RuntimeException();
        }
        this.card = card;
    }

    Card getCard(){
        return this.card;
    }
}
