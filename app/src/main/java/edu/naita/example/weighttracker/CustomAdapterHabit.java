package edu.naita.example.weighttracker;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapterHabit extends RecyclerView.Adapter<CustomAdapterHabit.MyViewHolder> {

    Context context;
    Activity activity;
    ArrayList w_id,date,w_weight;



    CustomAdapterHabit(Activity activity, Context context, ArrayList w_id, ArrayList date,
                       ArrayList w_weight){
        this.activity = activity;
        this.context = context;
        this.w_id = w_id;
        this.date=date;
        this.w_weight = w_weight;



    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row_habit, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {


        holder.w_id_txt.setText(String.valueOf(w_id.get(position)));
        holder.date_txt.setText(String.valueOf(date.get(position)));
        holder.w_weight_txt.setText(String.valueOf(w_weight.get(position)));
        holder.mainLayoutHabit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, updateweight.class);
                intent.putExtra("id", String.valueOf(w_id.get(position)));
                intent.putExtra("date", String.valueOf(date.get(position)));
                intent.putExtra("weight", String.valueOf(w_weight.get(position)));
                activity.startActivityForResult(intent,1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return w_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView w_id_txt, date_txt, w_weight_txt;
        LinearLayout mainLayoutHabit;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            w_id_txt = itemView.findViewById(R.id.w_id_txt);
            date_txt = itemView.findViewById(R.id.date_txt);
            w_weight_txt = itemView.findViewById(R.id.w_weight_txt);
            mainLayoutHabit = itemView.findViewById(R.id.mainLayoutHabit);


        }
    }
}
