package dev.punchcafe.netrunners.game.phase;

import dev.punchcafe.netrunners.game.Game;
import dev.punchcafe.netrunners.game.Turn;
import dev.punchcafe.netrunners.player.Player;

@FunctionalInterface
public interface TurnPhase {
    void execute(Turn turn, Game game);
}
