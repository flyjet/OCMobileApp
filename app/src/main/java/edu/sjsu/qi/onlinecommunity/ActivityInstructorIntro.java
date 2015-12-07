package edu.sjsu.qi.onlinecommunity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class ActivityInstructorIntro extends Activity {

    private static final String TAG = ActivityInstructorIntro.class.getSimpleName();

    private ImageView ivIntro, ivThumbnail, ivCourseThumbnail;
    private ImageButton ibBack;

    private List<CourseItem> queryResults;
    private InstructorItem instructor;

    private TextView tvName, tvSchool, tvDescription, tvCourseName;
    private RatingBar rating, courseRate;
    private ListView courseListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor_intro);

        ivThumbnail = (ImageView)findViewById(R.id.instructor_thumbnail);
        tvName = (TextView)findViewById(R.id.instructor_name);
        tvSchool = (TextView)findViewById(R.id.instructor_school);
        tvDescription = (TextView)findViewById(R.id.textView_DesContent);
        rating = (RatingBar)findViewById(R.id.instructor_rate);

        courseListView = (ListView)findViewById(R.id.lv_course);

        //sets the OnItemClickListener of the ListView
        //so the user can click on a course and go to the course intro
        courseListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> av, View v, int pos, long id) {
                Intent intent = new Intent(ActivityInstructorIntro.this, ActivityCourseIntro.class);

                //TODO: implement to get course ID and send to Activity CourseIntro
                //intent.putExtra("COURSE_ID", QueryResults.get(pos).getId());

                startActivity(intent);
            }
        });

        /*Remove the bigImage for instructor
        ivIntro = (ImageView)findViewById(R.id.imageView_intro);
        //when user click the the imageView, it will go to Activity VideoPlayer
        ivIntro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityInstructorIntro.this, ActivityVideoPlayer.class);
                intent.putExtra("InstructorName", instructorName);
                startActivity(intent);
            }
        });*/

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

        //TODO Implement to query instructor information by instructor ID or name
        queryInstructorDetailFromDB();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_instructor_intro, menu);
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

    // Query instructor detail information by instructor ID or name
    void queryInstructorDetailFromDB(){

        //TODO background task to query instructor details
        // and courses the instructor teach


        //The following is hard code
        instructor = new InstructorItem();
        instructor.setInstructorName("Don Patterson");
        instructor.setSchool("University of California, Irvine");
        instructor.setRating((float) 4.9);
        instructor.setDescription("Don Patterson is Associate Professor of Donald Bren School " +
                "of Information and Computer Sciences. He got his Ph.D. from University of Washington" +
                "at 2005. And he conducts research in the areas of Ubiquitous Computing, " +
                "Human Computer Interaction, Artificial Intelligence.");

        queryResults = new ArrayList<CourseItem>();

        CourseItem course1 = new CourseItem();
        CourseItem course2 = new CourseItem();
        CourseItem course3 = new CourseItem();
        course1.setCourseName("Foundations of Objective-C App Development");
        course1.setRating((float) 3.80);

        course2.setCourseName("iOS App Development Basics");
        course2.setRating((float) 4.60);

        course3.setCourseName("Best Practices for iOS User Interface Design");
        course3.setRating((float)4.90);

        queryResults.add(course1);
        queryResults.add(course2);

        //when done, update view
        updateInstructorDetail();
    }

    void updateInstructorDetail(){

        tvName.setText(instructor.getInstructorName());
        tvSchool.setText(instructor.getSchool());
        tvDescription.setText(instructor.getDescription());
        ivThumbnail.setImageResource(R.drawable.instructor_example);
        //ivIntro.setImageResource(R.drawable.instructor_background_example);
        rating.setRating(instructor.getRating());


        //create an ArrayAdapter
        ArrayAdapter<CourseItem> adapter = new ArrayAdapter<CourseItem>(getApplicationContext(),
                R.layout.listview_course_item_without_instructor, queryResults){
            @Override
            public View getView(int position, View convertView, ViewGroup parent){

                if(convertView == null){
                    convertView = getLayoutInflater().inflate(R.layout.listview_course_item, parent, false);
                }

                ivCourseThumbnail = (ImageView)convertView.findViewById(R.id.course_thumbnail);
                tvCourseName = (TextView)convertView.findViewById(R.id.course_name);
                courseRate = (RatingBar)convertView.findViewById(R.id.course_rate);

                CourseItem course = queryResults.get(position);

                tvCourseName.setText(course.getCourseName());
                courseRate.setRating(course.getRating());
                //todo Image may save as URL at database,here is hard code
                ivCourseThumbnail.setImageResource(R.drawable.course_thumbnail_example);

                return convertView;
            }
        };
        //Assign adapter to Listview
        courseListView.setAdapter(adapter);
    }

}
