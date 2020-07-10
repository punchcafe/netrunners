package dev.punchcafe.netrunners.render;

import dev.punchcafe.netrunners.card.Card;
import dev.punchcafe.netrunners.field.FieldRow;
import dev.punchcafe.netrunners.game.Game;
import dev.punchcafe.netrunners.player.Player;
import dev.punchcafe.netrunners.util.OnlineChecks;

import java.io.IOException;
import java.util.Map;

public class AsciiRenderer implements Renderer {

    public enum PlayerIdentifier {
        ONE, TWO
    }

    Map<Player, PlayerIdentifier> playerIdentifierMap;

    static int CARD_WIDTH = 3;
    static String FIELD_NOISE = "▒";
    static String PLAYER_1_SYMBOL = "Φ";
    static String PLAYER_2_SYMBOL = "Θ";
    static int TOTAL_WIDTH = 33;

    @Override
    public void render(Game game) {
        try {
            Runtime.getRuntime().exec("clear");
        } catch (IOException e) {
            e.printStackTrace();
        }

        playerIdentifierMap = Map.of(game.getPlayer1(), PlayerIdentifier.ONE, game.getPlayer2(), PlayerIdentifier.TWO);
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < game.getField().numberOfRows(); i++) {
            output.append(renderRow(game.getField().getFieldRow(i)));
        }
        System.out.println(output.toString());
        System.out.println(String.format("Online nodes: %d",
                OnlineChecks.allOnlineNodesFromOrigin(
                        game.getPlayer1().getOriginNode(),
                        game.getField()).size()
        ));
        System.out.println(getHand(game.getPlayer1()));
    }

    public String getHand(Player player){
        StringBuilder[] stringBuilders = {new StringBuilder(),
                new StringBuilder(),
                new StringBuilder(),
                new StringBuilder(),
                new StringBuilder()};

        for(int cardNumber = 0; cardNumber < player.getHand().cards.size(); cardNumber++){
            String[] cardRender = renderCardInHandSements(player.getHand().cards.get(cardNumber), cardNumber);
            for(int i = 0; i < stringBuilders.length; i++){
                stringBuilders[i].append(cardRender[i]);
            }
        };
        stringBuilders[0].append("\n");
        for(int i = 1; i < stringBuilders.length; i++){
            stringBuilders[0].append(stringBuilders[i].toString() + "\n");
        }
        return stringBuilders[0].toString();
    }

    public String[] renderCardInHandSements(Card card, int position){
        String renderedNumber = String.format("║ No. %d   ║", position);
        return new String[]{"╔═════════╗",
                renderedNumber,
                "║         ║",
                "║         ║",
                "╚═════════╝"};
    }

    public String[] singleCardRowSegments(PlayerIdentifier identifier) {
        if (identifier == null) {
            return new String[]{"░░░", "░░░", "░░░"};
        }
        if (identifier == PlayerIdentifier.ONE) {
            return new String[]{"   ", " " + PLAYER_1_SYMBOL + " ", "   "};
        }
        if (identifier == PlayerIdentifier.TWO) {
            return new String[]{"   ", " " + PLAYER_2_SYMBOL + " ", "   "};
        }
        throw new RuntimeException();
    }

    public String renderRow(FieldRow row) {
        final int widthOfRow = row.size() * CARD_WIDTH;
        final int displacement = (TOTAL_WIDTH - widthOfRow) / 2;
        System.out.println(String.format("width: %d", widthOfRow));
        System.out.println(String.format("displacement: %d", displacement));
        StringBuilder topRow = new StringBuilder();
        StringBuilder middleRow = new StringBuilder();
        StringBuilder bottomRow = new StringBuilder();
        // Noise to cards
        for (int i = 0; i < displacement; i++) {
            topRow.append(FIELD_NOISE);
            middleRow.append(FIELD_NOISE);
            bottomRow.append(FIELD_NOISE);
        }
        // Cards
        for (int i = 0; i < row.size(); i++) {
            PlayerIdentifier identifier;
            if (row.getCard(i) == null) {
                identifier = null;
            } else {
                identifier = playerIdentifierMap.get(row.getCard(i).getOwner());
            }
            String[] cardRender = singleCardRowSegments(identifier);
            System.out.println("you're logging cards");
            System.out.println(cardRender);
            topRow.append(cardRender[0]);
            middleRow.append(cardRender[1]);
            bottomRow.append(cardRender[2]);
        }
        // Other background
        for (int i = 0; i < displacement; i++) {
            topRow.append(FIELD_NOISE);
            middleRow.append(FIELD_NOISE);
            bottomRow.append(FIELD_NOISE);
        }
        return topRow.toString() + "\n" + middleRow.toString() + "\n" + bottomRow.toString() + "\n";
    }
}
