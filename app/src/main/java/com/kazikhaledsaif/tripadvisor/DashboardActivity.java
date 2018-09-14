package com.kazikhaledsaif.tripadvisor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class DashboardActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    ViewFlipper viewFlipper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        mAuth = FirebaseAuth.getInstance();

        int images[] ={ R.drawable.bich, R.drawable.hotel, R.drawable.hilli, R.drawable.del, R.drawable.wallhaven};
        viewFlipper =findViewById(R.id.backgroundimgViewer);

        for (int image: images){
            imgSlider(image);
        }



    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser==null){
            Intent intent = new Intent(DashboardActivity.this, LogInActivity.class);
                finish();
                startActivity(intent);
        }

    }
    public void signOut(View view) {
        mAuth.signOut();
        finish();
        Intent intent = new Intent(DashboardActivity.this, LogInActivity.class);
        finish();
        startActivity(intent);
    }


    public void imgSlider(int image){


        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(image);

        viewFlipper.addView(imageView);
        viewFlipper.setFlipInterval(4000);

        viewFlipper.setAutoStart(true);
        viewFlipper.setInAnimation(this,android.R.anim.slide_in_left);
        viewFlipper.setInAnimation(this,android.R.anim.slide_out_right);

    }





}
