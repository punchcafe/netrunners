package dev.punchcafe.netrunners.card;

import dev.punchcafe.netrunners.field.FieldPosition;
import dev.punchcafe.netrunners.game.GameField;
import dev.punchcafe.netrunners.game.Game;
import dev.punchcafe.netrunners.util.OnlineChecks;

import java.util.LinkedList;
import java.util.List;
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
        final List<String> moveViolations = isLegalMove(game.getField(), playerRowChoice, playerIndexChoice);
        if(moveViolations.isEmpty()){
            game.getField().setContents(this, playerRowChoice, playerIndexChoice);
        } else {
            //TODO: replace with validation violation messages
            throw new RuntimeException(String.format("Illegal Move: %s", moveViolations));
        }
        playCardImp(game);
    }
//TODO: make abstract, make implementation abstract class which playCard uses
    public List<String> isLegalMove(GameField field, int row, int index){
        final List<String> violations = new LinkedList<>();
        if(!OnlineChecks.isOnlineAccess(new FieldPosition<>(null, row, index), this.owner, field)){
            violations.add("Node is not online");
        }
        if(field.getTile(row, index).getContents() != null){
            violations.add("Card Already in node");
        }
        return violations;
    };

    public abstract void playCardImp(Game game);
}
