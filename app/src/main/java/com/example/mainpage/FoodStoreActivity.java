package com.example.mainpage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;

public class FoodStoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_store);

        ArrayList<Food> foodStallList = getIntent().getParcelableArrayListExtra("addStall");
        ArrayList<Food> restaurantList = getIntent().getParcelableArrayListExtra("addRes");
        ArrayList<Food> martList = getIntent().getParcelableArrayListExtra("addMart");

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

        // FOOD STALL button
        CardView foodStallCardView = findViewById(R.id.foodStallCardView);
        foodStallCardView.setOnClickListener(v -> {
            Intent intent = new Intent(FoodStoreActivity.this, FoodStallActivity.class);
            intent.putExtra("add", foodStallList);
            startActivity(intent);
        });

        // RESTAURANT button
        CardView restaurantCardView = findViewById(R.id.restaurantCardView);
        restaurantCardView.setOnClickListener(v -> {
            Intent intent = new Intent(FoodStoreActivity.this, FoodRestaurantActivity.class);
            intent.putExtra("add", restaurantList);
            startActivity(intent);
        });

        // CONVENIENCE STORE button
        CardView martCardView = findViewById(R.id.martCardView);
        martCardView.setOnClickListener(v -> {
            Intent intent = new Intent(FoodStoreActivity.this, FoodConvenienceActivity.class);
            intent.putExtra("add", martList);
            startActivity(intent);
        });
    }

}
