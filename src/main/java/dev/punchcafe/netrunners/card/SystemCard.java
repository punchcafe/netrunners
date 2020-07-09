package dev.punchcafe.netrunners.card;

import dev.punchcafe.netrunners.game.Game;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SystemCard extends Card {
    Pattern tileChoicePatter = Pattern.compile("(\\d):(\\d)");
    @Override
    public void playCard(Game game) {
        String tileChoice = owner.getPlayerInput("choose a tile");
        Matcher matcher = tileChoicePatter.matcher(tileChoice);
        matcher.find();
        game.getField().layCard(this, Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)));
    }
}
