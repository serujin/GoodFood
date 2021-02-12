package com.android.serujilituni.goodfood.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.serujilituni.goodfood.R;
import com.android.serujilituni.goodfood.items.TemporalPlateItem;

import java.util.List;

public class TemporalPlateAdapter extends RecyclerView.Adapter<TemporalPlateAdapter.TemporalPlateViewHolder> {
    private List<TemporalPlateItem> mDataSet;

    public TemporalPlateAdapter(List<TemporalPlateItem> selectedPlates) {
        this.mDataSet = selectedPlates;
    }

    @NonNull
    @Override
    public TemporalPlateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.temporal_order_item, parent, false);
        return new TemporalPlateViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TemporalPlateViewHolder holder, int position) {
        holder.getName().setText(mDataSet.get(position).getName());
        holder.getQuantity().setText(String.valueOf(mDataSet.get(position).getQuantity()));
        holder.getPrice().setText(String.valueOf(mDataSet.get(position).getPrice()));
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    class TemporalPlateViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView quantity;
        private TextView price;

        public TemporalPlateViewHolder(View v) {
            super(v);
            this.name = v.findViewById(R.id.plate_name_info);
            this.quantity = v.findViewById(R.id.plate_quantity_info);
            this.price = v.findViewById(R.id.plate_price_info);
        }

        public TextView getName() {
            return name;
        }

        public TextView getQuantity() {
            return quantity;
        }

        public TextView getPrice() {
            return price;
        }
    }
}
