package com.example.mainpage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SearchView;

import java.util.ArrayList;

public class FoodAllActivity extends AppCompatActivity implements View.OnClickListener {

    RecyclerView rcView;
    FoodListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_all);

        rcView = findViewById(R.id.allFoodRecView);
        rcView.setLayoutManager(new LinearLayoutManager(this));
        rcView.setHasFixedSize(true);
        rcView.setNestedScrollingEnabled(false);

        ArrayList<Food> allFoodList = getIntent().getParcelableArrayListExtra("add");
        adapter = new FoodListAdapter(FoodAllActivity.this, allFoodList);
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
/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.food_menu, menu);

        //MenuItem menuItem = menu.findItem(R.id.foodSearchView);
        SearchView searchView = (SearchView) findViewById(R.id.foodSearchView);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
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

        return true;
    }

 */
}
