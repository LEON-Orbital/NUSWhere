package com.example.mainpage.food;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.mainpage.bus.BusActivity;
import com.example.mainpage.map.GoogleMaps;
import com.example.mainpage.R;
import com.example.mainpage.study.StudyActivity;

import java.util.ArrayList;

public class FacultyFoodActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_food);

        ImageButton foodActivity = findViewById(R.id.foodBtn);
        foodActivity.setImageResource(R.drawable.food_button);

        ImageButton backActivity = findViewById(R.id.backBtn);
        ImageButton studyActivity = findViewById(R.id.studyBtn);
        ImageButton busActivity = findViewById(R.id.busBtn);
        ImageButton mapActivity = findViewById(R.id.mapBtn);

        CardView bizCardView = findViewById(R.id.bizCardView);
        CardView comCardView = findViewById(R.id.comCardView);
        CardView engineCardView = findViewById(R.id.engineCardView);
        CardView fassCardView = findViewById(R.id.fassCardView);
        CardView medCardView = findViewById(R.id.medCardView);
        CardView scienceCardView = findViewById(R.id.sciCardView);
        CardView sdeCardView = findViewById(R.id.sdeCardView);
        CardView utownCardView = findViewById(R.id.utownCardView);
        CardView ystcmCardView = findViewById(R.id.ystCardView);


        backActivity.setOnClickListener(this);
        foodActivity.setOnClickListener(this);
        studyActivity.setOnClickListener(this);
        busActivity.setOnClickListener(this);
        mapActivity.setOnClickListener(this);

        bizCardView.setOnClickListener(this);
        comCardView.setOnClickListener(this);
        engineCardView.setOnClickListener(this);
        fassCardView.setOnClickListener(this);
        medCardView.setOnClickListener(this);
        scienceCardView.setOnClickListener(this);
        sdeCardView.setOnClickListener(this);
        utownCardView.setOnClickListener(this);
        ystcmCardView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            ////////////// Navigation bar //////////////
            case R.id.backBtn:
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
                startActivity(new Intent(getApplicationContext(), GoogleMaps.class));
                break;

            ////////////// Faculty Cases //////////////
            case R.id.bizCardView:
                ArrayList<Food> bizFoodList = getIntent().getParcelableArrayListExtra("addBiz");
                startActivity(new Intent(FacultyFoodActivity.this, FoodBizActivity.class).putExtra("add", bizFoodList));
                break;
            case R.id.comCardView:
                ArrayList<Food> comFoodList = getIntent().getParcelableArrayListExtra("addCom");
                startActivity(new Intent(FacultyFoodActivity.this, FoodComActivity.class).putExtra("add", comFoodList));
                break;
            case R.id.engineCardView:
                ArrayList<Food> engineFoodList = getIntent().getParcelableArrayListExtra("addEngine");
                startActivity(new Intent(FacultyFoodActivity.this, FoodEngineActivity.class).putExtra("add", engineFoodList));
                break;
            case R.id.fassCardView:
                ArrayList<Food> fassFoodList = getIntent().getParcelableArrayListExtra("addFASS");
                startActivity(new Intent(FacultyFoodActivity.this, FoodFASSActivity.class).putExtra("add", fassFoodList));
                break;
            case R.id.medCardView:
                ArrayList<Food> medFoodList = getIntent().getParcelableArrayListExtra("addMed");
                startActivity(new Intent(FacultyFoodActivity.this, FoodMedicineActivity.class).putExtra("add", medFoodList));
                break;
            case R.id.sciCardView:
                ArrayList<Food> sciFoodList = getIntent().getParcelableArrayListExtra("addSci");
                startActivity(new Intent(FacultyFoodActivity.this, FoodScienceActivity.class).putExtra("add", sciFoodList));
                break;
            case R.id.sdeCardView:
                ArrayList<Food> sdeFoodList = getIntent().getParcelableArrayListExtra("addSDE");
                startActivity(new Intent(FacultyFoodActivity.this, FoodSDEActivity.class).putExtra("add", sdeFoodList));
                break;
            case R.id.utownCardView:
                ArrayList<Food> utownFoodList = getIntent().getParcelableArrayListExtra("addUTown");
                startActivity(new Intent(FacultyFoodActivity.this, FoodUtownActivity.class).putExtra("add", utownFoodList));
                break;
            case R.id.ystCardView:
                ArrayList<Food> ystFoodList = getIntent().getParcelableArrayListExtra("addYST");
                startActivity(new Intent(FacultyFoodActivity.this, FoodYSTCMActivity.class).putExtra("add", ystFoodList));
                break;
        }
    }
}


