package dev.punchcafe.netrunners.card;

import dev.punchcafe.netrunners.game.Game;
import dev.punchcafe.netrunners.player.Player;

public class OriginNode extends Card {

    int row;
    int index;

    public OriginNode(Player owner, int row, int index){
        this.owner = owner;
        this.row = row;
        this.index = index;
    }

    @Override
    public void playCard(Game game) {
        game.getField().setContents(this, row, index);
        //can only be played once at the beginning of the game automatically by each player
    }
}
