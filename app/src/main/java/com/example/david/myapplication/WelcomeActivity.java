package com.example.david.myapplication;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import java.util.ArrayList;

public class WelcomeActivity extends AppCompatActivity implements OnClickListener {

    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    FoodListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        TextView welcomeTW = findViewById(R.id.welcome_tv);
        String mail =getIntent().getStringExtra("Welcome");
        welcomeTW.setText(getString(R.string.welcome)+" "+ mail);
        recyclerView= findViewById(R.id.food_rv);
        layoutManager= new LinearLayoutManager(this);
        ArrayList<Food> foodList = new ArrayList<>();
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        adapter = new FoodListAdapter(this, foodList);
    }
   private ArrayList<Food> getProducts() {
       ArrayList<Food> foodList = new ArrayList<>();
       foodList.add(new Food("Cannolo", 2.00f));
       foodList.add(new Food("Arancina", 1.00f));
       foodList.add(new Food("Cassata", 3.00f));
       foodList.add(new Food("Sfingi", 0.50f));
       return foodList;


   }
    @Override
    public void onClick(View view) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setData(Uri.parse("mailto:"));
        String mail =getIntent().getStringExtra("Welcome");
        String[] to = {mail,""};
        intent.putExtra(Intent.EXTRA_EMAIL, to);
        intent.setType("message/rfc822");
        Intent chooser = Intent.createChooser(intent, "Send email");
        startActivity(chooser);
    }
}





