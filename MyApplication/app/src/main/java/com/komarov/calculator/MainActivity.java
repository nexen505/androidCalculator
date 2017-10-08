package com.komarov.calculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TabHost;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        View contentLayout = findViewById(R.id.mainContent);
        TabHost tabHost = contentLayout.findViewById(R.id.tabhost);
        TabHost.TabSpec tabSpec;

        tabSpec = tabHost.newTabSpec("tagMain");
        tabSpec.setIndicator(getResources().getString(R.string.title_activity_main_calc));
        tabSpec.setContent(new Intent(this, MainCalcActivity.class));
//        tabSpec.setContent(R.id.activity_main_calc);
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("tagEngineer");
        tabSpec.setIndicator(getResources().getString(R.string.title_activity_engineer_calc));
        tabSpec.setContent(new Intent(this, EngineerCalcActivity.class));
//        tabSpec.setContent(R.id.activity_engineer_calc);
        tabHost.addTab(tabSpec);

        tabHost.setCurrentTab(0);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        });
    }

}
