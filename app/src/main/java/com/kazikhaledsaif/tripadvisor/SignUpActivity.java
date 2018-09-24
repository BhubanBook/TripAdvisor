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

    private EditText mPasswordET,mEmailET;
    private String TAG = "Firebase";
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mAuth = FirebaseAuth.getInstance();
        mPasswordET = findViewById(R.id.signUpPasswordET);
        mEmailET = findViewById(R.id.signUpEmailET);


    }


    public void Join(View view) {

        String password= mPasswordET.getText().toString();

        String email= mEmailET.getText().toString();

        if(TextUtils.isEmpty(email)){
            mEmailET.setError("Email is Empty");
            return;
        }
         if(TextUtils.isEmpty(password)){

            mPasswordET.setError("Password is Empty");
            return;
        }
        if(password.length()<6){

            mPasswordET.setError("Password must be 6 digit");
            return;
        }

        else {
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.e(TAG, "createUserWithEmail:success");

                                FirebaseUser user = mAuth.getCurrentUser();
                                Intent intent = new Intent(SignUpActivity.this, AddProfileActivity.class);
                                finish();
                                startActivity(intent);
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.e(TAG, "createUserWithEmail:failure", task.getException());
                                Toast.makeText(SignUpActivity.this, "Email already Exists",
                                        Toast.LENGTH_LONG).show();

                            }

                            // ...
                        }
                    });
        }


    }

}
