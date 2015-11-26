package com.armadillo.team.sunkagame;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;

import java.util.ArrayList;

/**
 * Instrumentation Test which tests the SunkaGameActivity.
 *
 * @author Tamara
 * @since 28.10.2015
 */
public class SunkaGameActivityTest extends ActivityInstrumentationTestCase2<MenuActivity> {

    public SunkaGameActivityTest() {
        super(MenuActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
    }

    /**
     * Tests if the activity exists after launching the app
     */

    public void testActivityCanStart() {
        Intent i = new Intent(this.getActivity(),SunkaGameActivity.class);
        ArrayList<String> playersNames=new ArrayList<String>();
        playersNames.add(0,"cake");
        playersNames.add(1,"bake");
        i.putStringArrayListExtra("playersNames", playersNames);
        // Add a monitor before we start the activity
        Instrumentation.ActivityMonitor mGameActivityMonitor = new Instrumentation.ActivityMonitor(SunkaGameActivity.class.getName(), null, false);
        getInstrumentation().addMonitor(mGameActivityMonitor);

        this.getActivity().startActivity(i);

        Activity activity = mGameActivityMonitor.waitForActivityWithTimeout(5 * 1000);
        assertNotNull("Activity was not started", activity);
    }

    //TODO create more tests for this activity

}
