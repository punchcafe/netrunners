package dev.punchcafe.netrunners.game.phase;

import dev.punchcafe.netrunners.game.Game;
import dev.punchcafe.netrunners.game.Turn;

public class DrawPhase implements TurnPhase {
    @Override
    public void execute(Turn turn, Game game) {
        turn.currentPlayer().draw();
    }
}
