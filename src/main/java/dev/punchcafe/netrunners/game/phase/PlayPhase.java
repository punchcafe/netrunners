package dev.punchcafe.netrunners.game.phase;

import dev.punchcafe.netrunners.card.Card;
import dev.punchcafe.netrunners.game.Game;
import dev.punchcafe.netrunners.game.Turn;

public class PlayPhase implements TurnPhase {
    @Override
    public void execute(Turn turn, Game game) {
        for(Card card : turn.currentPlayer().getHand().cards){
            turn.currentPlayer().sendToPlayerOutput("a card");
        }
        int choice = Integer.parseInt(turn.currentPlayer().getPlayerInput("pick a card"));
        turn.currentPlayer().getHand().cards.get(choice).playCard(game);
    }
}
