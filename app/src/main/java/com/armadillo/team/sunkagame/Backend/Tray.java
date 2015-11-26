package com.armadillo.team.sunkagame.Backend;

import com.armadillo.team.sunkagame.Drawing.GameObject;

import java.io.Serializable;

/**
 * This class represents a single Tray of the board
 */
public class Tray extends GameObject implements Serializable {
    private int numberOfShells;

    public Tray() {
        this.numberOfShells = 7;
    }

    public boolean isEmpty() {
        return (this.numberOfShells == 0);
    }

    /**
     * Increase the number of shells by one
     */
    public void addShell() {
        this.numberOfShells++;
    }

    public void emptyTray() {
        this.numberOfShells = 0;
    }

    public int getNumberOfShells() {
        return this.numberOfShells;
    }

}
