package dev.punchcafe.netrunners.deck;

import dev.punchcafe.netrunners.card.Card;
import dev.punchcafe.netrunners.card.SystemCard;
import dev.punchcafe.netrunners.player.Player;

public class Deck {

    final private Player owner;

    public Deck(final Player owner){
        this.owner = owner;
    }
    public Card draw() {
        final Card card = new SystemCard();
        card.setOwner(this.owner);
        return card;
    }
}
