package edu.sjsu.qi.onlinecommunity;

import android.app.ActionBar;
import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class ActivityChat extends Activity {
    private static final String TAG = ActivityChat.class.getSimpleName();
    private ImageButton ibBack;
    private ListView lvChat;
    private Button btSend;
    private EditText etMessage;
    private List<ChatItem> listChat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        btSend = (Button)findViewById(R.id.bt_Send);
        etMessage = (EditText)findViewById(R.id.et_message);
        lvChat = (ListView)findViewById(R.id.listView_chat);

        listChat = new ArrayList<ChatItem>();


        //TODO Implement chat


        //create an ArrayAdapter
        ArrayAdapter<ChatItem> adapter = new ArrayAdapter<ChatItem>(getApplicationContext(),
                R.layout.listview_chat_me, listChat){
            @Override
            public View getView(int position, View convertView, ViewGroup parent){

                if(convertView == null){

                    //if user is myself
                    //convertView = getLayoutInflater().inflate(R.layout.listview_chat_me, parent, false);
                    //if user is other
                    //convertView = getLayoutInflater().inflate((R.layout.listview_chat_others,parent, false);
                }
                ChatItem chat = listChat.get(position);
                return convertView;
            }
        };

        //Assign adapter to ListView
        lvChat.setAdapter(adapter);



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
        getMenuInflater().inflate(R.menu.menu_activity_chat, menu);
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
}
