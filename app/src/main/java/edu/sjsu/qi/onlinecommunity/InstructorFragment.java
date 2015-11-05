package edu.sjsu.qi.onlinecommunity;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;


public class InstructorFragment extends Fragment {

    private static final String TAG = CourseFragment.class.getSimpleName();

    private String category = "";
    private TextView textView;
    private Handler handler;
    private ListView instructorList;

    public InstructorFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

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
        handler = new Handler();
        instructorList = (ListView)view.findViewById(R.id.listView_instructor);


        //TODO: the following code is hard code to click one course in the listview
        RelativeLayout layout = (RelativeLayout)view.findViewById(R.id.layout_instructorItem);
        final TextView textView = (TextView)view.findViewById(R.id.instructor_name);

        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ActivityInstructorIntro.class);
                intent.putExtra("InstructorName", textView.getText().toString());
                startActivity(intent);
            }
        });

        //TODO: implement the AsyncTask to query list of instructor by key word "category"
        //TODO: implement post task to update query result and show in ListView

        //sets the OnItemClickListener of the ListView
        //so the user can click on a course and go to the course intro
        instructorList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> av, View v, int pos, long id) {
                Intent intent = new Intent(getActivity(), ActivityCourseIntro.class);

                //TODO: implement to get Instructor ID and send to Activity InstructorIntro
                //intent.putExtra("Instructor_ID", QueryResults.get(pos).getId());
                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        inflater.inflate(R.menu.menu_search,menu);

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
                    //TODO: need implement search function, such as user search one instructor,
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

}
