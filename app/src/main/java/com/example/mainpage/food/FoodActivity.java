package com.example.mainpage.food;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.mainpage.MainActivity;
import com.example.mainpage.bus.BusActivity;
import com.example.mainpage.map.GoogleMaps;
import com.example.mainpage.R;
import com.example.mainpage.study.StudyActivity;

import java.util.ArrayList;

public class FoodActivity extends AppCompatActivity implements View.OnClickListener {

    FoodList foodList = new FoodList();
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        ImageButton foodActivity = findViewById(R.id.foodBtn);
        foodActivity.setImageResource(R.drawable.food_button);

        ImageButton homeActivity = findViewById(R.id.homeBtn);
        ImageButton studyActivity = findViewById(R.id.studyBtn);
        ImageButton busActivity = findViewById(R.id.busBtn);
        ImageButton mapActivity = findViewById(R.id.mapBtn);

        CardView allCardView = findViewById(R.id.allCardView);
        CardView facCardView = findViewById(R.id.facultyCardView);
        CardView foodCourtCardView = findViewById(R.id.foodCourtCardView);
        CardView foodStoreCardView = findViewById(R.id.foodStoreCardView);
        CardView cafeCardView = findViewById(R.id.cafeCardView);
        CardView supperCardView = findViewById(R.id.supperCardView);


        homeActivity.setOnClickListener(this);
        studyActivity.setOnClickListener(this);
        busActivity.setOnClickListener(this);
        mapActivity.setOnClickListener(this);

        allCardView.setOnClickListener(this);
        facCardView.setOnClickListener(this);
        foodCourtCardView.setOnClickListener(this);
        foodStoreCardView.setOnClickListener(this);
        cafeCardView.setOnClickListener(this);
        supperCardView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            ////////////// Navigation bar //////////////
            case R.id.homeBtn:
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
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

            ////////////// Food cases //////////////
            case R.id.allCardView:
                startActivity(new Intent(FoodActivity.this, FoodAllActivity.class));
                break;
            case R.id.facultyCardView:
                startActivity(new Intent(FoodActivity.this, FacultyFoodActivity.class));
                break;
            case R.id.foodCourtCardView:
                startActivity(new Intent(FoodActivity.this, FoodCourtActivity.class));
                break;
            case R.id.foodStoreCardView:
                startActivity(new Intent(FoodActivity.this, FoodStoreActivity.class));
                break;
            case R.id.cafeCardView:
                startActivity(new Intent(FoodActivity.this, FoodCafeBakeryActivity.class));
                break;
            case R.id.supperCardView:
                startActivity(new Intent(FoodActivity.this, FoodLateNightActivity.class));
                break;
        }
    }
}

