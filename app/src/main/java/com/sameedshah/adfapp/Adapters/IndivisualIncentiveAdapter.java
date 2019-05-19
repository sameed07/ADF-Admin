package com.sameedshah.adfapp.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sameedshah.adfapp.Model.IndivisualIncentive;
import com.sameedshah.adfapp.R;

import java.util.ArrayList;

public class IndivisualIncentiveAdapter extends RecyclerView.Adapter<IndivisualIncentiveAdapter.ViewHolder> {

    ArrayList<IndivisualIncentive> mList;
    Context context;

    public IndivisualIncentiveAdapter(Context context, ArrayList<IndivisualIncentive> mList) {

        this.context = context;
        this.mList = mList;
      System.err.println("data comming ");

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.indivisual_incentive_adapter, viewGroup, false);
        System.err.println("viewcreated ");

        return new IndivisualIncentiveAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        System.err.println("viewholder ");
        IndivisualIncentive brand = mList.get(i);


        holder.indiviIncentiveNo.setText(brand.getIncentiveno());
        holder.indiviIncentiveQuantity.setText(brand.getQuantity());
        holder.indiviIncentiveValue.setText(brand.getValue());


    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {


        private TextView indiviIncentiveNo;

        private TextView indiviIncentiveQuantity;

        private TextView indiviIncentiveValue;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            indiviIncentiveNo = itemView.findViewById(R.id.indivi_incentive_no);

            indiviIncentiveQuantity = itemView.findViewById(R.id.indivi_incentive_quantity);

            indiviIncentiveValue = itemView.findViewById(R.id.indivi_incentive_value);


        }
    }
}
