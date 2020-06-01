package com.example.mainpage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;

public class FoodSDEActivity extends AppCompatActivity implements View.OnClickListener {

    RecyclerView rcView;
    FoodListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_s_d_e);

        rcView = findViewById(R.id.sdeFoodRecView);
        rcView.setHasFixedSize(true);
        rcView.setNestedScrollingEnabled(false);
        rcView.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<Food> sdeFoodList = getIntent().getParcelableArrayListExtra("add");
        adapter = new FoodListAdapter(FoodSDEActivity.this, sdeFoodList);
        rcView.setAdapter(adapter);

        adapter.collapse();
        adapter.notifyDataSetChanged();
        adapter.setOnItemClickListener(new FoodListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (!adapter.isExpanded()) {
                    adapter.expand(position);
                    adapter.notifyItemChanged(position);
                } else {
                    int oldPos = adapter.getExpandedPosition();
                    adapter.collapse();
                    adapter.notifyItemChanged(oldPos);
                }
            }
        });

        ImageButton backActivity = findViewById(R.id.foodBackBtn);
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
            case R.id.foodBackBtn:
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
