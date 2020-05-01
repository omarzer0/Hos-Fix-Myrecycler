package com.example.myrecycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ItemAdapter extends ListAdapter<Items,ItemAdapter.itemsViewHolder> {
    private  Clickmanager clickmanager;


    protected ItemAdapter( DiffUtil.ItemCallback<Items> diffCallback,Clickmanager clickmanager) {
        super(diffCallback);

        this.clickmanager = clickmanager;

    }


    @NonNull
    @Override
    public itemsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_form, parent, false);
        return new itemsViewHolder(view);
    }

    interface Clickmanager{
        void cl(Items items);
        void del( Items items);
    }



    @Override
    public void onBindViewHolder(@NonNull itemsViewHolder holder, final int position) {
        holder.onBind(getItem(position));
        holder.itemView.findViewById(R.id.del_img).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickmanager.del(getItem(position));
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickmanager.cl(getItem(position));
            }
        });
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
