package Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sameedshah.adfapp.R;

import java.util.ArrayList;

import Model.Brands;
import Model.TargetModel;

public class FYTD_Fragment extends Fragment {


    EditText edt_target,edt_done,edt_lessValue,edt_vsTarget,edt_lastYear,edt_trend,aramis_target;
    ImageView add_brand;
    FirebaseDatabase mDatabase;
    DatabaseReference mRef;
    Spinner locationSpinner;
    ArrayList<String> mList;
    TargetModel tm;

    DatabaseReference brandRef;

    RecyclerView brandsRecycler;
    RecyclerView.LayoutManager layoutManager;
    private ArrayList<Brands> brandsList = new ArrayList<>();


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fytd_fragment, container, false);


        mDatabase = FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference("Targets").child("Quarterly");

        FirebaseDatabase mdatabase = FirebaseDatabase.getInstance();
        brandRef = mdatabase.getReference("Brands").child("ADF");

        edt_target = view.findViewById(R.id.edt_target);
        edt_done = view.findViewById(R.id.edt_done);
        edt_lessValue = view.findViewById(R.id.edt_lessValue);
        edt_vsTarget = view.findViewById(R.id.edt_vsTarget);
        edt_lastYear = view.findViewById(R.id.edt_lastYear);
        edt_trend = view.findViewById(R.id.edt_trend);
        add_brand = view.findViewById(R.id.add_brand);
        locationSpinner = view.findViewById(R.id.locationSpinner);




        add_brand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });



        loadData();

        return view;
    }

    private void loadData() {

        mList = new ArrayList<>();
        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){


                    for(DataSnapshot ds : dataSnapshot.getChildren()){
                        tm = ds.getValue(TargetModel.class);
                        mList.add(tm.getLocation());



                    }

                    ArrayAdapter adapter = new ArrayAdapter(getContext(),android.R.layout.simple_spinner_dropdown_item,mList);
                    locationSpinner.setAdapter(adapter);
                    locationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                            dataSaved(mList.get(position));

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });


                }else{
                    Toast.makeText(getContext(),"No Data Found!", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void dataSaved(String location){
        mRef.orderByChild("location").equalTo(location).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    TargetModel tm = ds.getValue(TargetModel.class);
                    edt_done.setText(tm.getDone());
                    edt_target.setText(tm.getTarget());
                    edt_lessValue.setText(tm.getLess_value());
                    edt_trend.setText(tm.getTrend());
                    edt_lastYear.setText(tm.getLast_year());
                    edt_vsTarget.setText(tm.getVs_target());

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}