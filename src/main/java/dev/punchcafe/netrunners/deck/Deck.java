package dev.punchcafe.netrunners.deck;

import dev.punchcafe.netrunners.card.Card;
import dev.punchcafe.netrunners.card.GenericSystemCard;
import dev.punchcafe.netrunners.player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Deck {

    final private Player owner;
    final private Stack<Card> cards;

    public Deck(final Player owner){
        this.owner = owner;
        List<Card> generartedCards = new ArrayList<>();
        for(int i = 0; i < 40; i++){
            generartedCards.add(new GenericSystemCard());
        }
        this.cards = loadCardsToStackAndSetOwner(generartedCards, this.owner);
    }

    public Deck(final List<Card> cards, Player owner){
        this.owner = owner;
        this.cards = loadCardsToStackAndSetOwner(cards, owner);
    }
    public Card draw() {
        return cards.pop();
    }

    private Stack<Card> loadCardsToStackAndSetOwner(List<Card> cards, Player owner){
        final Stack<Card> stack = new Stack<>();
        for(int i = 0; i < cards.size(); i++){
            final Card card = cards.get(i);
            card.setOwner(owner);
            stack.push(card);
        }
        return stack;
    }
}
