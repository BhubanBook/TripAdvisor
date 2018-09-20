package com.kazikhaledsaif.tripadvisor;

import android.app.Dialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kazikhaledsaif.tripadvisor.POJO.Event;

import java.util.ArrayList;
import java.util.List;

import io.github.yuweiguocn.lib.squareloading.SquareLoading;

public class EventsActivity extends AppCompatActivity {

    Dialog mDialog ;
    DatabaseReference root;
    RecyclerView recyclerView;
    EventAdapter eventAdapter;
    SquareLoading loader;
    ArrayList<Event> events = new ArrayList<Event>() ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        recyclerView = findViewById(R.id.eventsRV);
        loader = findViewById(R.id.loader);

        loader.setVisibility(View.VISIBLE);
        eventAdapter = new EventAdapter(EventsActivity.this,events);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(EventsActivity.this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(eventAdapter);
        root = FirebaseDatabase.getInstance().getReference("Events");


        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                loader.setVisibility(View.GONE);
                events.clear();
                for(DataSnapshot d : dataSnapshot.getChildren())
                {
                    Event event = d.getValue(Event.class);
                    events.add(event);
                    eventAdapter = new EventAdapter(EventsActivity.this,events);
                    RecyclerView.LayoutManager manager = new LinearLayoutManager(EventsActivity.this,LinearLayoutManager.VERTICAL,false);
                    recyclerView.setLayoutManager(manager);
                    recyclerView.setAdapter(eventAdapter);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




        FloatingActionButton fab =  findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EventsActivity.this, EventAddActivity.class);

                startActivity(intent);

            }
        });
    }

}
