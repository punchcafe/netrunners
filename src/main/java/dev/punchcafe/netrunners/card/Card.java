package dev.punchcafe.netrunners.card;

import dev.punchcafe.netrunners.game.Game;
import dev.punchcafe.netrunners.player.Player;

public abstract class Card {
    Player owner;
    String name;
    String description;

    // Allows cards to have a lot of power over how they work
    abstract public void playCard(Game game);

    //TODO: Add a publish event to each playCard method?

    public void setOwner(Player owner){
        this.owner = owner;
    }
    public Player getOwner() {
        return this.owner;
    }
}
