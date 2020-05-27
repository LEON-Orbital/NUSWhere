package com.example.mainpage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Food> foods = new ArrayList<>();
        ArrayList<Library> libraries = new ArrayList<>();

        // initialises classes with static variables
        FoodList foodList = new FoodList();
        LibraryList libraryList = new LibraryList();

        // retrieves the data and stores it inside static variables
        new DatabaseHandler().readFoodData(new FirebaseCallback() {
            @Override
            public void onFoodCallBack(ArrayList<Food> list) {
                foods.addAll(list);
                foodList.addAll(list);
            }

            @Override
            public void onLibraryCallBack(ArrayList<Library> list) {
                libraries.addAll(list);
                libraryList.addAll(list);
            }
        }, MainActivity.this);

        // food button
        ImageButton foodActivity = findViewById(R.id.foodBtn);
        foodActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FoodActivity.class);
                intent.putExtra("add", foods);
                startActivity(intent);
            }
        });

        // study button
        ImageButton studyActivity =  findViewById(R.id.studyBtn);
        studyActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), StudyActivity.class);
                intent.putExtra("add", libraries);
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

    }
}
