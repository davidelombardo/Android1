package com.example.david.myapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class FoodListAdapter extends RecyclerView.Adapter {

    private LayoutInflater mInFlater;
    private ArrayList<Food> data;


    public FoodListAdapter(Context context, ArrayList<Food> data){
    this.data=data;
    mInFlater=LayoutInflater.from(context);
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = mInFlater.inflate(R.layout.row_item, viewGroup, false);
        return new FoodViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        FoodViewHolder foodViewHolder=(FoodViewHolder)viewHolder;
        Food currentFood= data.get(i);
        foodViewHolder.productName.setText(currentFood.getProductName());
        foodViewHolder.productPrice.setText(String.valueOf(currentFood.getProductPrice()));

    }

    @Override
    public int getItemCount() {

        return data.size();
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
        // TODO
        }
    }

}
