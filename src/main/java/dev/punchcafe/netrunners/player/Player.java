package dev.punchcafe.netrunners.player;

import dev.punchcafe.netrunners.card.OriginNode;
import dev.punchcafe.netrunners.deck.Deck;
import dev.punchcafe.netrunners.game.GameField;

import java.util.Scanner;

public class Player {
    //TODO: make generic with Net runners specific class

    String displayName;
    Hand hand;
    PlayerInputStream inputStream;
    Deck deck;
    OriginNode originNode;

    /**
     * Calculates the resource score for a player, based on how deep into the other side of the field their
     * network penetrates. This score corresponds to the number of cards a player may draw per turn.
     * @param field
     * @return
     */
    public int calculateResourceScore(GameField field){
        return 1;
    }

    public Player(){
        Scanner scanner = new Scanner(System.in);
        this.hand = new Hand();
        this.inputStream = () -> scanner.nextLine();
        this.deck = new Deck(this);
    }

    public String getPlayerInput(String prompt) {
        System.out.println(prompt);
        return inputStream.getPlayerInput();
    }

    public void sendToPlayerOutput(String message){
        //TODO: make clever
        System.out.println(message);
    }

    public void draw(){
        this.draw(1);
    }

    public void draw(int times) {
        for (int i = 0; i < times; i++) {
            hand.cards.add(this.deck.draw());
        }
    }

    public void setOriginNode(OriginNode originNode){
        this.originNode = originNode;
    }

    public OriginNode getOriginNode(){
        return this.originNode;
    }

    public Hand getHand() {
        return hand;
    }

    public Deck getDeck() {
        return this.deck;
    }
}
