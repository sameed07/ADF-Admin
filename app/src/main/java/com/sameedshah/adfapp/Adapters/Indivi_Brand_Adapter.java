package com.sameedshah.adfapp.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sameedshah.adfapp.Model.Brands;
import com.sameedshah.adfapp.Model.Individual_Brands;
import com.sameedshah.adfapp.R;

import java.util.ArrayList;
import java.util.Random;

public class Indivi_Brand_Adapter extends RecyclerView.Adapter<Indivi_Brand_Adapter.ViewHolder> {

    ArrayList<Individual_Brands> mList;
    Context context;
    String[] colorArray = {"#3f51b5", "#007ee5", "#ff3300", "#0097A7", "#34495e", "#3498db", "#8e44ad",
            "#699f55", "#2979FF", "#E91E63", "#f44336", "#6D4C41", "#78909C", "#009688", "#1A237E", "#DD2C00", "#d50000", "#1A237E"};

    public Indivi_Brand_Adapter(Context context, ArrayList<Individual_Brands> mList) {

        this.context = context;
        this.mList = mList;
    }

    @NonNull
    @Override
    public Indivi_Brand_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.brand_indiv_adapter, viewGroup, false);

        ViewHolder holder = new ViewHolder(view);
        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull Indivi_Brand_Adapter.ViewHolder holder, int i) {

        Individual_Brands brand = mList.get(i);
        holder.txtBrandName.setText(brand.getBrand_name());
        holder.txtTarget.setText(brand.getTarget());
        holder.txtMtd.setText(brand.getLogged());
        holder.txtTrend.setText(brand.getVstarget());
        holder.txtLessvalue.setText(brand.getLess_value());

        Random random = new Random();
        int index = random.nextInt(colorArray.length);
        holder.colorView.setBackgroundColor(Color.parseColor(colorArray[index]));

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtBrandName, txtTarget, txtMtd, txtTrend, txtLessvalue;
        View colorView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtBrandName = itemView.findViewById(R.id.brand_name);
            txtTarget = itemView.findViewById(R.id.target);
            txtTrend = itemView.findViewById(R.id.trend);
            txtMtd = itemView.findViewById(R.id.mtd);
            txtLessvalue = itemView.findViewById(R.id.less_value);
            colorView = itemView.findViewById(R.id.recColorLine);

        }
    }
}
