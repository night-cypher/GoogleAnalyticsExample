package com.questdot.googleanalyticsexample;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.StandardExceptionParser;
import com.google.android.gms.analytics.Tracker;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bNextScreen = (Button) findViewById(R.id.bNextScreen);
        Button bFragment = (Button) findViewById(R.id.bFragment);
        Button bEvent = (Button) findViewById(R.id.bEvent);
        Button bException = (Button) findViewById(R.id.bException);
        Button bCrashApp = (Button) findViewById(R.id.bCrashApp);

        bNextScreen.setOnClickListener(this);
        bFragment.setOnClickListener(this);
        bEvent.setOnClickListener(this);
        bException.setOnClickListener(this);
        bCrashApp.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        BaseApplication.getInstance().trackScreenView("Main Screen Now");
    }

    @Override
    public void onClick(View view) {

        switch(view.getId()){
            case R.id.bNextScreen:
                Intent i = new Intent(MainActivity.this, AnotherActivity.class);
                startActivity(i);
                break;

            case R.id.bFragment:


                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                android.support.v4.app.Fragment myFragment = (android.support.v4.app.Fragment) fragmentManager.findFragmentByTag("TAG_FRAGMENT");
                if (myFragment == null) {

                    myFragment = new Fragment();

                    fragmentTransaction.replace(R.id.container, myFragment, "TAG_FRAGMENT");
                    fragmentTransaction.commit();

                } else {
                    fragmentTransaction.remove(myFragment).commit();

                }
                break;

            case R.id.bEvent:

                BaseApplication.getInstance().trackEvent("Category","Action","Label");

                Toast.makeText(MainActivity.this, "Event is recorded", Toast.LENGTH_LONG).show();
                break;

            case R.id.bException:
                Exception e = null;

                try{
                    int num[]={1,2,3,4};
                    System.out.println(num[5]);
                }catch (Exception f){
                    e = f;
                }
                if( e != null){

                    Toast.makeText(MainActivity.this, "The Exception is: " + e, Toast.LENGTH_LONG).show();

                    BaseApplication.getInstance().trackException(e);
                }


                break;

            case R.id.bCrashApp:

                Toast.makeText(getApplicationContext(), "App Crash!", Toast.LENGTH_LONG).show();
                Runnable r = new Runnable() {
                    @Override
                    public void run() {
                        startCrash();

                    }
                    public void startCrash() {
                        throw null;
                    }

                };
                Handler h = new Handler();
                h.postDelayed(r, 1500);
                break;
        }

    }
}