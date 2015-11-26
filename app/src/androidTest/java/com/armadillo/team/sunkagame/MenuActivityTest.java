package com.armadillo.team.sunkagame;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.test.ViewAsserts;
import android.view.View;

public class MenuActivityTest extends ActivityInstrumentationTestCase2<MenuActivity> {

    Activity activity;

    public MenuActivityTest() {
        super(MenuActivity.class);

    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        activity = super.getActivity();
    }

    public void testPreConditions() {
        assertNotNull(activity.findViewById(R.id.Play_human));
        assertNotNull(activity.findViewById(R.id.Play_comp));
        assertNotNull(activity.findViewById(R.id.Help_button));
        assertNotNull(activity.findViewById(R.id.Scores_button));

    }

    public void testViewable() {
        View rootView = activity.getCurrentFocus().getRootView();
        if(rootView==null){
            fail();
        }
        ViewAsserts.assertOnScreen(rootView, activity.findViewById(R.id.Play_human));
        ViewAsserts.assertOnScreen(rootView, activity.findViewById(R.id.Play_comp));
        ViewAsserts.assertOnScreen(rootView, activity.findViewById(R.id.Help_button));
        ViewAsserts.assertOnScreen(rootView, activity.findViewById(R.id.Scores_button));
    }

    public void testGame(){
        TouchUtils.clickView(this,this.getActivity().findViewById(R.id.Play_human));
        NamesDialogFragment n = new NamesDialogFragment(this.getActivity());
        n.show();
    }

}
