package com.questdot.googleanalyticsexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

public class AnotherActivity extends AppCompatActivity {


    private Tracker mTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_another);

        //Analytics Integration
        // Obtain the shared Tracker instance.
        BaseApplication application = (BaseApplication) getApplication();
        mTracker = application.getDefaultTracker();


    }

    @Override
    protected void onResume() {
        super.onResume();

        mTracker.setScreenName("Another Activity");
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());
    }

}