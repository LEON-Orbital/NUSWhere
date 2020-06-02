package com.example.mainpage;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.github.chrisbanes.photoview.PhotoView;

public class MapActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        PhotoView photoView = (PhotoView) findViewById(R.id.photo_view);
        photoView.setImageResource(R.drawable.busmap);

        ImageButton gmapActivity = findViewById(R.id.gMapsButton);
        ImageButton backActivity = findViewById(R.id.mapBackBtn);
        ImageButton foodActivity = findViewById(R.id.foodBtn);
        ImageButton studyActivity = findViewById(R.id.studyBtn);
        ImageButton busActivity = findViewById(R.id.busBtn);

        gmapActivity.setOnClickListener(this);
        backActivity.setOnClickListener(this);
        foodActivity.setOnClickListener(this);
        studyActivity.setOnClickListener(this);
        busActivity.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            //// NAV BAR ////
            case R.id.mapBackBtn:
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

            //// MAP BUTTONS ////
            case R.id.gMapsButton:
                startActivity(new Intent(getApplicationContext(), GoogleMaps.class));
                break;

        }
    }
}
