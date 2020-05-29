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
            //BUSINESS
            if (position == 2) {
                Intent showBiz = new Intent(getApplicationContext(), StudyBusinessActivity.class);
                startActivity(showBiz); //switches screen
            }

            ///////////// LIBRARIES ////////////////
            //Central Library
            if (position == 11) {
                Intent showCLB = new Intent(getApplicationContext(), StudyCentralLibraryActivity.class);
                showCLB.putExtra("add", libraryList.getLibrary("Central Library"));
                startActivity(showCLB);
            }
/*
            //Chinese Library
            if (position == 12) {
                Intent showCL = new Intent(getApplicationContext(), StudyChineseLibraryActivity.class);
                startActivity(showCL);
            }

            //Hon Sui Sen Memorial Library
            if (position == 13) {
                Intent showMemL = new Intent(getApplicationContext(), StudyMemorialLibraryActivity.class);
                startActivity(showMemL);
            }

            //Medical Library
            if (position == 14) {
                Intent showMedL = new Intent(getApplicationContext(), StudyMedicalLibraryActivity.class);
                startActivity(showMedL);
            }

            //Music Library
            if (position == 15) {
                Intent showMusB = new Intent(getApplicationContext(), StudyMusicLibraryActivity.class);
                startActivity(showMusB);
            }

            //Science Library
            if (position == 16) {
                Intent showSL = new Intent(getApplicationContext(), StudyScienceLibraryActivity.class);
                startActivity(showSL);
            }

*/
        });


    }
}
