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

public class FoodStoreActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_store);

        ImageButton foodActivity = findViewById(R.id.foodBtn);
        foodActivity.setImageResource(R.drawable.food_button);

        CardView foodStallCardView = findViewById(R.id.foodStallCardView);
        CardView restaurantCardView = findViewById(R.id.restaurantCardView);
        CardView martCardView = findViewById(R.id.martCardView);

        ImageButton backActivity = findViewById(R.id.backBtn);
        ImageButton homeActivity = findViewById(R.id.homeBtn);
        ImageButton studyActivity = findViewById(R.id.studyBtn);
        ImageButton busActivity = findViewById(R.id.busBtn);
        ImageButton mapActivity = findViewById(R.id.mapBtn);

        backActivity.setOnClickListener(this);
        homeActivity.setOnClickListener(this);
        foodActivity.setOnClickListener(this);
        studyActivity.setOnClickListener(this);
        busActivity.setOnClickListener(this);
        mapActivity.setOnClickListener(this);

        foodStallCardView.setOnClickListener(this);
        restaurantCardView.setOnClickListener(this);
        martCardView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            ////////////// Navigation bar //////////////
            case R.id.backBtn:
                finish();
                break;
            case R.id.homeBtn:
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
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

            ////////////// Food cases //////////////
            case R.id.foodStallCardView:
                startActivity(new Intent(FoodStoreActivity.this, FoodStallActivity.class));
                break;
            case R.id.restaurantCardView:
                startActivity(new Intent(FoodStoreActivity.this, FoodRestaurantActivity.class));
                break;
            case R.id.martCardView:
                startActivity(new Intent(FoodStoreActivity.this, FoodConvenienceActivity.class));
                break;
        }
    }
}
