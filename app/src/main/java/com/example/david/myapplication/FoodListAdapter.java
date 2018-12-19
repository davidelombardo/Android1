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
    private OnQuantityChange onQuantityChange;

    public interface OnQuantityChange{
        public void onItemAdd(float productPrice);
        public void onItemRemove(float productPrice);
    }


    public void setOnQuantityChange(OnQuantityChange onQuantityChange) {
        this.onQuantityChange = onQuantityChange;
    }

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
        foodViewHolder.productQty.setText(String.valueOf(currentFood.getProductQty()));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    private void addItem(){

    }

    private void removeItem(){


    }
    public class FoodViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView productName, productPrice, productQty;
        public Button addBtn, removeBtn;

        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);
            productName =itemView.findViewById(R.id.item_name);
            productPrice = itemView.findViewById(R.id.item_price);
            productQty = itemView.findViewById(R.id.item_picker)
                    .findViewById(R.id.quantity_tv);

            addBtn = itemView.findViewById(R.id.item_picker).
                    findViewById(R.id.add_btn);

            removeBtn = itemView.findViewById(R.id.item_picker)
                    .findViewById(R.id.remove_btn);
            addBtn.setOnClickListener(this);
            removeBtn.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
        if(v.getId()== R.id.add_btn){
            Food food = data.get(getAdapterPosition());
            food.increaseProductQty();
            notifyItemChanged(getAdapterPosition());
            onQuantityChange.onItemAdd(food.getProductPrice());
        }else if(v.getId() == R.id.remove_btn){
            Food food = data.get(getAdapterPosition());
            food.decreaseProductQty();
            notifyItemChanged(getAdapterPosition());
            onQuantityChange.onItemRemove(food.getProductPrice()                                                                                                         );
        }
        }
    }
        public FoodListAdapter(Context context){
        mInFlater=LayoutInflater.from(context);
        }
}
