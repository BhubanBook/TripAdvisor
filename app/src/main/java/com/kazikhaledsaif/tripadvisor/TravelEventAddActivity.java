package com.kazikhaledsaif.tripadvisor;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

public class TravelEventAddActivity extends AppCompatActivity {


    EditText mTravelDestinationET,mEstimatedBudgetET,mFromDateET,mToDateET;
    Button mFromDateCalanderBTN,mToDateCalanderBTN,mSaveBTN;
    String travelDestination,estimatedBudget,fromDate,toDate;
    private DatePickerDialog datePickerDialog ;
    private Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_event_add);

        initialization();
        calendarInit();



        mSaveBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                travelDestination =  mTravelDestinationET.getText().toString();
                estimatedBudget = mEstimatedBudgetET.getText().toString();
                fromDate = mFromDateET.getText().toString();
                toDate = mToDateET.getText().toString();


            }
        });


    }





    private void initialization(){


        mTravelDestinationET =findViewById(R.id.travelDestinationET);
        mEstimatedBudgetET =findViewById(R.id.estimatedBudgetET);
        mFromDateET =findViewById(R.id.fromDateET);
        mToDateET =findViewById(R.id.toDateET);
        mFromDateCalanderBTN =findViewById(R.id.fromCalendarBTN);
        mToDateCalanderBTN =findViewById(R.id.toCalendarBTN);
        mSaveBTN =findViewById(R.id.addTraveleventSaveBTN);


    }
    private void calendarInit() {
        mFromDateCalanderBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                calendar = Calendar.getInstance();
                datePickerDialog = new DatePickerDialog(TravelEventAddActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        int current_month= month+1;
                        mFromDateET.setText(dayOfMonth +" / "+current_month+" / "+year);
                    }
                },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });

        mToDateCalanderBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                calendar = Calendar.getInstance();
                datePickerDialog = new DatePickerDialog(TravelEventAddActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        int current_month= month+1;
                        mToDateET.setText(dayOfMonth +" / "+current_month+" / "+year);
                    }
                },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });}
}
