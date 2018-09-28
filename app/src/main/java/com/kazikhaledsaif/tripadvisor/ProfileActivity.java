package com.kazikhaledsaif.tripadvisor;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.kazikhaledsaif.tripadvisor.POJO.Event;
import com.kazikhaledsaif.tripadvisor.POJO.UserProfile;

public class ProfileActivity extends AppCompatActivity {

    TextView mName,mEmail,mPhoneNo,mAddress;
    ImageView mProfileImg;
    FirebaseUser user;
    DatabaseReference root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mName = findViewById(R.id.profileFullNameTV);
        mEmail = findViewById(R.id.profileEmailTV);
        mPhoneNo = findViewById(R.id.profilePhoneNOTV);
        mAddress = findViewById(R.id.profileAddressTV);
        mProfileImg = findViewById(R.id.profilePictureIV);
        user= FirebaseAuth.getInstance().getCurrentUser();
        final String userId = user.getUid();
        Query query = FirebaseDatabase.getInstance().getReference("User")
                .orderByChild("userId")
                .equalTo(userId);
        query.keepSynced(true);

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                UserProfile userProfile = dataSnapshot.getValue(UserProfile.class);
                mName.setText(userProfile.getUserFullName());
                mPhoneNo.setText(userProfile.getUserPhone());
                mAddress.setText(userProfile.getUserAddress());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });








    }

    public void profileBackButton(View view) {
        Intent intent = new Intent(ProfileActivity.this,DashboardActivity.class);
        startActivity(intent);
    }
}
