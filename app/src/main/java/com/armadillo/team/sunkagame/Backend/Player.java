package com.armadillo.team.sunkagame.Backend;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.armadillo.team.sunkagame.Drawing.GuiStore;
import com.armadillo.team.sunkagame.Drawing.GuiTray;
import com.armadillo.team.sunkagame.Drawing.Shell;
import com.armadillo.team.sunkagame.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * Class representing the player alongside with his store and trays
 */
public class Player implements Serializable {
    private String name;
    private GuiStore store;
    private ArrayList<GuiTray> trays;
    private ArrayList<Shell> shells = new ArrayList<>(98);
    private int wins, loses;
    private Context context;
    private boolean side;

    public Player(Context context, Boolean side) {
        this.side = side;
        this.context = context;
        reset();
        this.wins = this.loses = 0;
    }

    public boolean checkIfTraysAreEmpty() {
        for (Tray t : this.trays) {
            if (!t.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addWin() {
        this.wins++;
    }

    public int getWins() {
        return this.wins;
    }

    public void addLose() {
        this.loses++;
    }

    public int getLoses() {
        return this.loses;
    }

    public int getShellsInStore() {
        return this.store.getNumberOfShells();
    }

    public String getName() {
        return this.name;
    }

    public ArrayList<GuiTray> getTrays() {
        return this.trays;
    }

    public GuiStore getStore() {
        return this.store;
    }

    public String toString() {
        return this.name + " " + this.wins + " " + this.loses;
    }

    public static Comparator<Player> numberOfWinsComparator = new Comparator<Player>() {
        public int compare(Player lhs, Player rhs) {
            return rhs.getWins() - lhs.getWins();
        }
    };

    public static Comparator<Player> numberOfLosesComparator = new Comparator<Player>() {
        public int compare(Player lhs, Player rhs) {
            return rhs.getLoses() - lhs.getLoses();
        }
    };

    public ArrayList<Shell> getShells() {
        return shells;
    }

    public void setLoses(int loses) {
        this.loses = loses;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public void reset() {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 5;
        Bitmap storeImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.store);
        Bitmap trayImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.tray);
        Bitmap shellImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.shell, options);

        if (side) {
            this.store = new GuiStore(150, 350, storeImage);
            this.store.scoreY = this.store.getY() - 200;
        } else {
            this.store = new GuiStore(1130, 350, storeImage);
            this.store.scoreY = this.store.getY() + 200;
        }

        this.trays = new ArrayList<>(7);
        int k = 0;
        for (int i = 0; i < 7; i++) {
            if (side) {
                this.trays.add(new GuiTray(280 + i * 120, 250, trayImage));
            } else {
                this.trays.add(new GuiTray(280 + i * 120, 450, trayImage));
            }
            if (side) {
                this.trays.get(i).scoreY = this.trays.get(i).getY() - 100;
            } else {
                this.trays.get(i).scoreY = this.trays.get(i).getY() + 100;
            }
            for (int j = 0; j < 7; ++j) {
                shells.add(new Shell(60, 60, this.trays.get(i), shellImage));
                this.trays.get(i).shells.add(shells.get(k));
                ++k;
            }
        }
    }
}
