package com.kazikhaledsaif.tripadvisor;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kazikhaledsaif.tripadvisor.POJO.Event;
import com.kazikhaledsaif.tripadvisor.POJO.Expense;

import java.util.ArrayList;

public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.MyViewHolder> {

    Context context;
    ArrayList<Expense> expenses;

    public ExpenseAdapter(Context context, ArrayList<Expense> expenses) {
        this.context = context;
        this.expenses = expenses;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.expense_row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Expense expense = expenses.get(position);
        holder.expenseDetails.setText(expense.getExpanseDetails());
        holder.expenseAmount.setText(String.format(String.valueOf(expense.getExpanseAmount())));
        holder.expenseTime.setText(expense.getExpanseDate());

    }

    @Override
    public int getItemCount() {
        return expenses.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView expenseDetails,expenseTime,expenseAmount ;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            expenseDetails = itemView.findViewById(R.id.expenseDetailsTV);
            expenseTime = itemView.findViewById(R.id.expenseTimeTV);
            expenseAmount = itemView.findViewById(R.id.expenseAmountTV);



        }
    }
}
