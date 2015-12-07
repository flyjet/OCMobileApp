package edu.sjsu.qi.onlinecommunity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class ActivitySignup extends Activity {

    private static final String TAG = ActivitySignup.class.getSimpleName();

    Button bt_login, bt_signup_done;
    TextView tv_name, tv_email, tv_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        //set up the action bar
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.mipmap.ic_launcher);

        //Initialize UI elements - two buttons
        bt_login = (Button) findViewById(R.id.button_login);
        bt_signup_done = (Button) findViewById(R.id.button_signup_done);
        tv_name = (TextView) findViewById(R.id.text_name);
        tv_email = (TextView) findViewById(R.id.text_email);
        tv_password = (TextView) findViewById(R.id.text_password);


        //Actions of Login Button
        bt_login.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                //Goto activity Login
                try{
                    Intent intent = new Intent(ActivitySignup.this, ActivityLogin.class);
                    startActivity(intent);
                }catch(Exception e){
                    Log.e(TAG, e.toString());
                }
            }
        });

        //Actions of signup done Button
        bt_signup_done.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                //TODO: Need implement to add new user with name, email and password


                //Goto activity Login
                try{
                    Intent intent = new Intent(ActivitySignup.this, ActivityLogin.class);
                    startActivity(intent);
                }catch(Exception e){
                    Log.e(TAG, e.toString());
                }
            }
        });
    }

}
