package com.armadillo.team.sunkagame;

import android.test.AndroidTestCase;

import com.armadillo.team.sunkagame.Backend.Player;
import com.armadillo.team.sunkagame.Drawing.GuiTray;
import com.armadillo.team.sunkagame.Drawing.Shell;

import junit.framework.Assert;

import org.junit.Test;

import java.util.ArrayList;

/**
 * JUnit Test for the Player class
 * Created by Tamara on 13/11/2015.
 */
public class PlayerTest extends AndroidTestCase {

    @Test
    public void createdTest() {
        Player player = new Player(getContext(), true);
        Assert.assertNotNull(player.getTrays());
        Assert.assertNotNull(player.getStore());
    }
}
