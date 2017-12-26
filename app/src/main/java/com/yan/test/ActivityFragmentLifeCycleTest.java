package com.yan.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class ActivityFragmentLifeCycleTest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction().add(R.id.fl, LifeCycleFragment.inner()).commit();

        findViewById(R.id.fl).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fl,new ViewPagerFragment()).commit();
            }
        });

    }
}
