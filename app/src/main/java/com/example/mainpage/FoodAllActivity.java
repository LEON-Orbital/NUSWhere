package com.example.mainpage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class FoodAllActivity extends AppCompatActivity {

    RecyclerView rcView;
    FoodList foodList;
    FoodListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_all);

        rcView = findViewById(R.id.allFoodRecView);
        rcView.setHasFixedSize(true);
        rcView.setLayoutManager(new LinearLayoutManager(this));

        foodList = new FoodList();
        foodList.readData(new FirebaseCallback() {
            @Override
            public void onCallBack(ArrayList<Food> list) {
                adapter = new FoodListAdapter(FoodAllActivity.this, list);
                rcView.setAdapter(adapter);
            }
        }, FoodAllActivity.this, FoodList.Category.ALL);
    }
}
