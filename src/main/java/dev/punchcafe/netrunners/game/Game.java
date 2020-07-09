package dev.punchcafe.netrunners.game;

import dev.punchcafe.netrunners.field.Field;
import dev.punchcafe.netrunners.field.FieldRow;
import dev.punchcafe.netrunners.game.phase.DrawPhase;
import dev.punchcafe.netrunners.game.phase.PlayPhase;
import dev.punchcafe.netrunners.game.phase.TurnPhase;
import dev.punchcafe.netrunners.player.Player;
import dev.punchcafe.netrunners.render.AsciiRenderer;
import dev.punchcafe.netrunners.render.Renderer;

import java.util.List;

public class Game {
    private Renderer renderer;
    private Field gameField;
    private Turn turn;
    private Player player1;
    private Player player2;

    public Game(){
        this.gameField = new Field();
        this.turn = new Turn(List.of(new DrawPhase(), new PlayPhase()));
        player1 = new Player();
        player2 = new Player();
        renderer = new AsciiRenderer();
    }

    public Player getPlayer1(){
        return player1;
    }

    public Player getPlayer2(){
        return player2;
    }

    // Returns winner
    public Player gameLoop(){
        while(this.determineWinner(gameField) == null) {
            turn.switchPlayer(turn.turnOwner == player1 ? player2 : player1);
            TurnPhase phase = turn.nextTurnPhase();
            while (phase != null) {
                if (this.determineWinner(gameField) != null) {
                    return this.determineWinner(gameField);
                }
                //Prevent double execution
                phase.execute(turn, this);
                renderer.render(this);
                phase = turn.nextTurnPhase();
            }
            if (this.determineWinner(gameField) != null) {
                return this.determineWinner(gameField);
            }
        }
        return this.determineWinner(gameField);
    }

    public Field getField(){
        return this.gameField;
    }

    private Player determineWinner(Field gameField){
        // null means no winner
        return null;
    }
}
