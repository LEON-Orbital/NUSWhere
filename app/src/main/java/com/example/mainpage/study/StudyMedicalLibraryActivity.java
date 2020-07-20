package com.example.mainpage.study;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mainpage.R;
import com.example.mainpage.bus.BusActivity;
import com.example.mainpage.map.GoogleMaps;
import com.squareup.picasso.Picasso;

public class StudyMedicalLibraryActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_medical_library);

        ImageButton studyActivity = findViewById(R.id.studyBtn);
        studyActivity.setImageResource(R.drawable.study_button);

        Library ml = getIntent().getParcelableExtra("add");

        ImageView mainImage = findViewById(R.id.imageML);
        Picasso.get().load(ml.getMainimage()).into(mainImage);

        TextView libraryAddressText = findViewById(R.id.libraryAddressText);
        TextView libraryBusStopText = findViewById(R.id.libraryBusStopText);
        TextView libraryOpHoursText = findViewById(R.id.libraryOpHoursText);

        libraryAddressText.setText(ml.getAddress());
        libraryBusStopText.setText(ml.getNearbyBusStops());
        libraryOpHoursText.setText(ml.getOpHours());

        GridView libraryMorePhotosGrid = findViewById(R.id.libraryMorePhotosGrid);
        LibraryPhotosAdapter adapter = new LibraryPhotosAdapter(StudyMedicalLibraryActivity.this, ml.getImages());
        libraryMorePhotosGrid.setAdapter(adapter);


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
        switch (v.getId()) {
            ////////////// Navigation bar //////////////
            case R.id.backBtn:
                finish();
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