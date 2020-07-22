package com.example.mainpage.study;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.mainpage.MainActivity;
import com.example.mainpage.bus.BusActivity;
import com.example.mainpage.map.GoogleMaps;
import com.example.mainpage.R;
import com.example.mainpage.food.FoodActivity;

public class StudyActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study);

        ImageButton studyActivity = findViewById(R.id.studyBtn);
        studyActivity.setImageResource(R.drawable.study_button);

        LibraryList libraryList = new LibraryList();

        //Study area list
        ListView listView = findViewById(R.id.studyAreaListView);
        Resources res = getResources();
        String[] studyAreas = res.getStringArray(R.array.studyAreas);

        StudyListAdapter studyListAdapter = new StudyListAdapter(this, studyAreas);
        listView.setAdapter(studyListAdapter);

        ///////////////////////////////////////////////////////////////////

        ImageButton homeActivity = findViewById(R.id.homeBtn);
        ImageButton foodActivity = findViewById(R.id.foodBtn);
        ImageButton busActivity = findViewById(R.id.busBtn);
        ImageButton mapActivity = findViewById(R.id.mapBtn);

        homeActivity.setOnClickListener(this);
        foodActivity.setOnClickListener(this);
        busActivity.setOnClickListener(this);
        mapActivity.setOnClickListener(this);

        ///////////////////////////////////////////////////////////////////

        listView.setOnItemClickListener((parent, view, position, id) -> {
            //FASS
            if (position == 1) {
                Intent showFASS = new Intent(getApplicationContext(), StudyFASSActivity.class);
                startActivity(showFASS); //switches screen
            }

            //BUSINESS
            if (position == 2) {
                Intent showBiz = new Intent(getApplicationContext(), StudyBusinessActivity.class);
                startActivity(showBiz); //switches screen
            }

            //COMPUTING
            if (position == 3) {
                Intent showCom = new Intent(getApplicationContext(), StudyComputingActivity.class);
                startActivity(showCom); //switches screen
            }

            //DENTISTRY
            if (position == 4) {
                Intent showDent = new Intent(getApplicationContext(), StudyDentistryActivity.class);
                startActivity(showDent); //switches screen
            }

            //SDE
            if (position == 5) {
                Intent showSDE = new Intent(getApplicationContext(), StudySDEActivity.class);
                startActivity(showSDE); //switches screen
            }

            //ENGINE
            if (position == 6) {
                Intent showEng = new Intent(getApplicationContext(), StudyEngineActivity.class);
                startActivity(showEng); //switches screen
            }

            //MED
            if (position == 7) {
                Intent showMed = new Intent(getApplicationContext(), StudyMedicineActivity.class);
                startActivity(showMed); //switches screen
            }

            //MUSIC
            if (position == 8) {
                Intent showYST = new Intent(getApplicationContext(), StudyYSTActivity.class);
                startActivity(showYST); //switches screen
            }

            //SCIENCE
            if (position == 9) {
                Intent showSCI = new Intent(getApplicationContext(), StudyScienceActivity.class);
                startActivity(showSCI); //switches screen
            }


            ///////////// LIBRARIES ////////////////
            //Central Library
            if (position == 11) {
                Intent showCLB = new Intent(getApplicationContext(), StudyCentralLibraryActivity.class);
                showCLB.putExtra("add", libraryList.getLibrary("Central Library"));
                startActivity(showCLB);
            }

            //Chinese Library
            if (position == 12) {
                Intent showCL = new Intent(getApplicationContext(), StudyChineseLibraryActivity.class);
                showCL.putExtra("add", libraryList.getLibrary("Chinese Library"));
                startActivity(showCL);
            }

            //Hon Sui Sen Memorial Library
            if (position == 13) {
                Intent showMemL = new Intent(getApplicationContext(), StudyMemorialLibraryActivity.class);
                showMemL.putExtra("add", libraryList.getLibrary("Hon Sui Sen Memorial Library"));
                startActivity(showMemL);
            }

            //Medical Library
            if (position == 14) {
                Intent showMedL = new Intent(getApplicationContext(), StudyMedicalLibraryActivity.class);
                showMedL.putExtra("add", libraryList.getLibrary("Medical Library"));
                startActivity(showMedL);
            }

            //Music Library
            if (position == 15) {
                Intent showMusL = new Intent(getApplicationContext(), StudyMusicLibraryActivity.class);
                showMusL.putExtra("add", libraryList.getLibrary("Music Library"));
                startActivity(showMusL);
            }

            //Science Library
            if (position == 16) {
                Intent showSL = new Intent(getApplicationContext(), StudyScienceLibraryActivity.class);
                showSL.putExtra("add", libraryList.getLibrary("Science Library"));
                startActivity(showSL);
            }

            //Yale Nus Library
            if (position == 17) {
                Intent showYale = new Intent(getApplicationContext(), StudyYaleLibraryActivity.class);
                showYale.putExtra("add", libraryList.getLibrary("Yale-NUS College Library"));
                startActivity(showYale);
            }

            ///////////// LIBRARIES ////////////////
            //UTOWN
            if (position == 19) {
                Intent showUtown = new Intent(getApplicationContext(), StudyUtownActivity.class);
                startActivity(showUtown); //switches screen
            }

        });
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.homeBtn:
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                break;
            case R.id.foodBtn:
                startActivity(new Intent(getApplicationContext(), FoodActivity.class));
                break;
            case R.id.busBtn:
                startActivity(new Intent(getApplicationContext(), BusActivity.class));
                break;
            case R.id.mapBtn:
                startActivity(new Intent(StudyActivity.this, GoogleMaps.class));
                break;
        }
    }

}
