package com.komarov.calculator;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = MainActivity.this;
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TabHost tabHost = (TabHost) findViewById(R.id.tabHost);
        tabHost.setup();
        TabHost.TabSpec tabSpec;

        tabSpec = tabHost.newTabSpec("tagMain");
        tabSpec.setIndicator(getResources().getString(R.string.title_activity_main_calc));
        tabSpec.setContent(R.id.mainCalcLayout);
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("tagEngineer");
        tabSpec.setIndicator(getResources().getString(R.string.title_activity_engineer_calc));
        tabSpec.setContent(R.id.engineerCalcLayout);
        tabHost.addTab(tabSpec);

        tabHost.setCurrentTab(0);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean res = false;
        int selectedId = item.getItemId();
        switch (selectedId) {
            case R.id.about: {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle(R.string.menu_about_title)
                        .setIcon(R.drawable.ic_action_about)
                        .setMessage(R.string.menu_about_message)
                        .setCancelable(false)
                        .setNegativeButton(R.string.button_ok,
                                (dialog, id) -> dialog.cancel());
                AlertDialog alert = builder.create();
                alert.show();
                break;
            }
            case R.id.exit: {
                res = true;
                AlertDialog.Builder ad = new AlertDialog.Builder(context);
                ad.setTitle(R.string.menu_exit_title);
                ad.setMessage(R.string.menu_exit_message);
                ad.setPositiveButton(R.string.button_ok, (dialog, arg1) -> {
                    finish();
                    System.exit(0);
                });
                ad.setNegativeButton(R.string.button_cancel, (dialog, arg1) -> {
                    Toast.makeText(context, R.string.menu_exit_cancel_text, Toast.LENGTH_LONG)
                            .show();
                });
                ad.show();
                break;
            }
            default: {
                break;
            }
        }
        return res;
    }

}
