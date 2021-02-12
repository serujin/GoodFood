package com.android.serujilituni.goodfood.adapters;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.android.serujilituni.goodfood.R;
import com.android.serujilituni.goodfood.activities.menu.MenuActivity;
import com.android.serujilituni.goodfood.constants.Constants;
import com.android.serujilituni.goodfood.items.RestaurantItem;
import com.android.serujilituni.goodfood.store.AppCache;
import com.android.serujilituni.goodfood.utils.Utils;

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
            v.setOnClickListener(view -> changeToRestaurantMenu(
                    RestaurantViewHolder.this.getAdapterPosition(),
                    Utils.getStringFromID(R.string.restaurant_confirmation),
                    Utils.getStringFromID(R.string.yes_confirmation),
                    Utils.getStringFromID(R.string.no_confirmation)
            ));
            this.imageView = v.findViewById(R.id.restaurant_logo);
            this.textView = v.findViewById(R.id.restaurant_name);
        }

        public ImageView getImageView() {
            return imageView;
        }

        public TextView getTextView() {
            return textView;
        }

        private void changeToRestaurantMenu(int index, String msg, String positive, String negative) {
            if (AppCache.getInstance().getCurrentOrder().size() != 0 && index != AppCache.getInstance().getCurrentRestaurant()) {
                new AlertDialog.Builder(AppCache.getInstance().getContext()).setMessage(msg).
                        setPositiveButton(positive, (dialogInterface, i) -> {
                            AppCache.getInstance().resetOrder();
                            loadRestaurant(index);
                        })
                        .setNegativeButton(negative, null).create().show();
            } else {
                loadRestaurant(index);
            }
        }

        private void loadRestaurant(int index) {
            AppCache.getInstance().setCurrentRestaurant(index);
            Intent intent = new Intent(AppCache.getInstance().getContext(), MenuActivity.class);
            intent.putExtra(Constants.MENU_EXTRA_INTENT, index);
            AppCache.getInstance().getContext().startActivity(intent);
        }
    }


}
