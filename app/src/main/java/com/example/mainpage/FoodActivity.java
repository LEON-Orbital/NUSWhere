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


        // back button
        ImageButton back = (ImageButton) findViewById(R.id.foodBackBtn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(startIntent);
            }
        });


        // study button
        ImageButton studyActivity = (ImageButton) findViewById(R.id.studyBtn);
        studyActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), StudyActivity.class);
                startActivity(intent);
            }
        });

        // bus button
        ImageButton busActivity = (ImageButton) findViewById(R.id.busBtn);
        busActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BusActivity.class);
                startActivity(intent);
            }
        });

        // map button
        ImageButton mapActivity = (ImageButton) findViewById(R.id.mapBtn);
        mapActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MapActivity.class);
                startActivity(intent);
            }
        });

        CardView allCardView = (CardView) findViewById(R.id.allCardView);
        allCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FoodActivity.this, FoodAllActivity.class));
            }
        });

        CardView facCardView = (CardView) findViewById(R.id.facultyCardView);
        facCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FoodActivity.this, FacultyFoodActivity.class));
            }
        });

        CardView foodStoreCardView = (CardView) findViewById(R.id.foodStoreCardView);
        foodStoreCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FoodActivity.this, FoodStoreActivity.class));
            }
        });


    }

}
