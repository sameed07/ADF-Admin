package Fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sameedshah.adfapp.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Adapters.BrandAdapter;
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
        mRef = mDatabase.getReference("Targets").child("FYTD");

        FirebaseDatabase mdatabase = FirebaseDatabase.getInstance();
        brandRef = mdatabase.getReference("Brands").child("FYTD");

        edt_target = view.findViewById(R.id.edt_target);
        edt_done = view.findViewById(R.id.edt_done);
        edt_lessValue = view.findViewById(R.id.edt_lessValue);
        edt_vsTarget = view.findViewById(R.id.edt_vsTarget);
        edt_lastYear = view.findViewById(R.id.edt_lastYear);
        edt_trend = view.findViewById(R.id.edt_trend);
        add_brand = view.findViewById(R.id.add_brand);
        locationSpinner = view.findViewById(R.id.locationSpinner);



        brandsRecycler = view.findViewById(R.id.brandsRecycler);
        layoutManager = new LinearLayoutManager(getContext());
        brandsRecycler.setLayoutManager(layoutManager);
        brandsRecycler.setHasFixedSize(true);

        add_brand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBrandDialog();

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
                            showBrands(mList.get(position));

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


    public void showBrandDialog(){



        final Dialog mdialog = new Dialog(getContext(),android.R.style.Theme_DeviceDefault_NoActionBar_Fullscreen);
        mdialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mdialog.setContentView(R.layout.brands_dialog);
        mdialog.show();
        //   EditText edt_location = mdialog.findViewById(R.id.edtLocation);
        final EditText edt_brand = mdialog.findViewById(R.id.edtBrandName);
        final EditText edt_target = mdialog.findViewById(R.id.edtTarget);
        final EditText edt_mdt = mdialog.findViewById(R.id.edt_mtd);
        final EditText edt_trend = mdialog.findViewById(R.id.edt_trend);
        final EditText edt_less = mdialog.findViewById(R.id.edt_lessValue);
        final Spinner locaionSpin = mdialog.findViewById(R.id.locationSpinner);
        Button btnSave = mdialog.findViewById(R.id.btnSave);
        ArrayAdapter adapter = new ArrayAdapter(getContext(),android.R.layout.simple_spinner_dropdown_item,mList);
        locaionSpin.setAdapter(adapter);
        loadData();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if( !edt_brand.getText().toString().equals("") &&
                        !edt_target.getText().toString().equals("") && !edt_mdt.getText().toString().equals("") &&
                        !edt_trend.getText().toString().equals("") && !edt_less.getText().toString().equals("")) {

                    Map<String,Object> map = new HashMap<>();
                    map.put("location", locaionSpin.getSelectedItem().toString());
                    map.put("brand_name", edt_brand.getText().toString());
                    map.put("target",edt_target.getText().toString());
                    map.put("mdt", edt_mdt.getText().toString());
                    map.put("trend", edt_trend.getText().toString());
                    map.put("less_value",edt_less.getText().toString());

                    brandRef = brandRef.child(locaionSpin.getSelectedItem().toString());

                    brandRef.push().setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if(task.isSuccessful()){
                                Toast.makeText(getContext(), "Data Added", Toast.LENGTH_SHORT).show();
                                getActivity().finish();
                            }else{
                                Toast.makeText(getContext(), "Err :" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });


                }else{
                    Toast.makeText(getContext(), "All fields must not be empty!", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

    public void showBrands(String location){

        final BrandAdapter adapter = new BrandAdapter(getContext(),brandsList);
        brandRef.child(location).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                if(dataSnapshot.exists()){
                    brandsList.clear();
                    for(DataSnapshot ds : dataSnapshot.getChildren()){
                        Brands brands = ds.getValue(Brands.class);
                        brandsList.add(brands);
                    }

                    brandsRecycler.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }else{
                    brandsList.clear();
                    adapter.notifyDataSetChanged();
                    Toast.makeText(getContext(), "Data does not exists", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}