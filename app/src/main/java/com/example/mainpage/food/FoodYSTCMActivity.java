package com.example.mainpage.food;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SearchView;

import com.example.mainpage.MainActivity;
import com.example.mainpage.bus.BusActivity;
import com.example.mainpage.map.GoogleMaps;
import com.example.mainpage.R;
import com.example.mainpage.study.StudyActivity;

import java.util.ArrayList;

public class FoodYSTCMActivity extends AppCompatActivity implements View.OnClickListener {

    RecyclerView rcView;
    FoodListAdapter adapter;

    FoodList foodList = new FoodList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_y_s_t_c_m);

        ImageButton foodActivity = findViewById(R.id.foodBtn);
        foodActivity.setImageResource(R.drawable.food_button);

        rcView = findViewById(R.id.ystcmFoodRecView);
        rcView.setHasFixedSize(true);
        rcView.setNestedScrollingEnabled(false);
        rcView.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<Food> ystFoodList = foodList.getByFaculty("YSTCM");
        adapter = new FoodListAdapter(FoodYSTCMActivity.this, ystFoodList);
        rcView.setAdapter(adapter);

        // Pop up information
        adapter.collapse();
        adapter.notifyDataSetChanged();
        adapter.setOnItemClickListener(position -> {
            if (!adapter.isExpanded()) {
                adapter.expand(position);
                adapter.notifyItemChanged(position);
            } else {
                int oldPos = adapter.getExpandedPosition();
                adapter.collapse();
                adapter.notifyItemChanged(oldPos);
            }
        });

        // Food search
        SearchView foodSearch = findViewById(R.id.foodSearchView);
        foodSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });

        ImageButton favActivity = findViewById(R.id.favPageBtn);
        ImageButton backActivity = findViewById(R.id.backBtn);
        ImageButton homeActivity = findViewById(R.id.homeBtn);
        ImageButton studyActivity = findViewById(R.id.studyBtn);
        ImageButton busActivity = findViewById(R.id.busBtn);
        ImageButton mapActivity = findViewById(R.id.mapBtn);

        favActivity.setOnClickListener(this);
        backActivity.setOnClickListener(this);
        homeActivity.setOnClickListener(this);
        foodActivity.setOnClickListener(this);
        studyActivity.setOnClickListener(this);
        busActivity.setOnClickListener(this);
        mapActivity.setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.favPageBtn:
                startActivity(new Intent(getApplicationContext(), FoodFavouritesActivity.class));
                overridePendingTransition(R.anim.slide_in_top, R.anim.stay_at_position);
                break;
            case R.id.backBtn:
                finish();
                break;
            case R.id.homeBtn:
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
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

