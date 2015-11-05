package edu.sjsu.qi.onlinecommunity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;


public class MainActivity extends Activity {

    private static final String TAG = MainActivity.class.getSimpleName();

    Button bt_login, bt_signup;
    ImageButton imageBt_facebook, imageBt_gmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*//set up the action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.mipmap.ic_launcher); */

        //Initialize UI elements - four buttons
        bt_login = (Button) findViewById(R.id.button_login);
        bt_signup = (Button) findViewById(R.id.button_signup);
        imageBt_facebook = (ImageButton) findViewById(R.id.button_login_facebook);
        imageBt_gmail = (ImageButton) findViewById(R.id.button_login_gmail);

        //Actions of Login Button - goto activity login
        bt_login.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                try{
                    Intent loginIntent = new Intent(MainActivity.this, ActivityLogin.class);
                    startActivity(loginIntent);
                }catch(Exception e){
                    Log.e(TAG, e.toString());
                }
            }
        });

        //Actions of signup Button - goto activity signup
        bt_signup.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                try{
                    Intent signupIntent = new Intent(MainActivity.this, ActivitySignup.class);
                    startActivity(signupIntent);
                }catch(Exception e){
                    Log.e(TAG, e.toString());
                }
            }
        });

        //TODO: implement actions of facebook_login


        //TODO: implement actions of gmail_login
    }

}
