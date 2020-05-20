package com.example.mainpage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

public class StudyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study);

        ImageButton back = (ImageButton) findViewById(R.id.studyBackBtn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(startIntent);
            }
        });

        //Study area list
        ListView listView = (ListView) findViewById(R.id.studyAreaListView);
        Resources res = getResources();
        String[] studyAreas = res.getStringArray(R.array.studyAreas);

        StudyListAdapter studyListAdapter = new StudyListAdapter(this, studyAreas);
        listView.setAdapter(studyListAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 1) {
                    Intent showBiz = new Intent(getApplicationContext(), BusinessStudyActivity.class);
                    startActivity(showBiz); //switches screen
                }

            }
        });
    }
}
