package com.armadillo.team.sunkagame.Help;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.armadillo.team.sunkagame.R;


/**
 * An activity representing a list of HelpPoints. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link HelpPointDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 * <p/>
 * The activity makes heavy use of fragments. The list of items is a
 * {@link HelpPointListFragment} and the item details
 * (if present) is a {@link HelpPointDetailFragment}.
 * <p/>
 * This activity also implements the required
 * {@link HelpPointListFragment.Callbacks} interface
 * to listen for item selections.
 */
public class HelpPointListActivity extends AppCompatActivity
        implements HelpPointListFragment.Callbacks {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helppoint_app_bar);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());


        if (findViewById(R.id.helppoint_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-large and
            // res/values-sw600dp). If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;

            // In two-pane mode, list items should be given the
            // 'activated' state when touched.
            ((HelpPointListFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.helppoint_list))
                    .setActivateOnItemClick(true);
        }

        // TODO: If exposing deep links into your app, handle intents here.
    }

    /**
     * Callback method from {@link HelpPointListFragment.Callbacks}
     * indicating that the item with the given ID was selected.
     */
    @Override
    public void onItemSelected(String id) {
        if (id.equals("5")) {
            this.finish();
        } else if (mTwoPane) {
            // In two-pane mode, show the detail view in this activity by
            // adding or replacing the detail fragment using a
            // fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putString(HelpPointDetailFragment.ARG_ITEM_ID, id);
            HelpPointDetailFragment fragment = new HelpPointDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.helppoint_detail_container, fragment)
                    .commit();

        } else {
            // In single-pane mode, simply start the detail activity
            // for the selected item ID.
            Intent detailIntent = new Intent(this, HelpPointDetailActivity.class);
            detailIntent.putExtra(HelpPointDetailFragment.ARG_ITEM_ID, id);
            startActivity(detailIntent);
        }
    }
}
