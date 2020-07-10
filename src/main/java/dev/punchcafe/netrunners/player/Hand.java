package dev.punchcafe.netrunners.player;

import dev.punchcafe.netrunners.card.Card;
import dev.punchcafe.netrunners.game.Game;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    public List<Card> cards = new ArrayList<>();

    public void playCardFromHand(int cardNumber, Game game){
        //TODO: handle error
        cards.get(cardNumber).playCard(game);
    }
}
