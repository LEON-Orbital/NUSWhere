package com.example.mainpage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;

public class FoodStoreActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_store);

        ImageButton backActivity = findViewById(R.id.foodBackBtn);
        ImageButton foodActivity = findViewById(R.id.foodBtn);
        ImageButton studyActivity = findViewById(R.id.studyBtn);
        ImageButton busActivity = findViewById(R.id.busBtn);
        ImageButton mapActivity = findViewById(R.id.mapBtn);

        CardView foodStallCardView = findViewById(R.id.foodStallCardView);
        CardView restaurantCardView = findViewById(R.id.restaurantCardView);
        CardView martCardView = findViewById(R.id.martCardView);


        backActivity.setOnClickListener(this);
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
            case R.id.foodBackBtn:
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
                startActivity(new Intent(getApplicationContext(), MapActivity.class));
                break;

            ////////////// Food cases //////////////
            case R.id.foodStallCardView:
                ArrayList<Food> foodStallList = getIntent().getParcelableArrayListExtra("addStall");
                startActivity(new Intent(FoodStoreActivity.this, FoodStallActivity.class).putExtra("add", foodStallList));
                break;
            case R.id.restaurantCardView:
                ArrayList<Food> restaurantList = getIntent().getParcelableArrayListExtra("addRes");
                startActivity(new Intent(FoodStoreActivity.this, FoodRestaurantActivity.class).putExtra("add", restaurantList));
                break;
            case R.id.martCardView:
                ArrayList<Food> martList = getIntent().getParcelableArrayListExtra("addMart");
                startActivity(new Intent(FoodStoreActivity.this, FoodConvenienceActivity.class).putExtra("add", martList));
                break;
        }
    }
}
