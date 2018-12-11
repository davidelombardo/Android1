package com.example.david.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class WelcomeActivity extends AppCompatActivity implements OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        TextView welcomeTW = findViewById(R.id.welcome_tv);
        String mail =getIntent().getStringExtra("Welcome");
        welcomeTW.setText(getString(R.string.welcome)+" "+ mail);




    }

    @Override
    public void onClick(View view) {

    }
}