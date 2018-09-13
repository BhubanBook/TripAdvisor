package com.kazikhaledsaif.tripadvisor;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity {

    private EditText mFullNameET,mUserNameET,mPasswordET,mAddressET,mPhoneNumbET,mEmailET;
    private String TAG = "Firebase";
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mAuth = FirebaseAuth.getInstance();
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

        if(TextUtils.isEmpty(email)){
            mEmailET.setError("Email is Empty");
            return;
        }
        if(TextUtils.isEmpty(password)){

            mPasswordET.setError("Password is Empty");
            return;
        }

        else {
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "createUserWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();

                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                Toast.makeText(SignUpActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();

                            }

                            // ...
                        }
                    });
        }
    }
}
