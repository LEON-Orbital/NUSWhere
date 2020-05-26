package com.example.mainpage;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;

public class FoodComActivity extends AppCompatActivity {

    RecyclerView rcView;
    FoodListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_com);

        rcView = findViewById(R.id.comFoodRecView);
        rcView.setHasFixedSize(true);
        rcView.setNestedScrollingEnabled(false);
        rcView.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<Food> comFoodList = getIntent().getParcelableArrayListExtra("add");
        adapter = new FoodListAdapter(FoodComActivity.this, comFoodList);
        rcView.setAdapter(adapter);

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
    }

}