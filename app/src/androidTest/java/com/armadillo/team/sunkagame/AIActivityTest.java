package com.armadillo.team.sunkagame;


import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;


/**
 * Created by mihaelafronie on 13/11/2015.
 */
public class AIActivityTest extends ActivityInstrumentationTestCase2<AIActivity> {
    Activity activity;

    public AIActivityTest() {
        super(AIActivity.class);
    }

    public void setUp() throws Exception {
        super.setUp();
        activity = super.getActivity();
    }

    public void testExists() {
        assertNotNull(activity);
    }

}