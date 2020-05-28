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

        FoodList foodList = new FoodList();

        ///////////////////////////////////////////////////////////////////

        // back button
        ImageButton back = findViewById(R.id.foodBackBtn);
        back.setOnClickListener(v -> finish());


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

        // all food button
        CardView allCardView = findViewById(R.id.allCardView);
        allCardView.setOnClickListener(v -> {
            ArrayList<Food> allFoodList = foodList.getAll();
            Intent intent = new Intent(FoodActivity.this, FoodAllActivity.class);
            intent.putExtra("add", allFoodList);
            startActivity(intent);
        });

        ///////////////////////////////////////////////////////////////////

        // sort by faculty button
        CardView facCardView = findViewById(R.id.facultyCardView);
        facCardView.setOnClickListener(v -> {
            ArrayList<Food> bizFoodList = foodList.getByFaculty("Business");
            ArrayList<Food> comFoodList = foodList.getByFaculty("Computing");
            ArrayList<Food> engineFoodList = foodList.getByFaculty("Engineering");
            ArrayList<Food> fassFoodList = foodList.getByFaculty("FASS");
            ArrayList<Food> medFoodList = foodList.getByFaculty("Medicine");
            ArrayList<Food> sciFoodList = foodList.getByFaculty("Science");
            ArrayList<Food> sdeFoodList = foodList.getByFaculty("SDE");
            ArrayList<Food> utownFoodList = foodList.getByFaculty("Utown");
            ArrayList<Food> ystFoodList = foodList.getByFaculty("YSTCM");

            Intent intent = new Intent(FoodActivity.this, FacultyFoodActivity.class);
            intent.putExtra("addBiz", bizFoodList);
            intent.putExtra("addCom", comFoodList);
            intent.putExtra("addEngine", engineFoodList);
            intent.putExtra("addFASS", fassFoodList);
            intent.putExtra("addMed", medFoodList);
            intent.putExtra("addSci", sciFoodList);
            intent.putExtra("addSDE", sdeFoodList);
            intent.putExtra("addUTown", utownFoodList);
            intent.putExtra("addYST", ystFoodList);
            startActivity(intent);
        });

        // food court button
        CardView foodCourtCardView = findViewById(R.id.foodCourtCardView);
        foodCourtCardView.setOnClickListener(v -> {
            ArrayList<Food> foodCourtList = foodList.getByType("Foodcourt");
            Intent intent = new Intent(FoodActivity.this, FoodCourtActivity.class);
            intent.putExtra("add", foodCourtList);
            startActivity(intent);
        });

        // food store button
        CardView foodStoreCardView = findViewById(R.id.foodStoreCardView);
        foodStoreCardView.setOnClickListener(v -> {
            ArrayList<Food> foodStallList = foodList.getByType("Stall");
            ArrayList<Food> restaurantList = foodList.getByType("Restaurant");
            ArrayList<Food> martList = foodList.getByType("ConvenienceStore");

            Intent intent = new Intent(FoodActivity.this, FoodStoreActivity.class);
            intent.putExtra("addStall", foodStallList);
            intent.putExtra("addRes", restaurantList);
            intent.putExtra("addMart", martList);
            startActivity(intent);
        });

        // cafe button
        CardView cafeCardView = findViewById(R.id.cafeCardView);
        cafeCardView.setOnClickListener(v -> {
            ArrayList<Food> cafeList = foodList.getByType("Cafe");
            Intent intent = new Intent(FoodActivity.this, FoodCafeBakeryActivity.class);
            intent.putExtra("add", cafeList);
            startActivity(intent);
        });

        // supper button
        CardView supperCardView = findViewById(R.id.supperCardView);
        supperCardView.setOnClickListener(v -> {
            ArrayList<Food> supperList = foodList.getLateNight();
            Intent intent = new Intent(FoodActivity.this, FoodLateNightActivity.class);
            intent.putExtra("add", supperList);
            startActivity(intent);
        });

        
    }

}
