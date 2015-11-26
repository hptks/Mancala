package com.armadillo.team.sunkagame;

import android.test.ActivityInstrumentationTestCase2;

import com.armadillo.team.sunkagame.Backend.Game;
import com.armadillo.team.sunkagame.Backend.Player;
import com.armadillo.team.sunkagame.Drawing.GamePanel;

public class GamePanelTest extends ActivityInstrumentationTestCase2<MenuActivity> {
    public GamePanelTest() {
        super(MenuActivity.class);
    }


    public void setUp() throws Exception {
        super.setUp();
    }

    public void tearDown() throws Exception {
        super.tearDown();
    }

    public void testCreation() throws Exception {
            assertNotNull(new GamePanel(getActivity(),new Game(this.getActivity(),new Player(this.getActivity(),false),new Player(this.getActivity(),true))));
    }
}