package com.armadillo.team.sunkagame.Help;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p/>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class HelpContent {

    /**
     * An array of sample (dummy) items.
     */
    public static List<DummyItem> ITEMS = new ArrayList<DummyItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();

    static {
        addItem(new DummyItem("1", "Elements of the game", "Traditionally, Sunka is played on an oblong wooden block with two rows of seven circular trays and a larger circular tray at each end of the board.  The larger trays at both ends of the board are called stores.  Players sit opposite to one another and the store on a player's righthand side is that player's store.  The row of seven small trays on a player's side belong to that player.  The small trays in the other row belong to the opponent.\n" +
                "\n" +
                "Sunka board\n" +
                "\n" +
                "The game is played with 98 shells.  Initially, these shells are distributed equally across the 14 smaller trays, such that each tray contains 7 shells.  The objective of the game is to collect as many shells in one's own store as possible.  The table illustrates this initial distribution of shells.  In this table, each cell corresponds to a tray.  The large two row cells are stores.  The number represents the number of shells in each tray."));
        addItem(new DummyItem("2", "Basics of moving shells on the Sunka board", "Players take turns playing the game.  In each move, a player:\n" +
                "\n" +
                "Chooses one of the seven small trays on their side of the board,\n" +
                "Removes all the shells from that tray,\n" +
                "Redistributes the shells by navigating the trays in an anti-clockwise fashion and dropping one shell in each tray, including the player's own small trays, the player's own store and the opponent's small trays.\n" +
                "For example, consider a situation where the bottom player in the board below chooses to redistribute the shells from the tray shaded red below.\n" +
                "\n" +
                "Before the move\n" +
                "0\t7\t7\t7\t7\t7\t7\t7\t0\n" +
                "7\t7\t7\t7\t7\t7\t7\n" +
                "That move would require the player to redistribute the shells as follows.  \n" +
                "\n" +
                "After the move\n" +
                "0\t7\t7\t7\t7\t7\t8\t8\t1\n" +
                "7\t7\t0\t8\t8\t8\t8\n" +
                "For clarity, the seven trays that received one additional cell each are displayed in yellow.  Observe how the player has added one shell each to the seven adjacent trays encountered while navigating the board in an anti-clockwise fashion.\n" +
                "\n" +
                "Here is another example.  Again, consider the same move by the same player, but in a board with a different distribution of shells.\n" +
                "\n" +
                "Before the move\n" +
                "18\t6\t5\t2\t2\t2\t2\t2\t19\n" +
                "4\t3\t13\t3\t3\t3\t3\n" +
                "That move would require the player to redistribute the shells as follows.\n" +
                "\n" +
                "After the move\n" +
                "18\t7\t6\t3\t3\t3\t3\t3\t20\n" +
                "5\t3\t0\t4\t4\t4\t4\n" +
                "Observe that the bottom player has skipped his/her opponent's store as stipulated in the rules above."));
        addItem(new DummyItem("3", "Rules governing moves", "In the first turn of the game, both players begin redistributing shells according to the principles explained above simultaneously.  The first player to finish their move is the one who gets to move in the second turn.  After the first turn, players move alternately.  \n" +
                "\n" +
                "If the last shell that is redistributed in a move drops in the player's own store, then the player gets an additional move.  Note that such bonus moves can be accumulated.\n" +
                "\n" +
                "If the last shell that is redistributed in a move drops in an empty small tray (not the store) on the player's own side, then that shell and all the shells in the corresponding tray on the opposite side (i.e. the opponent's side) are captured and moved to the player's store.\n" +
                "\n" +
                "A player must make a move in his/her turn when one is possible.  If a player cannot make a move in his/her turn (because there are no shells in any of the small trays on his/her side), that player must pass until a move is possible in a future turn.  "));
        addItem(new DummyItem("4", "Game Over", "The game ends when there are no more shells in any of the small trays.  The winner at that point is the player who has the most shells in his/her store."));
        addItem(new DummyItem("5", "Back Home", ""));
    }

    private static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    public static class DummyItem {
        public String id;
        public String content;
        public String details;

        public DummyItem(String id, String content, String details) {
            this.id = id;
            this.content = content;
            this.details = details;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}
