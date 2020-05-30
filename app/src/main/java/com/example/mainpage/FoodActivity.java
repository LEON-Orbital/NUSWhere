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
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class FoodActivity extends AppCompatActivity implements View.OnClickListener {

    FoodList foodList = new FoodList();
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        ImageButton backActivity = findViewById(R.id.foodBackBtn);
        ImageButton studyActivity = findViewById(R.id.studyBtn);
        ImageButton busActivity = findViewById(R.id.busBtn);
        ImageButton mapActivity = findViewById(R.id.mapBtn);

        CardView allCardView = findViewById(R.id.allCardView);
        CardView facCardView = findViewById(R.id.facultyCardView);
        CardView foodCourtCardView = findViewById(R.id.foodCourtCardView);
        CardView foodStoreCardView = findViewById(R.id.foodStoreCardView);
        CardView cafeCardView = findViewById(R.id.cafeCardView);
        CardView supperCardView = findViewById(R.id.supperCardView);


        backActivity.setOnClickListener(this);
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
            case R.id.foodBackBtn:
                finish();
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
            case R.id.allCardView:
                ArrayList<Food> allFoodList = foodList.getAll();  // gets all the food from the static foodList variable initialised in MainActivity
                startActivity(new Intent(FoodActivity.this, FoodAllActivity.class).putExtra("add", allFoodList));
                break;
            case R.id.facultyCardView:
                ArrayList<Food> bizFoodList = foodList.getByFaculty("Business");
                ArrayList<Food> comFoodList = foodList.getByFaculty("Computing");
                ArrayList<Food> engineFoodList = foodList.getByFaculty("Engineering");
                ArrayList<Food> fassFoodList = foodList.getByFaculty("FASS");
                ArrayList<Food> medFoodList = foodList.getByFaculty("Medicine");
                ArrayList<Food> sciFoodList = foodList.getByFaculty("Science");
                ArrayList<Food> sdeFoodList = foodList.getByFaculty("SDE");
                ArrayList<Food> utownFoodList = foodList.getByFaculty("Utown");
                ArrayList<Food> ystFoodList = foodList.getByFaculty("YSTCM");

                Intent intent1 = new Intent(FoodActivity.this, FacultyFoodActivity.class);
                intent1.putExtra("addBiz", bizFoodList);
                intent1.putExtra("addCom", comFoodList);
                intent1.putExtra("addEngine", engineFoodList);
                intent1.putExtra("addFASS", fassFoodList);
                intent1.putExtra("addMed", medFoodList);
                intent1.putExtra("addSci", sciFoodList);
                intent1.putExtra("addSDE", sdeFoodList);
                intent1.putExtra("addUTown", utownFoodList);
                intent1.putExtra("addYST", ystFoodList);
                startActivity(intent1);
                break;
            case R.id.foodCourtCardView:
                ArrayList<Food> foodCourtList = foodList.getByType("Foodcourt");
                startActivity(new Intent(FoodActivity.this, FoodCourtActivity.class).putExtra("add", foodCourtList));
                break;
            case R.id.foodStoreCardView:
                ArrayList<Food> foodStallList = foodList.getByType("Stall");
                ArrayList<Food> restaurantList = foodList.getByType("Restaurant");
                ArrayList<Food> martList = foodList.getByType("ConvenienceStore");

                Intent intent2 = new Intent(FoodActivity.this, FoodStoreActivity.class);
                intent2.putExtra("addStall", foodStallList);
                intent2.putExtra("addRes", restaurantList);
                intent2.putExtra("addMart", martList);
                startActivity(intent2);
                break;
            case R.id.cafeCardView:
                ArrayList<Food> cafeList = foodList.getByType("Cafe");
                startActivity(new Intent(FoodActivity.this, FoodCafeBakeryActivity.class).putExtra("add", cafeList));
                break;
            case R.id.supperCardView:
                ArrayList<Food> supperList = foodList.getLateNight();
                startActivity(new Intent(FoodActivity.this, FoodLateNightActivity.class).putExtra("add", supperList));
                break;
        }
    }
}

