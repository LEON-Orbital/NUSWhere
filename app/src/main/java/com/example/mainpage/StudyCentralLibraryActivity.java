package com.example.mainpage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class StudyCentralLibraryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_central_library);


        Library clb = getIntent().getParcelableExtra("add");
        String name = clb.getName();
        TextView tvCLB = findViewById(R.id.textViewCLB);
        tvCLB.setText(name);
    }
}
