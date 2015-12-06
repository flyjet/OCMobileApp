package edu.sjsu.qi.onlinecommunity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class ActivityMyCourse extends Activity {

    private static final String TAG = ActivityMyCourse.class.getSimpleName();
    ImageButton ibBack;
    private ListView lvCurrent, lvPast, lvFuture;
    private List<CourseItem> queryResultsCurrent, queryResultsPast, queryResultsFuture;
    private CourseItem course;

    //TODO there is have bug with listView, may need scrollView by QI

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        setContentView(R.layout.activity_my_course);

        queryResultsPast = new ArrayList<CourseItem>();
        queryResultsCurrent = new ArrayList<CourseItem>();
        queryResultsFuture = new ArrayList<CourseItem>();
        lvCurrent = (ListView)findViewById(R.id.lv_current);
        lvPast = (ListView)findViewById(R.id.lv_past);
        lvFuture = (ListView)findViewById(R.id.lv_future);

        //query courselist and update view
        queryCourseListFromDB();

        //sets the OnItemClickListener of the ListView
        //so the user can click on a course and go to the course intro
        lvCurrent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> av, View v, int pos, long id) {
                Intent intent = new Intent(ActivityMyCourse.this , ActivityCourseIntro.class);
                //TODO need putExtra
                startActivity(intent);
            }
        });

        lvPast.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> av, View v, int pos, long id) {
                Intent intent = new Intent(ActivityMyCourse.this , ActivityCourseIntro.class);
                //TODO need putExtra
                startActivity(intent);
            }
        });

        lvFuture.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> av, View v, int pos, long id) {
                Intent intent = new Intent(ActivityMyCourse.this , ActivityCourseIntro.class);
                //TODO need putExtra
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
                    //Intent intent = new Intent(ActivityUserProfile.this, ActivityFragmentContainer.class);
                    //startActivity(intent);
                } catch (Exception e) {
                    Log.e(TAG, e.toString());
                }
            }
        });

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

    //query courselist and update view
    public void queryCourseListFromDB(){

        //type 1= past, 2=past, 3=future
        //TODO: implement the AsyncTask to query list of courses by Current user and type



        //The following is hardcode
        CourseItem course1 = new CourseItem();
        CourseItem course2 = new CourseItem();
        CourseItem course3 = new CourseItem();
        CourseItem course4 = new CourseItem();
        CourseItem course5 = new CourseItem();
        CourseItem course6 = new CourseItem();

        course1.setCourseName("Foundations of Objective-C App Development");
        course1.setThumbnailURL("drawable/course_thumbnail_example.png");
        course1.setInstructorName("Don Patterson");
        course1.setRating((float) 3.80);

        course2.setCourseName("Programming Mobile Applications for Android Handheld Systems");
        course2.setThumbnailURL("drawable/course_thumbnail_example2.png");
        course2.setInstructorName("Adam Porter");
        course2.setRating((float) 4.60);

        course3.setCourseName("Software Testing");
        course3.setInstructorName("Zhenyu Chen");
        course3.setRating((float) 4.2);

        course4.setCourseName("iOS App Development Basics");
        course4.setInstructorName("Don Patterson");
        course4.setRating((float) 4.6);

        course5.setCourseName("Best Practices for iOS User Interface Design");
        course5.setInstructorName("Don Patterson");
        course5.setRating((float) 4.90);

        course6.setCourseName("Advanced Data Structures in Java");
        course6.setInstructorName("Adam Porter");
        course6.setRating((float) 4.20);


        queryResultsCurrent.add(course1);
        queryResultsCurrent.add(course2);
        queryResultsFuture.add(course5);
        queryResultsFuture.add(course4);
        queryResultsPast.add(course6);
        queryResultsPast.add(course3);

        //after background query done, update UI
        showListCourseResult(lvCurrent,queryResultsCurrent);
        showListCourseResult(lvFuture,queryResultsFuture);
        showListCourseResult(lvPast,queryResultsPast);
    }

    public void showListCourseResult(ListView courseListView,final List<CourseItem> queryResults ){


        //create an ArrayAdapter
        ArrayAdapter<CourseItem> adapter = new ArrayAdapter<CourseItem>(getApplicationContext(),
                R.layout.listview_course_item, queryResults){
            @Override
            public View getView(int position, View convertView, ViewGroup parent){

                if(convertView == null){
                    convertView = getLayoutInflater().inflate(R.layout.listview_course_item, parent, false);
                }

                TextView tvName = (TextView)convertView.findViewById(R.id.course_name);
                TextView tvInstructor = (TextView)convertView.findViewById(R.id.course_instructor);
                ImageView ivthumbnail = (ImageView)convertView.findViewById(R.id.course_thumbnail);
                RatingBar rbCourseRate = (RatingBar)convertView.findViewById(R.id.course_rate);

                course = new CourseItem();
                course = queryResults.get(position);

                tvName.setText(course.getCourseName());
                tvInstructor.setText(course.getInstructorName());
                rbCourseRate.setRating(course.getRating());

                //todo Image may save as URL at database,here is hard code
                ivthumbnail.setImageResource(R.drawable.course_thumbnail_example);
                //Uri uri = Uri.parse("android.resource://edu.sjsu.qi.onlinecommunity/"+course.getURL());
                //ivCourseImage.setImageURI(uri);
                return convertView;
            }
        };

        //Assign adapter to ListView
        courseListView.setAdapter(adapter);

    }


}
