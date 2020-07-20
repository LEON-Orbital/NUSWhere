package com.example.mainpage.study;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mainpage.R;
import com.example.mainpage.bus.BusActivity;
import com.example.mainpage.food.FoodActivity;
import com.example.mainpage.map.GoogleMaps;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class StudyBusinessActivity extends AppCompatActivity implements View.OnClickListener {

    StudyList studyList = new StudyList();
    RecyclerView rcView;
    StudySpotColumnAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_business);

        ImageButton studyActivity = findViewById(R.id.studyBtn);
        studyActivity.setImageResource(R.drawable.study_button);

        StudyFaculty studySpot = studyList.getFaculty(StudyNUSFaculties.BIZ);

        //String mainImage = studySpot.getImage();
        //String name = studySpot.getName();
        ArrayList<StudySpot> studySpots = studySpot.getStudyAreas();

        ImageView imageStudy = findViewById(R.id.imageStudy);
        Picasso.get().load(studySpot.getImage()).into(imageStudy);

        TextView textStudy = findViewById(R.id.textStudy);
        textStudy.setText(studySpot.getName());

        rcView = findViewById(R.id.studySpotRcView);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rcView.setLayoutManager(layoutManager);
        rcView.setHasFixedSize(true);
        rcView.setNestedScrollingEnabled(false);

        adapter = new StudySpotColumnAdapter(this, studySpots);
        rcView.setAdapter(adapter);

        ImageButton backActivity = findViewById(R.id.backBtn);
        ImageButton foodActivity = findViewById(R.id.foodBtn);
        ImageButton busActivity = findViewById(R.id.busBtn);
        ImageButton mapActivity = findViewById(R.id.mapBtn);

        backActivity.setOnClickListener(this);
        foodActivity.setOnClickListener(this);
        studyActivity.setOnClickListener(this);
        busActivity.setOnClickListener(this);
        mapActivity.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
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
        }
    }
}