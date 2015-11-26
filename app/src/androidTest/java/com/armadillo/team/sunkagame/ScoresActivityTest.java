package com.armadillo.team.sunkagame;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.test.ViewAsserts;
import android.view.View;

/**
 * Created by mihaelafronie on 13/11/2015.
 */
public class ScoresActivityTest extends ActivityInstrumentationTestCase2<ScoresActivity>{

    Activity activity;


    public ScoresActivityTest() {
        super(ScoresActivity.class);
    }
    @Override
    public void setUp() throws Exception {
        super.setUp();
        activity = super.getActivity();
    }
    public void testPreConditions() {
        assertNotNull(activity.findViewById(R.id.win));
        assertNotNull(activity.findViewById(R.id.lose));
        assertNotNull(activity.findViewById(R.id.infoScores));


    }

    public void testViewable() {
        View rootView = activity.getCurrentFocus().getRootView();
        ViewAsserts.assertOnScreen(rootView, activity.findViewById(R.id.win));
        ViewAsserts.assertOnScreen(rootView, activity.findViewById(R.id.lose));
        ViewAsserts.assertOnScreen(rootView, activity.findViewById(R.id.infoScores));
    }




}
