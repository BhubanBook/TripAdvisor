package com.kazikhaledsaif.tripadvisor;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kazikhaledsaif.tripadvisor.POJO.Event;

import java.util.ArrayList;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.MyViewHolder> {

    Context context;
    ArrayList<Event> events = new ArrayList<>();

    public EventAdapter(Context context, ArrayList<Event> events) {
        this.context = context;
        this.events = events;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.event_row,parent,false);


        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Event event = events.get(position);
        holder.eventNameET.setText(event.getEventDesc());
        holder.eventBudgetET.setText(String.format(String.valueOf(event.getEventBudget())));
        holder.eventFormDateET.setText(event.getEventFromDate());
        holder.eventToDateET.setText(event.getEventToDate());
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView eventNameET,eventBudgetET,eventDescET,eventFormDateET,eventToDateET ;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            eventNameET = itemView.findViewById(R.id.eventNameET);

            eventBudgetET = itemView.findViewById(R.id.eventBudgetET);



            eventFormDateET = itemView.findViewById(R.id.eventFormDateET);

            eventToDateET = itemView.findViewById(R.id.eventToDateET);
        }
    }
}
