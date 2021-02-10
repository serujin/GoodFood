package com.android.serujilituni.goodfood.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.serujilituni.goodfood.R;
import com.android.serujilituni.goodfood.items.RestaurantItem;

import java.util.List;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder> {
    private List<RestaurantItem> mDataSet;

    public RestaurantAdapter(List<RestaurantItem> restaurants) {
        this.mDataSet = restaurants;
    }

    @NonNull
    @Override
    public RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.restaurant_item, parent, false);
        return new RestaurantViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantViewHolder holder, int position) {
        holder.getImageView().setImageDrawable(mDataSet.get(position).getImageResource());
        holder.getTextView().setText(mDataSet.get(position).getRestaurantName());
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    class RestaurantViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView textView;

        public RestaurantViewHolder(View v) {
            super(v);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("ELEMENT", "Element " + RestaurantViewHolder.this.getAdapterPosition() + " clicked.");
                }
            });
            this.imageView = v.findViewById(R.id.restaurant_logo);
            this.textView = v.findViewById(R.id.restaurant_name);
        }

        public ImageView getImageView() {
            return imageView;
        }

        public TextView getTextView() {
            return textView;
        }
    }
}
