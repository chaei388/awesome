package com.awesome.awesome;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.awesome.awesome.sql.SQLiteHelper;
import com.awesome.awesome.tab.Complete;
import com.awesome.awesome.tab.Incomplete;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    SQLiteHelper sqLiteHelper;
    Complete complete;
    Incomplete incomplete;

    @Override
    protected void onDestroy() {


        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sqLiteHelper = new SQLiteHelper(getApplicationContext());

        complete = new Complete();
        incomplete = new Incomplete();

        getSupportFragmentManager().beginTransaction().add(R.id.list, incomplete).commit();

        TabLayout tabs = findViewById(R.id.tabs);

        tabs.addTab(tabs.newTab().setText("미완료"));
        tabs.addTab(tabs.newTab().setText("완료"));

        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();

                Fragment selected = null;
                if (position == 0) {
                    selected = incomplete;
                }
                else if (position == 1) {
                    selected = complete;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.list, selected).commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}