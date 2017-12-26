package com.yan.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class ActivityViewPagerFragmentLifeCycleTest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction().add(R.id.fl, new ViewPagerFragment()).commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("Activity", "onDestroy: ");
    }
}


