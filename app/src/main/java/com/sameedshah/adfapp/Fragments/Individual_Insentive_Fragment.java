package com.sameedshah.adfapp.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sameedshah.adfapp.Adapters.IndivisualIncentiveAdapter;
import com.sameedshah.adfapp.Model.IndivisualIncentive;
import com.sameedshah.adfapp.Model.User_Model_Invi;
import com.sameedshah.adfapp.R;

import java.util.ArrayList;

public class Individual_Insentive_Fragment extends Fragment {


    private DatabaseReference brandRef;


    RecyclerView brandsRecycler;
    RecyclerView.LayoutManager layoutManager;
    private ArrayList<IndivisualIncentive> IndivisualIncentiveList = new ArrayList<>();
    private ArrayList<String> listLocation = new ArrayList<>();
    private ArrayList<String> listUser = new ArrayList<>();
    IndivisualIncentive tm;
    private Spinner locationSpinner, userspinner;
    IndivisualIncentiveAdapter adapter;
    ArrayAdapter adapter2;
    ArrayAdapter adapter1;

    ArrayList<User_Model_Invi> userList = new ArrayList<>();


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.individual_incentive_fragment, container, false);


        userspinner = view.findViewById(R.id.UserSpinner_indiv);

        locationSpinner = view.findViewById(R.id.locationSpinner_indiv);
        brandsRecycler = (RecyclerView) view.findViewById(R.id.rec_indiv_incentivi);
        layoutManager = new LinearLayoutManager(getContext());
        brandsRecycler.setLayoutManager(layoutManager);


        adapter = new IndivisualIncentiveAdapter(getActivity(), IndivisualIncentiveList);

        brandsRecycler.setAdapter(adapter);

        adapter2 = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, listUser);
        userspinner.setAdapter(adapter2);

        adapter1 = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, listLocation);
        locationSpinner.setAdapter(adapter1);

        brandRef = FirebaseDatabase.getInstance().getReference("Users");
        brandRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                System.err.printf("data");


                if (dataSnapshot.exists()) {
                    userList.clear();
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        User_Model_Invi brands = ds.getValue(User_Model_Invi.class);
                        listUser.add(brands.getUsername());

                        System.err.printf("data" + brands.getEmail());

                    }
                }
                adapter2.notifyDataSetChanged();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

//        brandRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//
//                if (dataSnapshot.exists()) {
//                    IndivisualIncentiveList.clear();
//                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
//                        IndivisualIncentive brands = ds.getValue(IndivisualIncentive.class);
//                        IndivisualIncentiveList.add(brands);
//                        listLocation.add(brands.getLocation());
//                        listUser.add(brands.getUser());
//                        System.err.println("dat is " + brands.getLocation());
//
//                    }
//
//                    adapter.notifyDataSetChanged();
//
//                    ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, listLocation);
//                    locationSpinner.setAdapter(adapter);
//                    locationSpinner.setAdapter(adapter);
//
//                    ArrayAdapter adapter2 = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, listUser);
//                    userspinner.setAdapter(adapter2);
//                    userspinner.setAdapter(adapter2);
//
//
//                } else {
//                    IndivisualIncentiveList.clear();
//                    adapter.notifyDataSetChanged();
//                    Toast.makeText(getContext(), "Data does not exists", Toast.LENGTH_SHORT).show();
//                }
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });

        locationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                System.err.println("selected");
                brandRef = FirebaseDatabase.getInstance().getReference("invisuialincentive").child(userspinner.getSelectedItem().toString()).child(locationSpinner.getSelectedItem().toString());

                brandRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        System.err.println("changed again");

                        if (dataSnapshot.exists()) {
                            IndivisualIncentiveList.clear();
                          IndivisualIncentive brands = dataSnapshot.getValue(IndivisualIncentive.class);


                                IndivisualIncentiveList.add(brands);
                                System.err.println("dat is " + brands.getLocation());



                            adapter.notifyDataSetChanged();


                        } else {
                            IndivisualIncentiveList.clear();
                            adapter.notifyDataSetChanged();
                            Toast.makeText(getContext(), "Data does not exists", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        userspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                System.err.println("selected");
                brandRef = FirebaseDatabase.getInstance().getReference("invisuialincentive").child(userspinner.getSelectedItem().toString());
                brandRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        System.err.println("changed again");

                        if (dataSnapshot.exists()) {
                            IndivisualIncentiveList.clear();
                            listLocation.clear();
                            for (DataSnapshot ds : dataSnapshot.getChildren()) {


                                IndivisualIncentive brands = ds.getValue(IndivisualIncentive.class);

                                listLocation.add(brands.getLocation());
//                                if (brands.getUser().equals(userspinner.getSelectedItem().toString())) {
//                                    if (brands.getLocation().equals(locationSpinner.getSelectedItem().toString())) {
//                                        IndivisualIncentiveList.add(brands);
//                                        System.err.println("dat is " + brands.getLocation());
//                                    }
//                                }
                            }

                            adapter1.notifyDataSetChanged();
                            adapter.notifyDataSetChanged();


                        } else {
                            IndivisualIncentiveList.clear();
                            adapter.notifyDataSetChanged();
                            Toast.makeText(getContext(), "Data does not exists", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        return view;
    }


}