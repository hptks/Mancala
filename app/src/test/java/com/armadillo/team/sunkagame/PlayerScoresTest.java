package com.armadillo.team.sunkagame;

import android.test.AndroidTestCase;

import com.armadillo.team.sunkagame.Backend.Player;
import com.armadillo.team.sunkagame.Backend.PlayersScores;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Created by Hristo Tanev.
 */
public class PlayerScoresTest extends AndroidTestCase {
    @Test
    public void collectionPresenceTest() {
        PlayersScores p=new PlayersScores();
        Assert.assertNotNull(p.getPlayers());
    }

    @Test
    public void getPlayerAtPositionTest1() {
        PlayersScores p=new PlayersScores();
        Player q;
        try {
            q=p.getPlayers().get(3);
        } catch (Exception e) {
            q=null;
        }
        Assert.assertNull(q);
    }

    @Test
    public void getPlayerAtPositionTest2() {
        PlayersScores p=new PlayersScores();
        p.addPlayer(new Player(getContext(),false));
        boolean ok;
        try {
            Player q=p.getPlayers().get(0);
            ok=true;
        } catch (Exception e) {
            ok=false;
        }
        Assert.assertTrue(ok);
    }
}
