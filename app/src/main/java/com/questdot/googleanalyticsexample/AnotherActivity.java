package com.questdot.googleanalyticsexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

public class AnotherActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_another);




    }

    @Override
    protected void onResume() {
        super.onResume();

        BaseApplication.getInstance().trackScreenView("Another Screen lo");
    }

}