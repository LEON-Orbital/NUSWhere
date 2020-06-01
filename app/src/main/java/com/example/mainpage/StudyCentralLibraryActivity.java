package com.example.mainpage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class StudyCentralLibraryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_central_library);

        Library clb = getIntent().getParcelableExtra("add");

        ImageView mainImage = findViewById(R.id.imageCLB);
        Picasso.get().load(clb.getMainimage()).into(mainImage);

        TextView libraryAddressText = findViewById(R.id.libraryAddressText);
        TextView libraryBusStopText = findViewById(R.id.libraryBusStopText);
        TextView libraryOpHoursText = findViewById(R.id.libraryOpHoursText);

        libraryAddressText.setText(clb.getAddress());
        libraryBusStopText.setText(clb.getNearbyBusStops());
        libraryOpHoursText.setText(clb.getOpHours());

        GridView libraryMorePhotosGrid = findViewById(R.id.libraryMorePhotosGrid);
        LibraryPhotosAdapter adapter = new LibraryPhotosAdapter(StudyCentralLibraryActivity.this, clb.getImages());
        libraryMorePhotosGrid.setAdapter(adapter);
        libraryMorePhotosGrid.hea

    }
}
