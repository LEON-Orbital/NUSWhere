package com.example.mainpage.bus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.mainpage.FirebaseCallback;
import com.example.mainpage.FirebaseCallback2;
import com.example.mainpage.map.GoogleMaps;
import com.example.mainpage.R;
import com.example.mainpage.food.FoodActivity;
import com.example.mainpage.study.StudyActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

public class BusActivity extends AppCompatActivity implements View.OnClickListener {

    AutoCompleteTextView startLocation;
    AutoCompleteTextView destination;
    ImageButton busResult;

    BusVenueList busVenueList = new BusVenueList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus);

        startLocation = findViewById(R.id.startLocation);
        destination = findViewById(R.id.destination);
        busResult = findViewById(R.id.busSearch);
        busResult.setEnabled(false);

        // to make sure the user is unable to click the button until they input something
        startLocation.addTextChangedListener(resultTextWatcher);
        destination.addTextChangedListener(resultTextWatcher);

        // AutoCompleteTextView
        ArrayList<String> locList = busVenueList.getLocations();
        ArrayAdapter<String> adapterStart = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, locList);
        startLocation.setAdapter(adapterStart);
        ArrayAdapter<String> adapterEnd = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, locList);
        destination.setAdapter(adapterEnd);

        // initialise the map of bus services
        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().child("BusStops");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                BusService newBus = new BusService();

                for (DataSnapshot busService : dataSnapshot.getChildren()) {
                    String id = busService.getKey();
                    Log.d("Bus Service: ", id);
                    ArrayList<BusStop> busStopList = new ArrayList<>();

                    for (DataSnapshot busStop : busService.getChildren()) {
                        String name = busStop.getKey();
                        Long busStopNo = (Long) busStop.child("busStopNo").getValue();
                        Long time = (Long) busStop.child("time").getValue();

                        BusStop newBusStop = new BusStop(name, busStopNo, time);
                        busStopList.add(newBusStop);
                    }
                    Collections.sort(busStopList); // sort the bus stops
                    newBus.put(id, busStopList); // add e.g ["A2":["PGP", "TCOMS", "OppHSSML", "OppNUSS"]] into BusServices
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(BusActivity.this, "Could not initialise Bus Service list", Toast.LENGTH_SHORT).show();

            }
        });


        // compulsory buttons
        ImageButton backActivity = findViewById(R.id.backBtn);
        ImageButton foodActivity = findViewById(R.id.foodBtn);
        ImageButton studyActivity = findViewById(R.id.studyBtn);
        ImageButton busActivity = findViewById(R.id.busBtn);
        ImageButton mapActivity = findViewById(R.id.mapBtn);

        Button busMapActivity = findViewById(R.id.busViewMapBtn);

        backActivity.setOnClickListener(this);
        foodActivity.setOnClickListener(this);
        studyActivity.setOnClickListener(this);
        busActivity.setOnClickListener(this);
        mapActivity.setOnClickListener(this);
        busMapActivity.setOnClickListener(this);

        busResult.setOnClickListener(this);
    }


    TextWatcher resultTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String startLocationInput = startLocation.getText().toString().trim();
            String destinationInput = destination.getText().toString().trim();
            if (!startLocationInput.isEmpty() && !destinationInput.isEmpty()) {
                busResult.setEnabled(true);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };


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
            case R.id.busViewMapBtn:
                startActivity(new Intent(getApplicationContext(), BusMapActivity.class));
                break;
            case R.id.busSearch:

                Intent intent = new Intent(getApplicationContext(), BusResultActivity.class);
                String startResultText = startLocation.getText().toString().trim();
                String endResultText = destination.getText().toString().trim();

                intent.putExtra("first", startResultText);
                intent.putExtra("second", endResultText);
                startActivity(intent);
        }
    }
}
