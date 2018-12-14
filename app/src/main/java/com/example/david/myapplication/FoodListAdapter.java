package com.example.david.myapplication;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class FoodListAdapter extends RecyclerView.Adapter {
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
    public class FoodViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView productName, productPrice, productQty;
        public Button addBtn, removebtn;

        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);
            productName =itemView.findViewById(R.id.item_name);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
