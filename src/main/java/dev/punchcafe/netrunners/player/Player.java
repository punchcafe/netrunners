package dev.punchcafe.netrunners.player;

import dev.punchcafe.netrunners.deck.Deck;

import java.util.Scanner;

public class Player {
    String displayName;
    Hand hand;
    OriginNode originNode;
    PlayerInputStream inputStream;
    Deck deck;

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

    public Hand getHand() {
        return hand;
    }

    public Deck getDeck() {
        return this.deck;
    }
}
