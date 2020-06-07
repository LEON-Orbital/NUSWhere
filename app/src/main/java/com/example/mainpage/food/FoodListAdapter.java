package com.example.mainpage.food;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mainpage.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class FoodListAdapter extends RecyclerView.Adapter<FoodListAdapter.FoodViewHolder> implements Filterable {

    private Context context;
    private ArrayList<Food> foodList;
    private ArrayList<Food> foodListFull;
    private OnItemClickListener mListener;
    private boolean expanded = false;
    private int expandedPosition = -1;

    FoodListAdapter(Context c, ArrayList<Food> f) {
        this.context = c;
        this.foodList = f;
        this.foodListFull = new ArrayList<>(f);
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.food_row, parent, false);
        return new FoodViewHolder(v, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
        String image = foodList.get(position).getImage();
        String title = foodList.get(position).getName();
        String location = foodList.get(position).getLocation();
        String halal =  "Halal Certified: " + foodList.get(position).getHalalCertified();
        String termTime = foodList.get(position).getTermOperatingHours();
        String vacationTime = foodList.get(position).getVacationOperatingHours();

        String opHours;
        String opHours2;
        if (termTime.equals(vacationTime)) {
            opHours = "Term Time & Vacation Operating Hours: " + "\n" +  termTime;
            opHours2 = "";
        } else {
            opHours = "Term Time Operating Hours: " + "\n"
                    + termTime;
            opHours2 = "Vacation Time Operating Hours: " + "\n"
                    + vacationTime;
        }

        Picasso.get().load(image).fit().into(holder.image);
        holder.title.setText(title);
        holder.title2.setText(title);
        holder.location.setText(location);
        holder.location2.setText(location);
        holder.halal.setText(halal);
        holder.operatingHours.setText(opHours);
        holder.operatingHours2.setText(opHours2);

        if (expanded) {
            holder.foodShowMoreCard.setVisibility(View.VISIBLE);
        } else {
            holder.foodShowMoreCard.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    static class FoodViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title;
        TextView location;
        TextView halal;
        TextView operatingHours;
        TextView operatingHours2;
        TextView title2;
        TextView location2;
        CardView foodShowLessCard;
        CardView foodShowMoreCard;

        FoodViewHolder(View v, OnItemClickListener listener) {
            super(v);
            this.image = v.findViewById(R.id.foodImageView);
            this.title = v.findViewById(R.id.foodTitleId);
            this.location = v.findViewById(R.id.foodLocationId);
            this.halal =  v.findViewById(R.id.foodHalalId);
            this.operatingHours = v.findViewById(R.id.foodOperatingHoursId);
            this.operatingHours2 = v.findViewById(R.id.food2OperatingHoursId);
            this.title2 = v.findViewById(R.id.food2TitleId);
            this.location2 = v.findViewById(R.id.food2LocationId);

            this.foodShowLessCard = v.findViewById(R.id.showLessFoodCard);
            this.foodShowMoreCard = v.findViewById(R.id.showMoreFoodCard);

            foodShowLessCard.setOnClickListener(v1 -> {
                if (listener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(position);
                    }
                }
            });
        }
    }

    private Filter foodFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<Food> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(foodListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Food f : foodListFull) {
                    if (f.getName().toLowerCase().contains(filterPattern)
                            || f.getLocation().toLowerCase().contains(filterPattern)
                            || f.getTags().toLowerCase().contains(filterPattern)) {
                        filteredList.add(f);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            foodList.clear();
            foodList.addAll((ArrayList<Food>) results.values);
            notifyDataSetChanged();
        }
    };

    @Override
    public Filter getFilter() {
        return foodFilter;
    }

    public void expand(int position) {
        this.expanded = true;
        this.expandedPosition = position;
    }

    public void collapse() {
        this.expanded = false;
        this.expandedPosition = -1;
    }

    public boolean isExpanded() {
        return this.expanded;
    }

    public int getExpandedPosition() {
        return this.expandedPosition;
    }

}
