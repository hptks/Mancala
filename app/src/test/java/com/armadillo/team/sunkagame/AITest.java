package com.armadillo.team.sunkagame;

import android.test.AndroidTestCase;

import com.armadillo.team.sunkagame.Backend.AI;
import com.armadillo.team.sunkagame.Backend.Game;
import com.armadillo.team.sunkagame.Backend.Player;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Created by Hristo Tanev.
 */
public class AITest extends AndroidTestCase {
    @Test
    public void aisMoveTest() {
        Game g=new Game(getContext(),new Player(getContext(),false),new Player(getContext(),true));
        AI ai=new AI(getContext());
        Assert.assertTrue(ai.getMove(g)!=0);
    }
}
