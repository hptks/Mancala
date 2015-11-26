package com.armadillo.team.sunkagame;

import com.armadillo.team.sunkagame.Backend.Store;

import junit.framework.Assert;

import org.junit.Test;

/**
 * JUnit Test for the Store class
 * Created by Tamara on 13/11/2015.
 */
public class StoreTest {

    @Test
    public void createdTest() {
        Store store = new Store();
        int result = store.getNumberOfShells();
        Assert.assertEquals("createdTest failed", 0, result);
    }

    @Test
    public void addShellsTest() {
        Store store = new Store();
        store.addShells(5);
        int result = store.getNumberOfShells();
        Assert.assertEquals("addShellsTest failed", 5, result);
    }

    @Test
    public void getNumberOfShellsTest() {
        Store store = new Store();
        store.addShells(5);
        store.addShells(10);
        int result = store.getNumberOfShells();
        Assert.assertEquals("getNumberOfShellsTest failed", 15, result);
    }
}