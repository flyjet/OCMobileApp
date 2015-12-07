package edu.sjsu.qi.onlinecommunity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


public class ActivityUserProfile extends Activity {

    private static final String TAG = ActivityUserProfile.class.getSimpleName();

    ImageButton ibBack;
    UserItem currentUser;
    TextView tvName, tvEmail;
    Button btSignout;
    ImageView ivThumbnail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        currentUser = new UserItem();

        tvName = (TextView)findViewById(R.id.user_name);
        tvEmail = (TextView)findViewById(R.id. user_email);
        ivThumbnail = (ImageView)findViewById(R.id.user_thumbnail);

        //TODO after use login , show putExtra with User Id, then query user infor by UserId
        queryUserProfileFromDB();

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

        //Signout button action
        btSignout = (Button) findViewById(R.id.bt_Signout);
        btSignout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //return back to main activity
                try{
                    //TODO may need clear user session
                    finish();
                    Intent intent = new Intent(ActivityUserProfile.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
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
        getMenuInflater().inflate(R.menu.menu_activity_user_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        /*
        //TODO not sure need Edit button for user change his profile
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_edit) {
            Intent intent = new Intent(ActivityUserProfile.this, ActivityUserEditProfile.class);
            //Todo May need putExtra for userId
            startActivity(intent);

            return true;
        }*/
        return super.onOptionsItemSelected(item);
    }

    //query user information for current User
    public void queryUserProfileFromDB(){
        //TODO: implement the AsyncTask to query list of courses by key word "category"

        //The following is hardcode
        currentUser.setUserName("Sophia Zhang");
        currentUser.setEmail("Bugfree@gmail.com");

        //after background query done, update UI
        showUserProfileResult();
    }

    public void showUserProfileResult(){
        tvName.setText(currentUser.getUserName());
        tvEmail.setText(currentUser.getEmail());
        ivThumbnail.setImageResource(R.drawable.user_thumbnail_example);
    }

}
