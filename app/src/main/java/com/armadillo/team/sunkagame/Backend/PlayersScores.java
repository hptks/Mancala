package com.armadillo.team.sunkagame.Backend;

import java.util.ArrayList;

/**
 * Created by Hristo Tanev :).
 */
public class PlayersScores {
    private ArrayList<Player> players;

    public PlayersScores() {
        this.players=new ArrayList<Player>();
    }

    public void addPlayer(Player p) {
        this.players.add(p);
    }

    public Player findPlayer(String name) {
        for (Player p : players) {
            if (p.getName().equals(name)) {
                return p;
            }
        }

        return null;
    }

    public Player getPlayerAtPosition(int position) {
        return this.players.get(position);
    }

    public void setPlayerAtPosition(int position,Player p) {
        this.players.set(position,p);
    }

    public ArrayList<Player> getPlayers() {
        return this.players;
    }

    public int numberOfPlayers() {
        return this.players.size();
    }
}
