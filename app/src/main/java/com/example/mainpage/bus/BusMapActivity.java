package com.example.mainpage.bus;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mainpage.MainActivity;
import com.example.mainpage.R;
import com.example.mainpage.food.FoodActivity;
import com.example.mainpage.map.GoogleMaps;
import com.example.mainpage.study.StudyActivity;
import com.github.chrisbanes.photoview.PhotoView;

public class BusMapActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_map);

        PhotoView photoView = (PhotoView) findViewById(R.id.photo_view);
        photoView.setImageResource(R.drawable.busmap);

        ImageButton backActivity = findViewById(R.id.backBtn);
        ImageButton foodActivity = findViewById(R.id.foodBtn);
        ImageButton studyActivity = findViewById(R.id.studyBtn);
        ImageButton busActivity = findViewById(R.id.busBtn);
        ImageButton homeActivity = findViewById(R.id.homeBtn);

        backActivity.setOnClickListener(this);
        foodActivity.setOnClickListener(this);
        studyActivity.setOnClickListener(this);
        busActivity.setOnClickListener(this);
        homeActivity.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            //// NAV BAR ////
            case R.id.backBtn:
                finish();
                break;
            case R.id.homeBtn:
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
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

        }
    }
}