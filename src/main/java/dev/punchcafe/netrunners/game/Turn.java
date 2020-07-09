package dev.punchcafe.netrunners.game;

import dev.punchcafe.netrunners.game.phase.TurnPhase;
import dev.punchcafe.netrunners.player.Player;

import java.util.Iterator;
import java.util.List;

public class Turn {
    Player turnOwner;
    int turnPhaseIndex = 0;
    final List<TurnPhase> phases;
    Iterator<TurnPhase> phase;


    public Turn(final List<TurnPhase> phases){
        //Immutable
        this.phases = List.copyOf(phases);
    }

    public TurnPhase nextTurnPhase(){
        if(this.turnPhaseIndex == phases.size()){
            return null;
        }
        TurnPhase nextPhase = phases.get(turnPhaseIndex);
        turnPhaseIndex++;
        return nextPhase;
    }

    public Player currentPlayer(){
        return this.turnOwner;
    }

    void switchPlayer(Player player){
        turnOwner = player;
        turnPhaseIndex = 0;
    }
    //TODO: consider where to put the game over check
}
