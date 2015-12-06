package edu.sjsu.qi.onlinecommunity;

import android.app.ActionBar;
import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;


public class ActivityCategory extends Activity {

    private static final String TAG = MainActivity.class.getSimpleName();

    ImageButton ibUser;
    TextView textView;
    RelativeLayout relativeLayout;
    String[] cat = {"art", "com", "fin", "life", "lan", "music", "math", "sport"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        setContentView(R.layout.activity_category);

        Resources r = getResources();
        String name = getPackageName();
        int[] layout_ids = new int[cat.length];
        int[] text_ids = new int[cat.length];

        //Set the category item click listener

        for(int i=0; i<cat.length ; i++){

            layout_ids[i] = r.getIdentifier("layout_" + cat[i], "id", name);
            relativeLayout = (RelativeLayout)findViewById(layout_ids[i]);

            text_ids[i] = r.getIdentifier("textView_" + cat[i], "id", name);
            textView = (TextView)findViewById(text_ids[i]);
            final String category = textView.getText().toString();

            relativeLayout.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){
                    try{

                        Intent intent = new Intent(ActivityCategory.this, ActivityCourseContainer.class);
                        intent.putExtra("CATEGORY", category);

                        //TODO May query data by CategoryID
                        startActivity(intent);
                    }catch(Exception e){
                        Log.e(TAG, e.toString());
                    }
                }
            });
        }

       // Set up the action bar
        ActionBar actionBar = getActionBar();

        //Hiding ActionBar icon and title
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);

        //Show the custom ActionBar with the icon_user
        View mActionBarView = getLayoutInflater().inflate(R.layout.action_bar_user,null);
        actionBar.setCustomView(mActionBarView);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);

        ibUser = (ImageButton) findViewById(R.id.button_mycourse);
        ibUser.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Goto activity MyCourse
                try{
                    Intent intent = new Intent(ActivityCategory.this, ActivityUserProfile.class);
                    startActivity(intent);
                }catch(Exception e){
                    Log.e(TAG, e.toString());
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_category, menu);
        return true;

        /* //don't need search bar here
        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        final SearchView searchView = (SearchView) menu.findItem(R.id.menu_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(920);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){
            @Override
            public boolean onQueryTextSubmit(String query){
                searchView.clearFocus();
                if(!TextUtils.isEmpty(query)){
                    //TODO: need implement search function, such as user search one course,
                    //here is only show the search Text
                    Toast.makeText(getApplicationContext(), "You are searching " + query ,
                             Toast.LENGTH_LONG).show();
                }
                return true;
            }
            @Override
            public boolean onQueryTextChange(String newText){
                return false;
            }
        }); */

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        int id = item.getItemId();
        if(id == R.id.action_course){
            Intent intent = new Intent(ActivityCategory.this, ActivityMyCourse.class);
            //Todo May need putExtra for userId
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
