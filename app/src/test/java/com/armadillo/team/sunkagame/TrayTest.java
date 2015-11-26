package com.armadillo.team.sunkagame;

import com.armadillo.team.sunkagame.Backend.Store;
import com.armadillo.team.sunkagame.Backend.Tray;

import junit.framework.Assert;

import org.junit.Test;

/**
 * JUnit Test for the Tray class
 * Created by Tamara on 13/11/2015.
 */
public class TrayTest {

    @Test
    public void createdTest() {
        Tray tray = new Tray();
        int result = tray.getNumberOfShells();
        Assert.assertEquals("createdTest failed", 7, result);
    }

    @Test
    public void isEmptyTest() {
        Tray tray = new Tray();
        boolean result = tray.isEmpty();
        Assert.assertFalse("isEmptyTest failed", result);
    }

    @Test
    public void getNumberOfShellsTest() {
        Tray tray = new Tray();
        tray.addShell();
        int result = tray.getNumberOfShells();
        Assert.assertEquals("getNumberOfShellsTest failed", 8, result);
    }

    @Test
    public void emptyTrayTest() {
        Tray tray = new Tray();
        tray.emptyTray();
        int result = tray.getNumberOfShells();
        Assert.assertEquals("emptyTrayTest failed", 0, result);
    }
}
