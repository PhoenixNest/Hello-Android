package com.dev.a0229lifecycles;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private MyChronometer chronometer;
    private long elapsedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chronometer = findViewById(R.id.chronometer);
        getLifecycle().addObserver(chronometer);
//        chronometer.setBase(SystemClock.elapsedRealtime());
//        chronometer.start();
    }

//    @Override
//    protected void onPause() {
//        super.onPause();
//        elapsedTime = System.currentTimeMillis() - chronometer.getBase();
//        chronometer.stop();
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        chronometer.setBase(SystemClock.elapsedRealtime() - elapsedTime);
//        chronometer.start();
//    }
}
