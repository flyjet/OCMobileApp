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
import android.widget.RelativeLayout;
import android.widget.TextView;


public class ActivityCourseIntro extends Activity {

    private static final String TAG = ActivityCourseIntro.class.getSimpleName();

    ImageButton ibBack;
    Button btJoin;
    ImageView ivIntro;


    String courseName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_intro);

        //TODO: Implement to query course information by course ID

        //TODO: init textView and update its value

        //get Course Name from Fragment Course
        Bundle extras = getIntent().getExtras();
        courseName  = extras.getString("CourseName");

        ivIntro = (ImageView)findViewById(R.id.imageView_intro);
        //when user click the the imageView, it will go to Activity VideoPlayer
        ivIntro.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View view){
                Intent intent = new Intent(ActivityCourseIntro.this, ActivityVideoPlayer.class );
                intent.putExtra("CourseName", courseName);
                startActivity(intent);
            }
        });

        //TODO, first to identify user already join the course or not
        //if joined, then set Join button invisible

        btJoin = (Button)findViewById(R.id.bt_join);
        //when user click join button, it will go to Activity CourseDetail
        btJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityCourseIntro.this, ActivityCourseDetail.class);
                intent.putExtra("CourseName", courseName);
                startActivity(intent);
            }
        });

        RelativeLayout layout = (RelativeLayout)findViewById(R.id.layout_instructor);
        layout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(ActivityCourseIntro.this, ActivityInstructorIntro.class);
                intent.putExtra("InstructorName", "Sample");
                startActivity(intent);
            }
        });


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
                } catch (Exception e) {
                    Log.e(TAG, e.toString());
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_course_intro, menu);
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
}
