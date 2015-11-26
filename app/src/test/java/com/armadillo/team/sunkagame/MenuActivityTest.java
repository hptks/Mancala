package com.armadillo.team.sunkagame;

import android.widget.Button;

import junit.framework.TestCase;

import org.junit.Test;

public class MenuActivityTest extends TestCase {
    MenuActivity menuActivity;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        menuActivity = new MenuActivity();
        //menuActivity.setContentView(R.layout.activity_menu);

    }

    @Test
    public void testMenu() throws Exception {
        try {
            menuActivity.play_comp_button((Button)menuActivity.findViewById(R.id.Play_comp));
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }

}
