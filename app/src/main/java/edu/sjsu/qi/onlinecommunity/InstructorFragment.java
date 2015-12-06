package edu.sjsu.qi.onlinecommunity;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class InstructorFragment extends Fragment {

    private static final String TAG = CourseFragment.class.getSimpleName();

    private String category = "";
    private TextView textView;
    private ListView instructorListView;
    private List<InstructorItem> queryResults;

    public InstructorFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        queryResults = new ArrayList<InstructorItem>();

        //Get Category from ActivityCourseContainer
        category = getArguments().getString("Category");
        Log.d(TAG, "Category from CourseContainer Activity: " + category);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_instructor, container, false);

        //Set the textView with category
        textView = (TextView)view.findViewById(R.id.textView_cat);
        textView.setText(category);

        //Set the ListView of courses
        instructorListView = (ListView)view.findViewById(R.id.listView_instructor);

        //query instructor list by Category
        queryInstructorListFromDB();

        //sets the OnItemClickListener of the ListView
        //so the user can click on a course and go to the course intro
        instructorListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> av, View v, int pos, long id) {
                Intent intent = new Intent(getActivity(), ActivityInstructorIntro.class);

                //TODO: implement to get Instructor ID and send to Activity InstructorIntro
                //intent.putExtra("Instructor_ID", QueryResults.get(pos).getId());
                startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        inflater.inflate(R.menu.menu_search, menu);

        //Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(getActivity().SEARCH_SERVICE);
        final SearchView searchView = (SearchView) menu.findItem(R.id.menu_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
        searchView.setMaxWidth(920);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchView.clearFocus();
                if (!TextUtils.isEmpty(query)) {
                    //TODO: may need implement search function, such as user search one instructor,
                    //here is only show the search Text
                    Toast.makeText(getActivity().getApplicationContext(), "You are searching " + query,
                            Toast.LENGTH_LONG).show();
                }
                return true;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    //query Instructors for current Category
    public void queryInstructorListFromDB(){

        //TODO: implement the AsyncTask to query list of instructor by key word "category"

        //The following is hardcode
        InstructorItem instructor1 = new InstructorItem();
        InstructorItem instructor2 = new InstructorItem();
        instructor1.setInstructorName("Don Patterson");
        instructor1.setSchool("University of California, Irvine");
        instructor1.setRating((float) 4.6);
        instructor2.setInstructorName("Dr. Adam Porter");
        instructor2.setSchool("University of Maryland, College Park");
        instructor2.setRating((float) 4.3);
        queryResults.add(instructor1);
        queryResults.add(instructor2);

        //update query result and show in ListView
        showListCourseResult();
    }

    public void showListCourseResult(){

        //create an ArrayAdapter
        ArrayAdapter<InstructorItem> adapter = new ArrayAdapter<InstructorItem>(getActivity().getApplicationContext(),
                R.layout.listview_instructor_item, queryResults){
            @Override
            public View getView(int position, View convertView, ViewGroup parent){

                if(convertView == null){
                    convertView = getActivity().getLayoutInflater().inflate(R.layout.listview_instructor_item, parent, false);
                }

                TextView tvName = (TextView)convertView.findViewById(R.id.instructor_name);
                TextView tvSchool = (TextView)convertView.findViewById(R.id.instructor_school);
                ImageView ivthumbnail = (ImageView)convertView.findViewById(R.id.instructor_thumbnail);
                RatingBar rbCourseRate = (RatingBar)convertView.findViewById(R.id.instructor_rate);

                InstructorItem instructor = queryResults.get(position);

                tvName.setText(instructor.getInstructorName());
                tvSchool.setText(instructor.getSchool());
                rbCourseRate.setRating(instructor.getRating());

                //todo Image may save as URL at database,here is hard code
                ivthumbnail.setImageResource(R.drawable.instructor_example);
                //Uri uri = Uri.parse("android.resource://edu.sjsu.qi.onlinecommunity/"+course.getURL());
                //ivCourseImage.setImageURI(uri);
                return convertView;
            }
        };

        //Assign adapter to ListView
        instructorListView.setAdapter(adapter);

    }


}
