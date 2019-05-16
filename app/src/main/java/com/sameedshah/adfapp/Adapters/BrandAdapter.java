package com.sameedshah.adfapp.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sameedshah.adfapp.R;

import java.util.ArrayList;
import java.util.Random;

import com.sameedshah.adfapp.Model.Brands;

public class BrandAdapter extends RecyclerView.Adapter<BrandAdapter.ViewHolder> {

    ArrayList<Brands> mList;
    Context context;
    String[] colorArray = {"#3f51b5", "#007ee5", "#ff3300", "#0097A7", "#34495e", "#3498db", "#8e44ad",
            "#699f55", "#2979FF", "#E91E63", "#f44336", "#6D4C41", "#78909C", "#009688", "#1A237E", "#DD2C00", "#d50000", "#1A237E"};

    public BrandAdapter(Context context, ArrayList<Brands> mList){

        this.context  = context;
        this.mList = mList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.brand_adapter,viewGroup,false);

        return new BrandAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {

        Brands brand = mList.get(i);
        holder.txtBrandName.setText(brand.getBrand_name());
        holder.txtTarget.setText(brand.getTarget());
        holder.txtMtd.setText(brand.getMtd());
        holder.txtTrend.setText(brand.getTrend());
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

        TextView txtBrandName,txtTarget,txtMtd,txtTrend,txtLessvalue;
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
