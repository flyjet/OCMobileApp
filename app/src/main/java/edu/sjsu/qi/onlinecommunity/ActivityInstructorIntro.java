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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class ActivityInstructorIntro extends Activity {

    private static final String TAG = ActivityInstructorIntro.class.getSimpleName();

    private ImageView ivIntro, ivThumbnail, ivCourseThumbnail;
    private ImageButton ibBack;
    private String instructorName;

    private List<CourseItem> listCourses;
    private InstructorItem instructor;

    private TextView tvName, tvSchool, tvDescription, tvCourseName;
    private RatingBar rating, courseRate;
    private ListView lvCourse;
    private LinearLayout linearLayoutCourse;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor_intro);

        ivThumbnail = (ImageView)findViewById(R.id.instructor_thumbnail);
        tvName = (TextView)findViewById(R.id.instructor_name);
        tvSchool = (TextView)findViewById(R.id.instructor_school);
        tvDescription = (TextView)findViewById(R.id.textView_DesContent);
        rating = (RatingBar)findViewById(R.id.instructor_rate);

        linearLayoutCourse = (LinearLayout)findViewById(R.id.linearLayout_course);
        linearLayoutCourse.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(ActivityInstructorIntro.this, ActivityCourseIntro.class);

                //TODO may have bug to go to next activity
                intent.putExtra("CourseName", tvCourseName.getText().toString());
                startActivity(intent);

            }
        });


        ivIntro = (ImageView)findViewById(R.id.imageView_intro);
        //when user click the the imageView, it will go to Activity VideoPlayer
        ivIntro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityInstructorIntro.this, ActivityVideoPlayer.class);
                intent.putExtra("InstructorName", instructorName);
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

        listCourses = new ArrayList<CourseItem>();

        CourseItem course1 = new CourseItem();
        CourseItem course2 = new CourseItem();
        CourseItem course3 = new CourseItem();
        course1.setCourseName("Foundations of Objective-C App Development");
        course1.setRating((float) 3.80);

        course2.setCourseName("iOS App Development Basics");
        course2.setRating((float) 4.60);

        course3.setCourseName("Best Practices for iOS User Interface Design");
        course3.setRating((float)4.90);



        listCourses.add(course1);
        listCourses.add(course2);

        //when done, update view
        updateInstructorDetail();
    }

    void updateInstructorDetail(){

        tvName.setText(instructor.getInstructorName());
        tvSchool.setText(instructor.getSchool());
        tvDescription.setText(instructor.getDescription());
        ivThumbnail.setImageResource(R.drawable.instructor_example);
        ivIntro.setImageResource(R.drawable.instructor_background_example);
        rating.setRating(instructor.getRating());



        for(int i=0; i<listCourses.size(); i++ ){

            View convertView = LayoutInflater.from(getApplicationContext()).inflate(
                    R.layout.listview_course_item_without_instructor, null);
            linearLayoutCourse.addView(convertView);

            ivCourseThumbnail = (ImageView)convertView.findViewById(R.id.course_thumbnail);
            tvCourseName = (TextView)convertView.findViewById(R.id.course_name);
            courseRate = (RatingBar)convertView.findViewById(R.id.course_rate);

            CourseItem course = new CourseItem();
            course = listCourses.get(i);
            //Todo may need do background Task to download course Thumbnail
            //after done, set view value

            ivCourseThumbnail.setImageResource(R.drawable.course_thumbnail_example);
            tvCourseName.setText(course.getCourseName());
            courseRate.setRating(course.getRating());

        }







        /* Can not use listview in scrollview
        //create an ArrayAdapter to show the courses listview

        ArrayAdapter<CourseItem> adapter = new ArrayAdapter<CourseItem>(getApplicationContext(),
                R.layout.listview_course_item_without_instructor,listCourses){

            @Override
            public View getView(int position, View convertView, ViewGroup parent){

                if(convertView == null){
                    convertView = getLayoutInflater().inflate(R.layout.listview_course_item_without_instructor,
                           parent, false);
                }

                ivCourseThumbnail = (ImageView)convertView.findViewById(R.id.course_thumbnail);
                tvCourseName = (TextView)convertView.findViewById(R.id.course_name);
                courseRate = (RatingBar)convertView.findViewById(R.id.course_rate);

                CourseItem course = new CourseItem();
                course = listCourses.get(position);
                //Todo may need do background Task to download course Thumbnail
                //after done, set view value

                ivCourseThumbnail.setImageResource(R.drawable.course_thumbnail_example);
                tvCourseName.setText(course.getCourseName());
                courseRate.setRating(course.getRating());
                return convertView;
            }
        };
        //Assign adapter to Listview
        lvCourse.setAdapter(adapter);  */
    }

}
