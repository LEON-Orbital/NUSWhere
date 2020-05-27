package com.example.mainpage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

public class StudyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study);

        LibraryList libraryList = new LibraryList();

        ImageButton back = findViewById(R.id.studyBackBtn);
        back.setOnClickListener(v -> finish());

        //Study area list
        ListView listView = findViewById(R.id.studyAreaListView);
        Resources res = getResources();
        String[] studyAreas = res.getStringArray(R.array.studyAreas);

        StudyListAdapter studyListAdapter = new StudyListAdapter(this, studyAreas);
        listView.setAdapter(studyListAdapter);

        ///////////////////////////////////////////////////////////////////

        // food button
        ImageButton foodActivity = findViewById(R.id.foodBtn);
        foodActivity.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), FoodActivity.class);
            startActivity(intent);
        });

        // bus button
        ImageButton busActivity = findViewById(R.id.busBtn);
        busActivity.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), BusActivity.class);
            startActivity(intent);
        });

        // map button
        ImageButton mapActivity = findViewById(R.id.mapBtn);
        mapActivity.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), MapActivity.class);
            startActivity(intent);
        });

        ///////////////////////////////////////////////////////////////////

        listView.setOnItemClickListener((parent, view, position, id) -> {
            if (position == 2) {
                Intent showBiz = new Intent(getApplicationContext(), StudyBusinessActivity.class);
                startActivity(showBiz); //switches screen
            }
        });


    }
}
