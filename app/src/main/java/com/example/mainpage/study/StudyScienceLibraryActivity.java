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
import com.example.mainpage.map.MapActivity;
import com.squareup.picasso.Picasso;

public class StudyScienceLibraryActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_science_library);

        Library sciL = getIntent().getParcelableExtra("add");

        ImageView mainImage = findViewById(R.id.imageSL);
        Picasso.get().load(sciL.getMainimage()).into(mainImage);

        TextView libraryAddressText = findViewById(R.id.libraryAddressText);
        TextView libraryBusStopText = findViewById(R.id.libraryBusStopText);
        TextView libraryOpHoursText = findViewById(R.id.libraryOpHoursText);

        libraryAddressText.setText(sciL.getAddress());
        libraryBusStopText.setText(sciL.getNearbyBusStops());
        libraryOpHoursText.setText(sciL.getOpHours());

        GridView libraryMorePhotosGrid = findViewById(R.id.libraryMorePhotosGrid);
        LibraryPhotosAdapter adapter = new LibraryPhotosAdapter(StudyScienceLibraryActivity.this, sciL.getImages());
        libraryMorePhotosGrid.setAdapter(adapter);


        ImageButton backActivity = findViewById(R.id.backBtn);
        ImageButton studyActivity = findViewById(R.id.studyBtn);
        ImageButton busActivity = findViewById(R.id.busBtn);
        ImageButton mapActivity = findViewById(R.id.mapBtn);


        backActivity.setOnClickListener(this);
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
                startActivity(new Intent(getApplicationContext(), MapActivity.class));
                break;
        }
    }
}