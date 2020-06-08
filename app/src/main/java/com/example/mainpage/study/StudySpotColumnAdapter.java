package com.example.mainpage.study;

import android.view.View;
import android.view.ViewGroup;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mainpage.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class StudySpotColumnAdapter extends RecyclerView.Adapter<StudySpotColumnAdapter.StudySpotViewHolder> {

    private Context context;
    private ArrayList<StudySpot> studySpotList;

    StudySpotColumnAdapter(Context c, ArrayList<StudySpot> s) {
        this.context = c;
        this.studySpotList = s;

    }

    @NonNull
    @Override
    public StudySpotViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.study_spot_column, parent, false);
        return new StudySpotViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull StudySpotViewHolder holder, int position) {
        String image = studySpotList.get(position).getImages().get(0);
        String name = studySpotList.get(position).getName();

        Picasso.get().load(image).fit().into(holder.image);
        holder.name.setText(name);
    }

    @Override
    public int getItemCount() {
        return studySpotList.size();
    }

    static class StudySpotViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name;

        StudySpotViewHolder(View v) {
            super(v);
            this.image = v.findViewById(R.id.imageStudySpot);
            this.name = v.findViewById(R.id.textStudySpot);
        }
    }
}
