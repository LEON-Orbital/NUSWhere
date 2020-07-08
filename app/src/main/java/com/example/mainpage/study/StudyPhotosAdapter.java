package com.example.mainpage.study;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mainpage.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class StudyPhotosAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private ArrayList<String> images;

    public StudyPhotosAdapter(Context c, ArrayList<String> images) {
        context = c;
        this.images = images;
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public Object getItem(int position) {
        return images.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null) {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.study_spot_more_photos_item, null);

        }

        ImageView libraryMorePhotosImage = convertView.findViewById(R.id.studyMorePhotosImage);
        Picasso.get().load(images.get(position)).into(libraryMorePhotosImage);
        return convertView;
    }
}
