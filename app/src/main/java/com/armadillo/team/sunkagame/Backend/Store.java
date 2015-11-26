package com.armadillo.team.sunkagame.Backend;

import com.armadillo.team.sunkagame.Drawing.GameObject;

import java.io.Serializable;

/**
 * This class represents the Store on each side of the board
 */
public class Store extends GameObject implements Serializable {
    private int numberOfShells;

    public Store() {
        this.numberOfShells = 0;
    }

    /**
     * Add shells to the Store
     *
     * @param numberOfShells - number of shells to be added
     */
    public void addShells(int numberOfShells) {
        this.numberOfShells += numberOfShells;
    }

    public int getNumberOfShells() {
        return this.numberOfShells;
    }

}
