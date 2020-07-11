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
    public static boolean isOnline(int row, int index, OriginNode node) {
        return true;
    }

    public static List<FieldPosition<Card>> allOnlineNodesFromOrigin(OriginNode node, GameField field) {
        final List<FieldPosition<Card>> nodes = new ArrayList<>();
        FieldPosition<?> fieldPositionOfNode = field.getPositionOf(node);
        final Tile<Card> nodeTile = field.getTile(fieldPositionOfNode.getRow(), fieldPositionOfNode.getIndex());
        return recursiveFunction(nodeTile, new ArrayList<>(), node.getOwner())
                .stream()
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
