package edu.sjsu.qi.onlinecommunity;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;


public class ActivityCourseContainer extends Activity {

    private static final String TAG = ActivityCourseContainer.class.getSimpleName();

    String category="";
    ImageButton ibBack;
    //Delcaring two tabs and corresponding fragments
    Tab courseTab, instructorTab;
    Fragment courseFragment = new CourseFragment();
    Fragment instructorFragment = new InstructorFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //TODO: here is the category name, may use category Id to query
        //get Category from Activity Category
        Bundle extras = getIntent().getExtras();
        category = extras.getString("CATEGORY");

        //put Category to CourseFragment and InstructorFragment
        Bundle bundle = new Bundle();
        bundle.putString("Category", category);
        courseFragment.setArguments(bundle);
        instructorFragment.setArguments(bundle);

        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        setContentView(R.layout.activity_course_container);

        // Set up the action bar
        ActionBar actionBar = getActionBar();

        //Hiding ActionBar icon and title
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);

        //Show the custom ActionBar with the icon_back
        View mActionBarView = getLayoutInflater().inflate(R.layout.action_bar_back,null);
        actionBar.setCustomView(mActionBarView);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);

        ibBack = (ImageButton) findViewById(R.id.button_back);
        ibBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Goto activity Category
                try{
                    finish();
                    //Intent intent = new Intent(ActivityCourseContainer.this, ActivityCategory.class);
                    //startActivity(intent);
                }catch(Exception e){
                    Log.e(TAG, e.toString());
                }
            }
        });


        //Creating ActionBar tabs
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        //Setting tabs
        courseTab = actionBar.newTab().setText("COURSE");
        instructorTab = actionBar.newTab().setText("INSTRUCTOR");

        //Setting tab listeners
        courseTab.setTabListener(new TabListener(courseFragment));
        instructorTab.setTabListener(new TabListener(instructorFragment));

        //Adding tabs to the ActionBar
        actionBar.addTab(courseTab);
        actionBar.addTab(instructorTab);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_course_container, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    //TabListener
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
