package com.kazikhaledsaif.tripadvisor;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.kazikhaledsaif.tripadvisor.POJO.Event;
import com.kazikhaledsaif.tripadvisor.POJO.Expense;

import java.text.DateFormat;
import java.util.Calendar;

public class TravelExpenseActivity extends AppCompatActivity {

    EditText mExpenseDetailsET, mExpenseAmountET, mExpenseDateET;
    Button mExpenceDateCalanderBTN,mExpenseSaveBTN;
    Spinner mSpinner;
    String expenseDetails,expenseDate,expanseId,userId;;
    Double expenceAmmount;
    private DatePickerDialog datePickerDialog ;
    private Calendar calendar;
    DatabaseReference root;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_expense);

        initialization();
        calendarInit();
        user= FirebaseAuth.getInstance().getCurrentUser();
        root = FirebaseDatabase.getInstance().getReference("Expense");

        mExpenseSaveBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                expenseDetails =  mExpenseDetailsET.getText().toString();
                expenceAmmount = Double.parseDouble(mExpenseAmountET.getText().toString());
                expenseDate = mExpenseDateET.getText().toString();
                expanseId = root.push().getKey();
                userId = user.getUid();
                Expense expense = new Expense(expanseId,userId,expenseDetails,expenceAmmount,expenseDate);
                root.child(expanseId).setValue(expense);

                mExpenseDetailsET.setText("");
                mExpenseAmountET.setText("");
                mExpenseDateET.setText("");

            }
        });




    }




    private void initialization(){
        mSpinner =findViewById(R.id.SpinnerBTN);
        mExpenseDetailsET =findViewById(R.id.expenseDetailsET);
        mExpenseAmountET =findViewById(R.id.expenseAmountET);
        mExpenseDateET =findViewById(R.id.expenseDateET);
        mExpenceDateCalanderBTN =findViewById(R.id.expenseCalendarBTN);
        mExpenseSaveBTN =findViewById(R.id.addExpenseSaveBTN);
    }

    private void calendarInit() {
        mExpenceDateCalanderBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                calendar = Calendar.getInstance();
                datePickerDialog = new DatePickerDialog(TravelExpenseActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        int current_month= month+1;
                        String currentDate = DateFormat.getDateInstance(DateFormat.MEDIUM).format(calendar.getTime());
                        mExpenseDateET.setText(currentDate);
                        //mFromDateET.setText(dayOfMonth +" / "+current_month+" / "+year);
                    }
                },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();

            }
        });

    }

    public void espenceBackButton(View view) {
        Intent intent = new Intent(TravelExpenseActivity.this,DashboardActivity.class);
        finish();
        startActivity(intent);
    }
}
