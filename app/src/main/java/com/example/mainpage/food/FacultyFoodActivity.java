package com.example.mainpage.food;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.mainpage.bus.BusActivity;
import com.example.mainpage.map.GoogleMaps;
import com.example.mainpage.R;
import com.example.mainpage.study.StudyActivity;

import java.util.ArrayList;

public class FacultyFoodActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_food);

        ImageButton foodActivity = findViewById(R.id.foodBtn);
        foodActivity.setImageResource(R.drawable.food_button);

        ImageButton backActivity = findViewById(R.id.backBtn);
        ImageButton studyActivity = findViewById(R.id.studyBtn);
        ImageButton busActivity = findViewById(R.id.busBtn);
        ImageButton mapActivity = findViewById(R.id.mapBtn);

        CardView bizCardView = findViewById(R.id.bizCardView);
        CardView comCardView = findViewById(R.id.comCardView);
        CardView engineCardView = findViewById(R.id.engineCardView);
        CardView fassCardView = findViewById(R.id.fassCardView);
        CardView medCardView = findViewById(R.id.medCardView);
        CardView scienceCardView = findViewById(R.id.sciCardView);
        CardView sdeCardView = findViewById(R.id.sdeCardView);
        CardView utownCardView = findViewById(R.id.utownCardView);
        CardView ystcmCardView = findViewById(R.id.ystCardView);


        backActivity.setOnClickListener(this);
        foodActivity.setOnClickListener(this);
        studyActivity.setOnClickListener(this);
        busActivity.setOnClickListener(this);
        mapActivity.setOnClickListener(this);

        bizCardView.setOnClickListener(this);
        comCardView.setOnClickListener(this);
        engineCardView.setOnClickListener(this);
        fassCardView.setOnClickListener(this);
        medCardView.setOnClickListener(this);
        scienceCardView.setOnClickListener(this);
        sdeCardView.setOnClickListener(this);
        utownCardView.setOnClickListener(this);
        ystcmCardView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            ////////////// Navigation bar //////////////
            case R.id.backBtn:
                finish();
                break;
            case R.id.foodBtn:
                startActivity(new Intent(getApplicationContext(), FoodActivity.class));
                break;
            case R.id.studyBtn:
                startActivity(new Intent(getApplicationContext(), StudyActivity.class));
                break;
            case R.id.busBtn:
                startActivity(new Intent(getApplicationContext(), BusActivity.class));
                break;
            case R.id.mapBtn:
                startActivity(new Intent(getApplicationContext(), GoogleMaps.class));
                break;

            ////////////// Faculty Cases //////////////
            case R.id.bizCardView:
                startActivity(new Intent(FacultyFoodActivity.this, FoodBizActivity.class));
                break;
            case R.id.comCardView:
                startActivity(new Intent(FacultyFoodActivity.this, FoodComActivity.class));
                break;
            case R.id.engineCardView:
                startActivity(new Intent(FacultyFoodActivity.this, FoodEngineActivity.class));
                break;
            case R.id.fassCardView:
                startActivity(new Intent(FacultyFoodActivity.this, FoodFASSActivity.class));
                break;
            case R.id.medCardView:
                startActivity(new Intent(FacultyFoodActivity.this, FoodMedicineActivity.class));
                break;
            case R.id.sciCardView:
                startActivity(new Intent(FacultyFoodActivity.this, FoodScienceActivity.class));
                break;
            case R.id.sdeCardView:
                startActivity(new Intent(FacultyFoodActivity.this, FoodSDEActivity.class));
                break;
            case R.id.utownCardView:
                startActivity(new Intent(FacultyFoodActivity.this, FoodUtownActivity.class));
                break;
            case R.id.ystCardView:
                startActivity(new Intent(FacultyFoodActivity.this, FoodYSTCMActivity.class));
                break;
        }
    }
}


