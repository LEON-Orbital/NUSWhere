package com.example.mainpage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class FacultyFoodActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_food);

        ArrayList<Food> bizFoodList = getIntent().getParcelableArrayListExtra("addBiz");
        ArrayList<Food> comFoodList = getIntent().getParcelableArrayListExtra("addCom");
        ArrayList<Food> engineFoodList = getIntent().getParcelableArrayListExtra("addEngine");
        ArrayList<Food> fassFoodList = getIntent().getParcelableArrayListExtra("addFASS");
        ArrayList<Food> medFoodList = getIntent().getParcelableArrayListExtra("addMed");
        ArrayList<Food> sciFoodList = getIntent().getParcelableArrayListExtra("addSci");
        ArrayList<Food> sdeFoodList = getIntent().getParcelableArrayListExtra("addSDE");
        ArrayList<Food> utownFoodList = getIntent().getParcelableArrayListExtra("addUTown");
        ArrayList<Food> ystFoodList = getIntent().getParcelableArrayListExtra("addYST");


        // back button
        ImageButton back = findViewById(R.id.foodBackBtn);
        back.setOnClickListener(v -> finish());

        // food button
        ImageButton foodActivity = findViewById(R.id.foodBtn);
        foodActivity.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), FoodActivity.class);
            startActivity(intent);
        });

        // study button
        ImageButton studyActivity = findViewById(R.id.studyBtn);
        studyActivity.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), StudyActivity.class);
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

        // BUSINESS button
        CardView bizCardView = findViewById(R.id.bizCardView);
        bizCardView.setOnClickListener(v -> {
            Intent intent = new Intent(FacultyFoodActivity.this, FoodBizActivity.class);
            intent.putExtra("add", bizFoodList);
            startActivity(intent);
        });

        // COMPUTING button
        CardView comCardView = findViewById(R.id.comCardView);
        comCardView.setOnClickListener(v -> {
            Intent intent = new Intent(FacultyFoodActivity.this, FoodComActivity.class);
            intent.putExtra("add", comFoodList);
            startActivity(intent);
        });

        // ENGINEERING button
        CardView engineCardView = findViewById(R.id.engineCardView);
        engineCardView.setOnClickListener(v -> {
            Intent intent = new Intent(FacultyFoodActivity.this, FoodEngineActivity.class);
            intent.putExtra("add", engineFoodList);
            startActivity(intent);
        });

        // FASS button
        CardView fassCardView = findViewById(R.id.fassCardView);
        fassCardView.setOnClickListener(v -> {
            Intent intent = new Intent(FacultyFoodActivity.this, FoodFASSActivity.class);
            intent.putExtra("add", fassFoodList);
            startActivity(intent);
        });

        // MEDICINE button
        CardView medCardView = findViewById(R.id.medCardView);
        medCardView.setOnClickListener(v -> {
            Intent intent = new Intent(FacultyFoodActivity.this, FoodMedicineActivity.class);
            intent.putExtra("add", medFoodList);
            startActivity(intent);
        });

        // SCIENCE button
        CardView scienceCardView = findViewById(R.id.sciCardView);
        scienceCardView.setOnClickListener(v -> {
            Intent intent = new Intent(FacultyFoodActivity.this, FoodScienceActivity.class);
            intent.putExtra("add", sciFoodList);
            startActivity(intent);
        });

        // SDE button
        CardView sdeCardView = findViewById(R.id.sdeCardView);
        sdeCardView.setOnClickListener(v -> {
            Intent intent = new Intent(FacultyFoodActivity.this, FoodSDEActivity.class);
            intent.putExtra("add", sdeFoodList);
            startActivity(intent);
        });

        // UTOWN button
        CardView utownCardView = findViewById(R.id.utownCardView);
        utownCardView.setOnClickListener(v -> {
            Intent intent = new Intent(FacultyFoodActivity.this, FoodUtownActivity.class);
            intent.putExtra("add", utownFoodList);
            startActivity(intent);
        });

        // YSTCM button
        CardView ystcmCardView = findViewById(R.id.ystCardView);
        ystcmCardView.setOnClickListener(v -> {
            Intent intent = new Intent(FacultyFoodActivity.this, FoodYSTCMActivity.class);
            intent.putExtra("add", ystFoodList);
            startActivity(intent);
        });
    }
}


