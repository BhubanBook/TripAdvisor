package com.kazikhaledsaif.tripadvisor;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.kazikhaledsaif.tripadvisor.POJO.Event;
import com.kazikhaledsaif.tripadvisor.POJO.Expense;

import java.util.ArrayList;
import java.util.List;

import io.github.yuweiguocn.lib.squareloading.SquareLoading;

public class ExpenseActivity extends AppCompatActivity {


    DatabaseReference root;
    RecyclerView recyclerView;
    ExpenseAdapter expenseAdapter;
    FirebaseUser user;
    Spinner mSpinner;
    private List<String> list = new ArrayList<String>();
    private ArrayAdapter<String> dataAdapter;
    ArrayList<Expense> expenses = new ArrayList<Expense>() ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense);
        user= FirebaseAuth.getInstance().getCurrentUser();
        String userId = user.getUid();

        Query query = FirebaseDatabase.getInstance().getReference("Events")
                .orderByChild("userId")
                .equalTo(userId);
        query.keepSynced(true);
        dataAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, list);
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner = (Spinner)findViewById(R.id.expenseSpinnerBTN);
        mSpinner.setAdapter(dataAdapter);

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                    Event data = snapshot.getValue(Event.class);
                    list.add(data.getEventDesc());
                }
                dataAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        recyclerView = findViewById(R.id.expenseRV);

        expenseAdapter = new ExpenseAdapter(ExpenseActivity.this,expenses);
        final RecyclerView.LayoutManager manager = new LinearLayoutManager(ExpenseActivity.this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(expenseAdapter);

        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                Query query2 = FirebaseDatabase.getInstance().getReference("Expense")
                        .orderByChild("eventName")
                        .equalTo(mSpinner.getSelectedItem().toString());
                query2.keepSynced(true);

                query2.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        expenses.clear();

                        for(DataSnapshot d : dataSnapshot.getChildren())
                        {
                            Expense expense = d.getValue(Expense.class);
                            expenses.add(expense);
                            expenseAdapter = new ExpenseAdapter(ExpenseActivity.this,expenses);
                            RecyclerView.LayoutManager manager = new LinearLayoutManager(ExpenseActivity.this,LinearLayoutManager.VERTICAL,false);
                            recyclerView.setLayoutManager(manager);
                            recyclerView.setAdapter(expenseAdapter);

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });













        FloatingActionButton fab =  findViewById(R.id.fabEvent);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ExpenseActivity.this, TravelExpenseActivity.class);

                startActivity(intent);

            }
        });
    }
}
