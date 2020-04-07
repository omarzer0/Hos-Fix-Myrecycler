package com.example.myrecycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.itemsViewHolder> {
    public ArrayList<Items> itemsArrayList = new ArrayList<>();

    @NonNull
    @Override
    public itemsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_form, parent, false);
        return new itemsViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull itemsViewHolder holder, int position) {
        holder.onBind(itemsArrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return itemsArrayList.size();
    }

    class itemsViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvNumber;

        public itemsViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.name_text);
            tvNumber = itemView.findViewById(R.id.number_text);
        }

        void onBind(Items items) {
            tvName.setText(items.getName());
            tvNumber.setText(items.getNumber());
        }
    }

}


