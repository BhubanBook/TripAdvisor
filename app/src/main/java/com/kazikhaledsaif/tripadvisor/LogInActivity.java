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

public class LogInActivity extends AppCompatActivity {


    private EditText mPasswordET,mEmailET;
    private String TAG = "Firebase";
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        mAuth = FirebaseAuth.getInstance();
        mPasswordET = findViewById(R.id.loginPasswordET);
        mEmailET = findViewById(R.id.loginEmailET);

    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser!=null){
            Intent intent = new Intent(LogInActivity.this, DashboardActivity.class);
            finish();
            startActivity(intent);
        }

    }

    public void createNewAccount(View view) {


        Intent intent = new Intent(LogInActivity.this,SignUpActivity.class);
        finish();
        startActivity(intent);
    }
    public void logIn(View view) {

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
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "signInWithEmail:success");

                                FirebaseUser user = mAuth.getCurrentUser();
                                Intent intent = new Intent(LogInActivity.this, DashboardActivity.class);
                                finish();
                                startActivity(intent);
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "signInWithEmail:failure", task.getException());
                                Toast.makeText(LogInActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();

                            }

                            // ...
                        }
                    });

        }

    }

    public void SignInWithApp(View view) {
    }
}
