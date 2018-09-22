package com.kazikhaledsaif.tripadvisor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class AddProfileActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_add);

    }

    public void Skip(View view) {
        Intent intent = new Intent(AddProfileActivity.this, DashboardActivity.class);
        finish();
        startActivity(intent);
    }
}
