package com.example.mainpage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class BusResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_result);

        // get the text input by user
        String startResultText = getIntent().getExtras().getString("first");
        String endResultText = getIntent().getExtras().getString("second");

        // set the top bar text
        TextView startResult = findViewById(R.id.startResult);
        TextView endResult = findViewById(R.id.endResult);

        startResult.setText(startResultText);
        endResult.setText(endResultText);
    }
}