package com.android.serujilituni.goodfood.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.serujilituni.goodfood.R;
import com.android.serujilituni.goodfood.items.PlateItem;
import com.android.serujilituni.goodfood.items.TemporalPlateItem;
import com.android.serujilituni.goodfood.store.AppCache;

import java.util.List;

public class PlateAdapter extends RecyclerView.Adapter<PlateAdapter.PlateViewHolder> {
    private List<PlateItem> mDataSet;

    public PlateAdapter(List<PlateItem> plates) {
        this.mDataSet = plates;
    }

    @NonNull
    @Override
    public PlateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.restaurant_menu_item, parent, false);
        return new PlateAdapter.PlateViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PlateViewHolder holder, int position) {
        holder.getImageView().setImageDrawable(mDataSet.get(position).getImageResource());
        holder.getTitleTextView().setText(mDataSet.get(position).getPlateName());
        holder.getQuantityTextView().setText(String.valueOf(mDataSet.get(position).getPlateQuantity()));
        holder.getPriceTextView().setText(String.valueOf(mDataSet.get(position).getPlatePrice()));
        initListener(holder, holder.getAddQuantityBtn(), 1);
        initListener(holder, holder.getSubQuantityBtn(), -1);
    }

    private void initListener(PlateViewHolder holder, ImageButton button, int change) {
        button.setOnClickListener(view -> {
            PlateAdapter.this.updateOrder(holder, change);
            PlateAdapter.this.updateUI(holder, change);
        });
    }

    private void updateUI(PlateViewHolder holder, int sign) {
        int quantity = Integer.parseInt(holder.getQuantityTextView().getText().toString());
        if(sign < 0) {
            if(quantity > 0) {
                quantity += sign;
            }
        } else {
            quantity += sign;
        }
        holder.getQuantityTextView().setText(String.valueOf(quantity));
    }

    private void updateOrder(PlateViewHolder holder, int sign) {
        String name = holder.getTitleTextView().getText().toString();
        int plateQuantity = Integer.parseInt(holder.getQuantityTextView().getText().toString()) + sign;
        float price = Float.parseFloat(holder.getPriceTextView().getText().toString());
        if(sign < 0 && plateQuantity != 0) {
            AppCache.getInstance().subPlate(new TemporalPlateItem(name, plateQuantity, price));
        } else if (sign > 0) {
            AppCache.getInstance().addPlate(new TemporalPlateItem(name, plateQuantity, price));
        }
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    class PlateViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView titleTextView;
        private TextView quantityTextView;
        private TextView priceTextView;
        private ImageButton addQuantityBtn;
        private ImageButton subQuantityBtn;

        public PlateViewHolder(View v) {
            super(v);
            imageView = v.findViewById(R.id.plate_img);
            titleTextView = v.findViewById(R.id.plate_title_tv);
            quantityTextView = v.findViewById(R.id.plate_quantity_tv);
            addQuantityBtn = v.findViewById(R.id.plus_btn);
            subQuantityBtn = v.findViewById(R.id.minus_btn);
            priceTextView = v.findViewById(R.id.plate_price);
        }

        public ImageButton getAddQuantityBtn() {
            return addQuantityBtn;
        }

        public ImageButton getSubQuantityBtn() {
            return subQuantityBtn;
        }

        public ImageView getImageView() {
            return imageView;
        }

        public TextView getTitleTextView() {
            return titleTextView;
        }

        public TextView getQuantityTextView() {
            return quantityTextView;
        }

        public TextView getPriceTextView() {
            return priceTextView;
        }
    }
}
