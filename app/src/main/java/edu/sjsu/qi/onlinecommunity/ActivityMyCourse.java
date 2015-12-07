package edu.sjsu.qi.onlinecommunity;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.ActionBar.Tab;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.view.Window;
import android.widget.ImageButton;


public class ActivityMyCourse extends Activity {

    private static final String TAG = ActivityMyCourse.class.getSimpleName();
    ImageButton ibBack;

    //Delcaring 3 tabs and corresponding fragments
    Tab currentTab, pastTab, futureTab;

    Fragment currentFragment = new CourseFragment();
    Fragment pastFragment = new CourseFragment();
    Fragment futureFragment = new CourseFragment();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundleCurrent = new Bundle();
        Bundle bundlePast = new Bundle();
        Bundle bundleFuture = new Bundle();
        bundleCurrent.putString("Category", "CURRENT");
        bundlePast.putString("Category", "PAST");
        bundleFuture.putString("Category", "FUTURE");

        currentFragment.setArguments(bundleCurrent);
        pastFragment.setArguments(bundlePast);
        futureFragment.setArguments(bundleFuture);

        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        setContentView(R.layout.activity_my_course);

        // Set up the action bar
        ActionBar actionBar = getActionBar();
        //Hiding ActionBar icon and title
        actionBar.setDisplayShowHomeEnabled(false);

        //Show the custom ActionBar with the icon_back
        View mActionBarView = getLayoutInflater().inflate(R.layout.action_bar_back,null);
        actionBar.setCustomView(mActionBarView);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);

        ibBack = (ImageButton) findViewById(R.id.button_back);
        ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Goto activity Category
                try {
                    finish();
                    //Intent intent = new Intent(ActivityUserProfile.this, ActivityFragmentContainer.class);
                    //startActivity(intent);
                } catch (Exception e) {
                    Log.e(TAG, e.toString());
                }
            }
        });

        //Creating ActionBar tabs
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        //Setting tabs
        currentTab = actionBar.newTab().setText("CURRENT");
        pastTab = actionBar.newTab().setText("PAST");
        futureTab = actionBar.newTab().setText("FUTURE");
        currentTab.setTabListener(new TabListener(currentFragment));
        pastTab.setTabListener(new TabListener(pastFragment));
        futureTab.setTabListener(new TabListener(futureFragment));

        //Adding tabs to the ActionBar
        actionBar.addTab(currentTab);
        actionBar.addTab(pastTab);
        actionBar.addTab(futureTab);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_my_course, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

    //Tab Listener
    public class TabListener implements ActionBar.TabListener{
        private Fragment fragment;

        //Constructor
        public TabListener(Fragment fragment){
            this.fragment = fragment;
        }

        //When a tab is tapped, the FragmentTransaction replaces the content of CourseContainer layout
        @Override
        public void onTabSelected(Tab tab, FragmentTransaction ft){
            ft.replace(R.id.activity_course_container, fragment);
        }

        @Override
        public void onTabUnselected(Tab tab, FragmentTransaction ft){
            ft.remove(fragment);
        }

        @Override
        public void onTabReselected(Tab tab, FragmentTransaction ft) {
        }
    }

}
