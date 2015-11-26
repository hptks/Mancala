package com.armadillo.team.sunkagame;

import android.test.AndroidTestCase;

import com.armadillo.team.sunkagame.Backend.Game;
import com.armadillo.team.sunkagame.Backend.Player;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Created by Hristo Tanev.
 */
public class GameTest extends AndroidTestCase {
    @Test
    public void gamePresenceTest() {
        Game g=new Game(getContext(),new Player(getContext(),false),new Player(getContext(),true));
        Assert.assertNotNull(g.getGamePanel());
        Assert.assertNotNull(g.getPlayer1());
        Assert.assertNotNull(g.getPlayer2());
    }

    @Test
    public void player1MakesInvalidMove() {
        Game g=new Game(getContext(),new Player(getContext(),false),new Player(getContext(),true));
        g.getPlayer1().getTrays().get(0).emptyTray();
        g.player1Turn(0);
        Assert.assertTrue(g.getPlayer1().getTrays().get(0).isEmpty());
        for (int i=1;i<7;i++) {
            Assert.assertEquals(7, g.getPlayer1().getTrays().get(i).getNumberOfShells());
        }
        Assert.assertEquals(0, g.getPlayer1().getStore().getNumberOfShells());
    }

    @Test
    public void player2MakesInvalidMove() {
        Game g=new Game(getContext(),new Player(getContext(),false),new Player(getContext(),true));
        g.getPlayer1().getTrays().get(6).emptyTray();
        g.player2Turn(6);
        Assert.assertTrue(g.getPlayer2().getTrays().get(6).isEmpty());
        for (int i=5;i>=0;i--) {
            Assert.assertEquals(7, g.getPlayer2().getTrays().get(i).getNumberOfShells());
        }
        Assert.assertEquals(0, g.getPlayer2().getStore().getNumberOfShells());
    }

    @Test
    public void player1MakeValidMove() {
        Game g=new Game(getContext(),new Player(getContext(),false),new Player(getContext(),true));
        Assert.assertFalse(g.getPlayer1().getTrays().get(0).isEmpty());
        g.player1Turn(0);
        Assert.assertTrue(!g.getTurn());
        Assert.assertEquals(1,g.getPlayer1().getStore().getNumberOfShells());
        for (int i=1;i<7;i++) {
            Assert.assertEquals(8,g.getPlayer1().getTrays().get(i).getNumberOfShells());
        }
    }

    @Test
    public void player2MakeValidMove() {
        Game g=new Game(getContext(),new Player(getContext(),false),new Player(getContext(),true));
        Assert.assertFalse(g.getPlayer2().getTrays().get(6).isEmpty());
        g.player2Turn(6);
        Assert.assertTrue(g.getTurn());
        Assert.assertEquals(1,g.getPlayer2().getStore().getNumberOfShells());
        for (int i=0;i<6;i++) {
            Assert.assertEquals(8,g.getPlayer1().getTrays().get(i).getNumberOfShells());
        }
    }
}
