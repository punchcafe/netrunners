package dev.punchcafe.netrunners.util;

import dev.punchcafe.netrunners.card.Card;
import dev.punchcafe.netrunners.card.OriginNode;
import dev.punchcafe.netrunners.field.FieldPosition;
import dev.punchcafe.netrunners.field.Tile;
import dev.punchcafe.netrunners.game.GameField;
import dev.punchcafe.netrunners.field.graph.Edge;
import dev.punchcafe.netrunners.player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OnlineChecks {
    // TODO: checker for detemining if a card in a slot is connected to a node

    /**
     * Position is next to an online node of the player
     *
     * @param position
     * @param player
     * @param gameField
     * @return
     */
    public static boolean isOnlineAccess(FieldPosition<?> position, Player player, GameField gameField) {
        final List<Tile<Card>> allOnlinePlayerNodes = allOnlineNodesFromOrigin(player.getOriginNode(), gameField);
        final Tile<Card> cardTile = gameField.getTile(position.getRow(), position.getIndex());
        for (Edge<Tile> edge : cardTile.getEdges()) {
            if (allOnlinePlayerNodes.contains(edge.getOther(cardTile))) {
                return true;
            }
        }
        return false;
    }

    public static List<Tile<Card>> allOnlineNodesFromOrigin(OriginNode node, GameField field) {
        FieldPosition<?> fieldPositionOfNode = field.getPositionOf(node);
        final Tile<Card> nodeTile = field.getTile(fieldPositionOfNode.getRow(), fieldPositionOfNode.getIndex());
        return recursiveFunction(nodeTile, new ArrayList<>(), node.getOwner());
    }

    public static List<FieldPosition<Card>> allOnlineNodePositionsFromOrigin(OriginNode node, GameField field) {
        return allOnlineNodesFromOrigin(node, field).stream()
                .map(tile ->
                        FieldPosition.convertFieldPosition(field.getPositionOfTile(tile), tile.getContents())
                ).collect(Collectors.toList());
    }

    public static List<Tile<Card>> recursiveFunction(Tile<Card> tile, List<Tile> path, Player owner) {
        if (path.contains(tile) || tile.getContents() == null || tile.getContents().getOwner() != owner) {
            return List.of();
        }
        path.add(tile);
        final List<Tile<Card>> result = new ArrayList<>();
        result.add(tile);
        for (Edge<Tile> edge : tile.getEdges()) {
            result.addAll(recursiveFunction(edge.getOther(tile), path, owner));
        }
        return result;
    }
}
