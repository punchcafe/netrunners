package dev.punchcafe.netrunners.game;

import dev.punchcafe.netrunners.card.OriginNode;
import dev.punchcafe.netrunners.game.phase.DrawPhase;
import dev.punchcafe.netrunners.game.phase.PlayPhase;
import dev.punchcafe.netrunners.game.phase.TurnPhase;
import dev.punchcafe.netrunners.player.Player;
import dev.punchcafe.netrunners.render.AsciiRenderer;
import dev.punchcafe.netrunners.render.Renderer;

import java.util.List;

public class Game {
    private Renderer renderer;
    private GameField gameField;
    private Turn turn;
    private Player player1;
    private Player player2;

    public Game(){
        this.gameField = new GameField();
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

    public void setupGame(){
        //TODO: Load deck from config files
        //TODO: Load Player hands
        player1.setOriginNode(new OriginNode(player1, 0, 0));
        //TODO: look at decoupling dependencies here
        player2.setOriginNode(new OriginNode(player2, 8, 0));
        player1.getOriginNode().playCard(this);
        player2.getOriginNode().playCard(this);
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

    public GameField getField(){
        return this.gameField;
    }

    private Player determineWinner(GameField gameField){
        // null means no winner
        return null;
    }
}
