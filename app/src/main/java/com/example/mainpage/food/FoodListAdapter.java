package com.example.mainpage.food;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.res.ResourcesCompat;
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
        boolean favourite = foodList.get(position).getFavStatus();

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

        if (favourite) {
            holder.favBtn.setBackgroundResource(R.drawable.ic_favorite_red_24dp);
            holder.favBtn2.setBackgroundResource(R.drawable.ic_favorite_red_24dp);
        } else {
            holder.favBtn.setBackgroundResource(R.drawable.ic_favorite_shadow_24dp);
            holder.favBtn2.setBackgroundResource(R.drawable.ic_favorite_shadow_24dp);
        }

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

    class FoodViewHolder extends RecyclerView.ViewHolder {
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
        Button favBtn;
        Button favBtn2;

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

            this.favBtn = v.findViewById(R.id.favBtn);
            this.favBtn2 = v.findViewById(R.id.favBtn2);

            favBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    Food food = foodList.get(position);

                    if (!food.getFavStatus()) {
                        food.setFavStatus(true);
                        notifyItemChanged(position);
                        Toast toast = Toast.makeText(context, "Added to Favourites", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 675);
                        toast.show();

                    } else {
                        if (context instanceof FoodFavouritesActivity) {

                            TextView alertText = new TextView(context);
                            alertText.setText("Remove from favourites?");
                            alertText.setPadding(60,65,60,18);
                            alertText.setTextSize(19);
                            alertText.setTypeface(ResourcesCompat.getFont(context, R.font.lato_regular));

                            AlertDialog.Builder builder = new AlertDialog.Builder(context)
                                    .setView(alertText);

                            builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    food.setFavStatus(false);
                                    foodList.remove(food);
                                    notifyDataSetChanged();
                                    dialog.dismiss();
                                    Toast toast = Toast.makeText(context, "Removed from Favourites", Toast.LENGTH_SHORT);
                                    toast.setGravity(Gravity.CENTER_VERTICAL, 0, 675);
                                    toast.show();
                                }
                            });

                            builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // Do nothing
                                    dialog.dismiss();
                                }
                            });

                            AlertDialog alert = builder.create();
                            alert.show();

                        } else {
                            food.setFavStatus(false);
                            notifyItemChanged(position);
                            Toast toast = Toast.makeText(context, "Removed from Favourites", Toast.LENGTH_SHORT);
                            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 675);
                            toast.show();
                        }
                    }
                }
            });

            favBtn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    Food food = foodList.get(position);

                    if (!food.getFavStatus()) {
                        food.setFavStatus(true);
                        notifyItemChanged(position);
                        Toast toast = Toast.makeText(context, "Added to Favourites", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 675);
                        toast.show();

                    } else {
                        if (context instanceof FoodFavouritesActivity) {

                            TextView alertText = new TextView(context);
                            alertText.setText("Remove from favourites?");
                            alertText.setPadding(60,65,60,18);
                            alertText.setTextSize(19);
                            alertText.setTypeface(ResourcesCompat.getFont(context, R.font.lato_regular));

                            AlertDialog.Builder builder = new AlertDialog.Builder(context)
                                    .setView(alertText);

                            builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    food.setFavStatus(false);
                                    foodList.remove(food);
                                    notifyDataSetChanged();
                                    dialog.dismiss();
                                    Toast toast = Toast.makeText(context, "Removed from Favourites", Toast.LENGTH_SHORT);
                                    toast.setGravity(Gravity.CENTER_VERTICAL, 0, 675);
                                    toast.show();
                                }
                            });

                            builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // Do nothing
                                    dialog.dismiss();
                                }
                            });

                            AlertDialog alert = builder.create();
                            alert.show();

                        } else {
                            food.setFavStatus(false);
                            notifyItemChanged(position);
                            Toast toast = Toast.makeText(context, "Removed from Favourites", Toast.LENGTH_SHORT);
                            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 675);
                            toast.show();
                        }
                    }
                }
            });

            foodShowLessCard.setOnClickListener(v2 -> {
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
