package com.example.mainpage.study;

import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mainpage.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class StudySpotColumnAdapter extends RecyclerView.Adapter<StudySpotColumnAdapter.StudySpotViewHolder> {

    private Context context;
    private ArrayList<StudySpot> studySpotList;

    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    private TextView studySpotName, studySpotLocation, studySpotSeats, studySpotBus, studySpotHours;
    private ListView studySpotPhotos;
    private ImageButton studySpotCancel;

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

    class StudySpotViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name;

        StudySpotViewHolder(View v) {
            super(v);
            this.image = v.findViewById(R.id.imageStudySpot);
            this.name = v.findViewById(R.id.textStudySpot);

            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    createNewDialog(position);
                }
            });
        }
    }

    public void createNewDialog(int pos) {
        dialogBuilder = new AlertDialog.Builder(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
        View contactPopUpView = inflater.inflate(R.layout.study_pop_up, null);

        studySpotName = contactPopUpView.findViewById(R.id.studySpotName);
        studySpotLocation = contactPopUpView.findViewById(R.id.studySpotLocation);
        studySpotSeats = contactPopUpView.findViewById(R.id.studySpotSeats);
        studySpotBus = contactPopUpView.findViewById(R.id.studySpotBus);
        studySpotHours = contactPopUpView.findViewById(R.id.studySpotHours);

        studySpotCancel = contactPopUpView.findViewById(R.id.studySpotCancel);

        StudySpot studySpot = studySpotList.get(pos);

        studySpotName.setText(studySpotList.get(pos).getName());
        studySpotLocation.setText("Location: " + studySpot.getLocation());
        studySpotSeats.setText("Seating Capacity: " + studySpot.getSeatingCap());
        studySpotBus.setText("Nearby Bus Stops: " + studySpot.getNearbyBusStops());
        studySpotHours.setText("Opening Hours: " + studySpot.getOpHours());

        studySpotPhotos = contactPopUpView.findViewById(R.id.studySpotPhotosListView);
        ArrayList<String> studySpotImages = studySpot.getImages();
        StudyPhotosAdapter adapter = new StudyPhotosAdapter(context, studySpotImages);
        studySpotPhotos.setAdapter(adapter);

        dialogBuilder.setView(contactPopUpView);
        dialog = dialogBuilder.create();
        dialog.show();

        studySpotCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }
}
