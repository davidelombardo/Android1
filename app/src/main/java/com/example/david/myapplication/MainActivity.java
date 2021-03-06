package com.example.david.myapplication;

import android.content.Context;
import android.content.Intent;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements OnClickListener, OnCheckedChangeListener {
    private static final String TAG = "MainActivity";
    private static final int PASSWORD_LENGTH = 6;
    EditText emailET;
    EditText passwordET;
    Button loginBtn;
    Button registerBtn;
    Switch switchSt;
    SharedPreferences shared;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        emailET = findViewById(R.id.email_et);
        passwordET = findViewById(R.id.password_et);
        loginBtn = findViewById(R.id.login_btn);
        registerBtn = findViewById(R.id.register_btn);
        registerBtn.setOnClickListener(this);
        registerBtn.setVisibility(View.VISIBLE);
        loginBtn.setOnClickListener(this);
        switchSt = findViewById(R.id.switch_id);
        switchSt.setOnCheckedChangeListener(this);
        shared= getPreferences(Context.MODE_PRIVATE);
        editor= shared.edit();
        switchSt.setChecked(getColorValue());
        Log.i(TAG, "activity created");
    }

    private boolean isValidEmail() {
        String email = emailET.getText().toString();
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean isValidPassword() {
        String password = passwordET.getText().toString();
        return (password.length() > PASSWORD_LENGTH);
    }

    private void showErrorMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        Log.e(TAG, message);
    }

    private void showSuccessMessage() {
        Toast.makeText(this, getString(R.string.login_success), Toast.LENGTH_LONG)
                .show();
        Log.i(TAG, getString(R.string.login_success));
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "activity started");

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "activity RESUMED");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "activity PAUSED");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "activity STOPPED");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "activity DESTROYED");

    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.login_btn) {
            if (!isValidEmail()) {
                showErrorMessage(getString(R.string.email_error));
                return;
            }
            if (!isValidPassword()) {
                showErrorMessage(getString(R.string.password_error));
                return;
            }
            showSuccessMessage();
            Intent i = new Intent(this, WelcomeActivity.class);
            String mail = emailET.getText().toString();
            i.putExtra("Welcome", mail);
            startActivity(i);


        } else if (view.getId() == R.id.register_btn) {
            Intent myIntent = new Intent(this, RegisterActivity.class);
            startActivity(myIntent);

        }


    }
    private void setColorValue(boolean value){
        editor.putBoolean("BGcolor", value);
        editor.commit();
    }
    private boolean getColorValue(){
        return shared.getBoolean("BGcolor", false);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
       setColorValue(isChecked);
        if(isChecked){
        switchSt.getRootView().setBackgroundColor(getResources().getColor(R.color.dark));
        }else{
            switchSt.getRootView().setBackgroundColor(getResources().getColor(R.color.white));

        }
    }
}
