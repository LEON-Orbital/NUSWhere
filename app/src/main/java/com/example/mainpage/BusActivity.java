package com.example.mainpage;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class BusActivity extends AppCompatActivity implements View.OnClickListener {

    EditText startLocation;
    EditText destination;
    ImageButton busResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus);

        startLocation = findViewById(R.id.startLocation);
        destination = findViewById(R.id.destination);
        busResult = findViewById(R.id.busSearch);
        busResult.setEnabled(false);

        startLocation.addTextChangedListener(resultTextWatcher);
        destination.addTextChangedListener(resultTextWatcher);



        // compulsory buttons
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
                startActivity(new Intent(getApplicationContext(), MapActivity.class));
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
