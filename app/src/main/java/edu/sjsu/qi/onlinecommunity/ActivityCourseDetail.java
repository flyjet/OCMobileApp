package edu.sjsu.qi.onlinecommunity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;


public class ActivityCourseDetail extends Activity {

    private static final String TAG = ActivityCourseDetail.class.getSimpleName();

    String courseName;
    ImageButton ibBack;
    TextView tvLesson,tvCourseName;
    private ListView lvLesson;
    private List<LessonItem> queryResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);

       // Bundle extras = getIntent().getExtras();
        //courseName  = extras.getString("CourseName");

        queryResults = new ArrayList<LessonItem>();

        tvLesson =(TextView)findViewById(R.id.tv_lessonName);
        tvCourseName = (TextView)findViewById(R.id.tv_courseName);
        tvCourseName.setText(courseName);
        lvLesson = (ListView)findViewById(R.id.listView_lesson);

        //query lessonList by course name
        queryLessonListFromDB();

        //sets the OnItemClickListener of the ListView
        //so the user can click on a lesson and go to the lesson intro
        lvLesson.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> av, View v, int pos, long id) {
                Intent intent = new Intent(ActivityCourseDetail.this, ActivityLesson.class);
                intent.putExtra("LessonName", tvLesson.getText().toString());
                //TODO may need put other extra

                startActivity(intent);
            }
        });

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
                }catch(Exception e){
                    Log.e(TAG, e.toString());
                }
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_course_detail, menu);
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


    //query Lessons for current course
    public void queryLessonListFromDB(){

        //TODO: implement the AsyncTask to query list of lesson

        //The following is hard code
        LessonItem lesson1 = new LessonItem();
        LessonItem lesson2 = new LessonItem();
        LessonItem lesson3 = new LessonItem();
        LessonItem lesson4 = new LessonItem();
        LessonItem lesson5 = new LessonItem();
        LessonItem lesson6 = new LessonItem();
        LessonItem lesson7 = new LessonItem();

        lesson1.setLessonName("Introduction of Software Testing");
        lesson2.setLessonName("Black-box Testing");
        lesson3.setLessonName("White-box Testing");
        lesson4.setLessonName("Unit Testing");
        lesson5.setLessonName("Integration Testing");
        lesson6.setLessonName("Regression Testing");
        lesson7.setLessonName("System Performance");

        queryResults.add(lesson1);
        queryResults.add(lesson2);
        queryResults.add(lesson3);
        queryResults.add(lesson4);
        queryResults.add(lesson5);
        queryResults.add(lesson6);
        queryResults.add(lesson7);

        //after background query doen, update UI
        showListLessonResult();
    }

    //Use ArrayAdapter and pass it to ListView to display lesson results
    //In the getView method, inflate the listview_lesson_item.xml layout and update its view
    public void showListLessonResult(){

        //create an ArrayAdapter
        ArrayAdapter<LessonItem> adapter = new ArrayAdapter<LessonItem>(getApplicationContext(),
                R.layout.listview_lesson_item, queryResults){
            @Override
            public View getView(int position, View convertView, ViewGroup parent){

                if(convertView == null){
                    convertView = getLayoutInflater().inflate(R.layout.listview_lesson_item, parent, false);
                }

                tvLesson = (TextView)convertView.findViewById(R.id.tv_lessonName);

                LessonItem lesson = queryResults.get(position);

                String lessonName = "Lesson " + String.valueOf(position + 1) + ": " + lesson.getLessonName();
                tvLesson.setText(lessonName);
                tvCourseName.setText("Software Testing");

                return convertView;
            }
        };

        //Assign adapter to ListView
        lvLesson.setAdapter(adapter);

    }
}
