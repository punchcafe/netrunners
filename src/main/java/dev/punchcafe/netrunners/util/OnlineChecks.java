package dev.punchcafe.netrunners.util;

import dev.punchcafe.netrunners.card.Card;
import dev.punchcafe.netrunners.card.OriginNode;
import dev.punchcafe.netrunners.field.CardTile;
import dev.punchcafe.netrunners.field.Field;
import dev.punchcafe.netrunners.field.FieldPosition;
import dev.punchcafe.netrunners.graph.Edge;
import dev.punchcafe.netrunners.player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OnlineChecks {
    // TODO: checker for detemining if a card in a slot is connected to a node
    public static boolean isOnline(int row, int index, OriginNode node) {
        return true;
    }

    public static List<FieldPosition> allOnlineNodesFromOrigin(OriginNode node, Field field) {
        final List<FieldPosition<Card>> nodes = new ArrayList<>();
        final CardTile nodeTile = getTileForCard(node, field);
        return recursiveFunction(nodeTile, new ArrayList<>(), node.getOwner())
                .stream()
                .map(CardTile::getFieldPosition).collect(Collectors.toList());
    }

    public static List<CardTile> recursiveFunction(CardTile tile, List<CardTile> path, Player owner) {
        if (path.contains(tile) || tile.getCard() == null || tile.getCard().getOwner() != owner) {
            return List.of();
        }
        path.add(tile);
        final List<CardTile> result = new ArrayList<>();
        result.add(tile);
        for (Edge<CardTile> edge : tile.getEdges()) {
            result.addAll(recursiveFunction(edge.getOther(tile), path, owner));
        }
        return result;
    }

    public static CardTile getTileForCard(Card card, Field field) {
        for (int i = 0; i < field.numberOfRows(); i++) {
            for (int j = 0; i < field.getFieldRow(i).size(); i++) {
                if (card == field.getFieldRow(i).getCard(j)) {
                    return field.getFieldRow(i).getCardTile(j);
                }
            }
        }
        return null;
    }
}
