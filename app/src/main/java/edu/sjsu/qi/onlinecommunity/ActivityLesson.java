package edu.sjsu.qi.onlinecommunity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;


public class ActivityLesson extends Activity {

    private static final String TAG = ActivityLesson.class.getSimpleName();
    ImageButton ibBack;
    Button btInClass,btChat, btPlayVideo;
    LessonItem lesson;
    TextView tvLessonName, tvLessonDes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson);

        lesson = new LessonItem();

        tvLessonName = (TextView)findViewById(R.id.tv_lessonName);
        tvLessonDes  = (TextView)findViewById(R.id.tv_DesContent);

        btInClass = (Button)findViewById(R.id.button_gotoclass);
        btChat = (Button)findViewById(R.id.button_chat);
        btPlayVideo = (Button)findViewById(R.id.button_watchvideo);

        //TODO if the lesson is for future, may set buttonInClass to light color and not clickable
        //For example
        btInClass.setBackground(getResources().getDrawable(R.drawable.notclickbutton));

        btInClass.setClickable(false);
        btInClass.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(ActivityLesson.this, ActivityInClass.class );
                //Todo need put extra
                startActivity(intent);
            }
        });

        btChat.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(ActivityLesson.this, ActivityChat.class );
                //Todo need put extra
                startActivity(intent);
            }
        });

        btPlayVideo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(ActivityLesson.this, ActivityVideoPlayer.class );
                //Todo need put extra
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

        //query Lesson Detail and update view
        queryLessonDetailFromDB();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_lesson, menu);
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

    //query lesson detail
    public void queryLessonDetailFromDB(){

        //TODO: implement the AsyncTask to query lesson detail

        //The following is hardcode
        lesson.setLessonName("Lesson 1: Introduction of Software Testing");
        lesson.setDescription("Software testing theory, testing method, testing model and design test case");

        //after background query done, update UI
        showListCourseResult();
    }

    //after background query done, update UI
    public void showListCourseResult(){
        tvLessonName.setText(lesson.getLessonName());
        tvLessonDes.setText(lesson.getDescription());
    }
}
