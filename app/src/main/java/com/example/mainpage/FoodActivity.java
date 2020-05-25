package com.example.mainpage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class FoodActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        final FoodList foodList = new FoodList();
        new DatabaseHandler().readData(new FirebaseCallback() {
            @Override
            public void onCallBack(ArrayList<Food> list) {
                foodList.replace(list);
            }
        }, FoodActivity.this);


        // back button
        ImageButton back = findViewById(R.id.foodBackBtn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(startIntent);
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

        // all food button
        CardView allCardView = findViewById(R.id.allCardView);
        allCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FoodActivity.this, FoodAllActivity.class);
                ArrayList<Food> allFoodList = foodList.getByCategory(FoodCategory.ALL);
                intent.putExtra("add", allFoodList);
                startActivity(intent);
            }
        });

        // sort by faculty button
        CardView facCardView = findViewById(R.id.facultyCardView);
        facCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FoodActivity.this, FacultyFoodActivity.class);
                ArrayList<Food> allFoodList = foodList.getByCategory(FoodCategory.ALL);
                intent.putExtra("add", allFoodList);
                startActivity(intent);
            }
        });

        // food store button
        CardView foodStoreCardView = findViewById(R.id.foodStoreCardView);
        foodStoreCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FoodActivity.this, FoodStoreActivity.class);
                ArrayList<Food> allFoodList = foodList.getByCategory(FoodCategory.ALL);
                intent.putExtra("add", allFoodList);
                startActivity(intent);
            }
        });
        
    }

}
