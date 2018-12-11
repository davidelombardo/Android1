package com.example.david.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;


public class RegisterActivity extends AppCompatActivity  implements OnClickListener {

    EditText emailEt;
    EditText passwordEt;
    EditText phoneNumberEt;
    Button registerBtn;
    boolean emailValidate, passwordValidate, phoneValidate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstancesState) {
        super.onCreate(savedInstancesState);
        setContentView(R.layout.activity_register);
        emailEt = findViewById(R.id.email_et);
        passwordEt = findViewById(R.id.password_et);
        phoneNumberEt = findViewById(R.id.phone_number);
        registerBtn = findViewById(R.id.register_btn);
        registerBtn.setOnClickListener(this);
        emailEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                emailValidate=isValidEmail(editable.toString());
                enableButton();

            }
        });
        passwordEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                passwordValidate=isValidPassword(editable.toString());
                enableButton();
            }
        });
        phoneNumberEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
            phoneValidate=isValidPhoneNumber(editable.toString());
            enableButton();
            }
        });


    }
    public boolean isValidEmail(String email){
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
    private boolean isValidPassword(String password){
        return (password.length() > 6);
    }
    private boolean isValidPhoneNumber(String number){
        return (number.length() <= 10);
    }

    private void enableButton(){
        if(emailValidate && passwordValidate  && phoneValidate)
            registerBtn.setEnabled(true);
    }


    @Override
    public void onClick(View view) {
    }
}
