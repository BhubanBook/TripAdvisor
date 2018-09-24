package com.kazikhaledsaif.tripadvisor.POJO;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

public class OfflineFirebaseDatabase extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);

    }
}
