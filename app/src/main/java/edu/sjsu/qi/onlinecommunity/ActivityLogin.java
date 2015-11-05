package edu.sjsu.qi.onlinecommunity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class ActivityLogin extends Activity {
    private static final String TAG = ActivityLogin.class.getSimpleName();

    Button bt_login_done, bt_signup;
    TextView tv_email, tv_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //set up the action bar
        android.app.ActionBar actionBar = getActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.mipmap.ic_launcher);

        //Initialize UI elements - two buttons
        bt_login_done = (Button) findViewById(R.id.button_login_done);
        bt_signup = (Button) findViewById(R.id.button_signup);
        tv_email = (TextView) findViewById(R.id.text_email);
        tv_password = (TextView) findViewById(R.id.text_password);

        //Actions of Login Button
        bt_login_done.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                //TODO: Need implement to verify user email and password



                //Goto activity Category
                try{
                    Intent intent = new Intent(ActivityLogin.this, ActivityCategory.class);
                    startActivity(intent);
                }catch(Exception e){
                    Log.e(TAG, e.toString());
                }
            }
        });

        //Actions of signup Button - goto activity signup
        bt_signup.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                try{
                    Intent intent = new Intent(ActivityLogin.this, ActivitySignup.class);
                    startActivity(intent);
                }catch(Exception e){
                    Log.e(TAG, e.toString());
                }
            }
        });

    }

}
