package dev.punchcafe.netrunners.game.phase;

import dev.punchcafe.netrunners.card.Card;
import dev.punchcafe.netrunners.game.Game;
import dev.punchcafe.netrunners.game.Turn;

public class PlayPhase implements TurnPhase {
    @Override
    public void execute(Turn turn, Game game) {
        for(Card card : turn.currentPlayer().getHand().cards){
            //TODO: remove for renderer
            turn.currentPlayer().sendToPlayerOutput("a card");
        }
        int choice = Integer.parseInt(turn.currentPlayer().getPlayerInput("pick a card"));
        //TODO: move method from hand to player
        turn.currentPlayer().getHand().playCardFromHand(choice, game);
    }
}
