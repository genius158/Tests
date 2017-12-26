package com.yan.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class ActivityFragmentLifeCycleTest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction().add(R.id.fl, LifeCycleFragment.inner()).commit();
    }
}
