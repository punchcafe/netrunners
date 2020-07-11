package dev.punchcafe.netrunners.card;

import dev.punchcafe.netrunners.game.GameField;
import dev.punchcafe.netrunners.game.Game;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class SystemCard extends Card {
    Pattern tileChoicePatter = Pattern.compile("(\\d):(\\d)");
    @Override
    public void playCard(Game game) {
        //isLegalMove(game.getField(), )
      //  this.playCardImp(game);
        String tileChoice = owner.getPlayerInput("choose a tile");
        Matcher matcher = tileChoicePatter.matcher(tileChoice);
        //TODO: add legality check
        //TODO: add Origin Node insertion
        //TODO: add win check condition
        //TODO: add online node render path

        matcher.find();
        int playerRowChoice = Integer.parseInt(matcher.group(1));
        int playerIndexChoice = Integer.parseInt(matcher.group(2));
        if(isLegalMove(game.getField(), playerRowChoice, playerIndexChoice)){
            game.getField().setContents(this, playerRowChoice, playerIndexChoice);
        } else {
            //TODO: replace with validation violation messages
            throw new RuntimeException("Illegal Move");
        }
        playCardImp(game);
    }
//TODO: make abstract, make implementation abstract class which playCard uses
    public boolean isLegalMove(GameField field, int row, int index){
        return true;
    };

    public abstract void playCardImp(Game game);
}
