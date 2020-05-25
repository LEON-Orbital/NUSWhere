package com.example.mainpage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;

public class FacultyFoodActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_food);

        //ArrayList<Food> allFoodList = getIntent().getParcelableArrayListExtra("add");

        final FoodList foodList = new FoodList();
        new DatabaseHandler().readData(new FirebaseCallback() {
            @Override
            public void onCallBack(ArrayList<Food> list) {
                foodList.replace(list);
            }
        }, FacultyFoodActivity.this);

        // back button
        ImageButton back = findViewById(R.id.foodBackBtn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FacultyFoodActivity.this, FoodActivity.class));
            }
        });

        // food button
        ImageButton foodActivity = findViewById(R.id.foodBtn);
        foodActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FoodActivity.class);
                startActivity(intent);
            }
        });

        // study button
        ImageButton studyActivity = findViewById(R.id.studyBtn);
        studyActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), StudyActivity.class);
                startActivity(intent);
            }
        });

        // bus button
        ImageButton busActivity = findViewById(R.id.busBtn);
        busActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BusActivity.class);
                startActivity(intent);
            }
        });

        // map button
        ImageButton mapActivity = findViewById(R.id.mapBtn);
        mapActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MapActivity.class);
                startActivity(intent);
            }
        });

        // BUSINESS button
        CardView bizCardView = findViewById(R.id.bizCardView);
        bizCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FacultyFoodActivity.this, FoodBizActivity.class);
                ArrayList<Food> bizFoodList = foodList.getByCategory(FoodCategory.ALL); // change category to BUSINESS once ready
                intent.putExtra("add", bizFoodList);
                startActivity(intent);
            }
        });

        // COMPUTING button
        CardView comCardView = findViewById(R.id.comCardView);
        comCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FacultyFoodActivity.this, FoodComActivity.class);
                ArrayList<Food> comFoodList = foodList.getByCategory(FoodCategory.ALL); // change category to COMPUTING once ready
                intent.putExtra("add", comFoodList);
                startActivity(intent);
            }
        });

        // ENGINEERING button
        CardView engineCardView = findViewById(R.id.engineCardView);
        engineCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FacultyFoodActivity.this, FoodEngineActivity.class);
                ArrayList<Food> engineFoodList = foodList.getByCategory(FoodCategory.ALL); // change category to ENGINEERING once ready
                intent.putExtra("add", engineFoodList);
                startActivity(intent);
            }
        });

        // FASS button
        CardView fassCardView = findViewById(R.id.fassCardView);
        fassCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FacultyFoodActivity.this, FoodFASSActivity.class);
                ArrayList<Food> fassFoodList = foodList.getByCategory(FoodCategory.ALL); // change category to FASS once ready
                intent.putExtra("add", fassFoodList);
                startActivity(intent);
            }
        });

        // MEDICINE button
        CardView medCardView = findViewById(R.id.medCardView);
        medCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FacultyFoodActivity.this, FoodMedicineActivity.class);
                ArrayList<Food> medFoodList = foodList.getByCategory(FoodCategory.ALL); // change category to MEDICINE once ready
                intent.putExtra("add", medFoodList);
                startActivity(intent);
            }
        });

        // SCIENCE button
        CardView scienceCardView = findViewById(R.id.sciCardView);
        scienceCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FacultyFoodActivity.this, FoodScienceActivity.class);
                ArrayList<Food> scienceFoodList = foodList.getByCategory(FoodCategory.ALL); // change category to SCIENCE once ready
                intent.putExtra("add", scienceFoodList);
                startActivity(intent);
            }
        });

        // SDE button
        CardView sdeCardView = findViewById(R.id.sdeCardView);
        sdeCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FacultyFoodActivity.this, FoodSDEActivity.class);
                ArrayList<Food> sdeFoodList = foodList.getByCategory(FoodCategory.ALL); // change category to SDE once ready
                intent.putExtra("add", sdeFoodList);
                startActivity(intent);
            }
        });

        // UTOWN button
        CardView utownCardView = findViewById(R.id.utownCardView);
        utownCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FacultyFoodActivity.this, FoodUtownActivity.class);
                ArrayList<Food> utownFoodList = foodList.getByCategory(FoodCategory.ALL); // change category to UTOWN once ready
                intent.putExtra("add", utownFoodList);
                startActivity(intent);
            }
        });

        // YSTCM button
        CardView ystcmCardView = findViewById(R.id.ystCardView);
        ystcmCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FacultyFoodActivity.this, FoodYSTCMActivity.class);
                ArrayList<Food> ystcmFoodList = foodList.getByCategory(FoodCategory.ALL); // change category to YSTCM once ready
                intent.putExtra("add", ystcmFoodList);
                startActivity(intent);
            }
        });


    }



}


