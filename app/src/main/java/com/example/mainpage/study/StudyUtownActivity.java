package com.example.mainpage.study;

import androidx.appcompat.app.AlertDialog;
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
import com.example.mainpage.map.MapActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class StudyUtownActivity extends AppCompatActivity implements View.OnClickListener {

    StudyList studyList = new StudyList();
    RecyclerView rcView;
    StudySpotColumnAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_utown);

        StudyFaculty studyFaculty = studyList.getFaculty(StudyNUSFaculties.UTOWN);

        String mainImage = studyFaculty.getImage();
        String name = studyFaculty.getName();
        ArrayList<StudySpot> studySpots = studyFaculty.getStudyAreas();

        ImageView imageStudy = findViewById(R.id.imageStudy);
        Picasso.get().load(mainImage).into(imageStudy);

        TextView textStudy = findViewById(R.id.textStudy);
        textStudy.setText(name);

        // set list and adapter
        rcView = findViewById(R.id.studySpotRcViewUTOWN);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rcView.setLayoutManager(layoutManager);
        rcView.setHasFixedSize(true);
        rcView.setNestedScrollingEnabled(false);

        adapter = new StudySpotColumnAdapter(this, studySpots);
        rcView.setAdapter(adapter);

        ImageButton backActivity = findViewById(R.id.backBtn);
        ImageButton foodActivity = findViewById(R.id.foodBtn);
        ImageButton studyActivity = findViewById(R.id.studyBtn);
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
                startActivity(new Intent(getApplicationContext(), MapActivity.class));
                break;
        }
    }
}