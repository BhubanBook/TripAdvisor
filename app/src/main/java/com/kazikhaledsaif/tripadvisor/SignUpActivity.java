package com.kazikhaledsaif.tripadvisor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SignUpActivity extends AppCompatActivity {

    private EditText mFullNameET,mUserNameET,mPasswordET,mAddressET,mPhoneNumbET,mEmailET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mFullNameET = findViewById(R.id.signUpFullNameET);
        mUserNameET = findViewById(R.id.signUpUserNameET);
        mPasswordET = findViewById(R.id.signUpPasswordET);
        mAddressET = findViewById(R.id.signUpAddressET);
        mPhoneNumbET = findViewById(R.id.signUpPhoneNumET);
        mEmailET = findViewById(R.id.signUpEmailET);

    }

    public void Join(View view) {

        Intent intent = new Intent(SignUpActivity.this, PictureActivity.class);
        startActivity(intent);
        UserRegistration();

    }

    private void UserRegistration() {
        String fullName= mFullNameET.getText().toString();
        String userName= mUserNameET.getText().toString();
        String password= mPasswordET.getText().toString();
        String address= mAddressET.getText().toString();
        String email= mEmailET.getText().toString();
        String phoneNum= mPhoneNumbET.getText().toString();
    }
}
