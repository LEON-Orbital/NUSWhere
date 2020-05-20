package com.example.mainpage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class StudyListAdapter extends ArrayAdapter<String> {

    Context context;
    String[] studyAreas;
    LayoutInflater inflater;

    public StudyListAdapter(Context c, String[] s) {
        super(c, R.layout.study_area_row, s);
        this.studyAreas = s;
        this.context = c;
        this.inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        TextView studyText;

        if (position == 0 || position == 7) {
            convertView = inflater.inflate(R.layout.study_category_row, parent, false);
            convertView.setOnClickListener(null);
            studyText = (TextView) convertView.findViewById(R.id.studyCategoryText);
        } else {
            convertView = inflater.inflate(R.layout.study_area_row, parent, false);
            studyText = (TextView) convertView.findViewById(R.id.studyAreaText);
        }

        String area = studyAreas[position];
        studyText.setText(area);

        return convertView;
    }
}
