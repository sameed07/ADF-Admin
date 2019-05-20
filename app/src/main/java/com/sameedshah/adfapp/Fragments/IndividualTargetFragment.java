package com.sameedshah.adfapp.Fragments;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
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
import com.sameedshah.adfapp.Model.Indivisualtarget;
import com.sameedshah.adfapp.Model.User_Model_Invi;
import com.sameedshah.adfapp.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

public class IndividualTargetFragment extends Fragment {
    private ImageView addBrand;
    private RecyclerView brandsRecycler;

    private DatabaseReference brandRef;

    ArrayList<User_Model_Invi> user_model_invis2 = new ArrayList<>();

    ArrayList<Indivisualtarget> IndivisualIncentiveList = new ArrayList<>();
    private ArrayList<String> listLocation, listUser;
    private ArrayList<String> mList;
    private DatabaseReference mRef;
    Spinner userspi;

    private Spinner spinnerUserIndTarget;
    private Spinner spinnerLocationIndTarget;

    EditText edt_target, edt_done, edt_lessValue, target_pay_per_day, edt_lessValue_to_achive;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.individual_target_fragment, container, false);

        spinnerUserIndTarget = (Spinner) view.findViewById(R.id.spinner_user_ind_target);
        spinnerLocationIndTarget = (Spinner) view.findViewById(R.id.spinner_location_ind_target);
        addBrand = (ImageView) view.findViewById(R.id.add_brand);
        brandsRecycler = (RecyclerView) view.findViewById(R.id.brandsRecycler);


        edt_target = (EditText) view.findViewById(R.id.edt_target);
        edt_done = (EditText) view.findViewById(R.id.edt_done);
        edt_lessValue = (EditText) view.findViewById(R.id.edt_lessValue);
        edt_lessValue_to_achive = (EditText) view.findViewById(R.id.edt_lessValue_to_achive);
        target_pay_per_day = (EditText) view.findViewById(R.id.target_pay_per_day);
        loadData();


//        brandRef = FirebaseDatabase.getInstance().getReference("invisuialtarget").child(spinnerUserIndTarget.getSelectedItem().toString());

        spinnerUserIndTarget.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                brandRef = FirebaseDatabase.getInstance().getReference("invisuialtarget").child(spinnerUserIndTarget.getSelectedItem().toString());

                brandRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        listLocation = new ArrayList<>();

                        if (dataSnapshot.exists()) {

                            for (DataSnapshot ds : dataSnapshot.getChildren()) {

                                Indivisualtarget brands = ds.getValue(Indivisualtarget.class);

                                listLocation.add(brands.getLocation());
                                if (listLocation.size() == 0) {
                                    Toast.makeText(getContext(), "List is empty", Toast.LENGTH_SHORT).show();
                                } else {
                                    ArrayAdapter adapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_dropdown_item, listLocation);
                                    spinnerLocationIndTarget.setAdapter(adapter);
                                }

                            }
                        } else {
                            IndivisualIncentiveList.clear();

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


        spinnerLocationIndTarget.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                brandRef = FirebaseDatabase.getInstance().getReference("invisuialtarget").child(spinnerUserIndTarget.getSelectedItem().toString()).child(spinnerLocationIndTarget.getSelectedItem().toString());

                brandRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot ds) {




                                Indivisualtarget brands = ds.getValue(Indivisualtarget.class);



                        edt_target.setText(brands.getTarget());
                        edt_done.setText(brands.getDone());
                        edt_lessValue.setText(brands.getVs_target());
                        edt_lessValue_to_achive.setText(brands.getLess_value());
                        target_pay_per_day.setText(brands.getTargetperday());

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

        addBrand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBrandDialog();
            }
        });


      //  brandRef = FirebaseDatabase.getInstance().getReference("IndivisualBrand").child(spinnerUserIndTarget.getSelectedItem().toString());








        return view;
    }

    public void showBrandDialog() {


        final Dialog mdialog = new Dialog(getContext(), android.R.style.Theme_DeviceDefault_NoActionBar_Fullscreen);
        mdialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mdialog.setContentView(R.layout.brands_indivi_dialog);
        mdialog.show();
        final EditText edt_location = mdialog.findViewById(R.id.edtLocation);
        final EditText edt_brand = mdialog.findViewById(R.id.edtBrandName);
        final EditText edt_target = mdialog.findViewById(R.id.edtTarget);
        final EditText edt_looged = mdialog.findViewById(R.id.edt_mtd);
        final EditText edt_trend = mdialog.findViewById(R.id.edt_trend);
        final EditText edt_less = mdialog.findViewById(R.id.edt_lessValue);
        userspi = mdialog.findViewById(R.id.userSpinner2);

       // loadData();
        Button btnSave = mdialog.findViewById(R.id.btnSave);
        ArrayAdapter adapter2 = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_dropdown_item, mList);
        userspi.setAdapter(adapter2);


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!edt_brand.getText().toString().equals("") &&
                        !edt_target.getText().toString().equals("") && !edt_looged.getText().toString().equals("") &&
                        !edt_trend.getText().toString().equals("") && !edt_less.getText().toString().equals("")) {

                    Map<String, Object> map = new HashMap<>();
                    map.put("location", edt_location.getText().toString());
                    map.put("brand_name", edt_brand.getText().toString());
                    map.put("target", edt_target.getText().toString());
                    map.put("logged", edt_looged.getText().toString());
                    map.put("vstarget", edt_looged.getText().toString());
                    map.put("less_value", edt_less.getText().toString());

                    brandRef = FirebaseDatabase.getInstance().getReference("IndivisualBrand");
                    brandRef = brandRef.child(userspi.getSelectedItem().toString()).child(edt_location.getText().toString());


                    brandRef.push().setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if (task.isSuccessful()) {
                                Toast.makeText(getContext(), "Data Added", Toast.LENGTH_SHORT).show();
                                getActivity().finish();
                            } else {
                                Toast.makeText(getContext(), "Err :" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });


                } else {
                    Toast.makeText(getContext(), "All fields must not be empty!", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }


    private void loadData() {

        mList = new ArrayList<>();

        mRef = FirebaseDatabase.getInstance().getReference("Users");
        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {


                    for (DataSnapshot ds : dataSnapshot.getChildren()) {

                        User_Model_Invi user_model_invis3 = ds.getValue(User_Model_Invi.class);
                        mList.add((user_model_invis3.getUsername()));


                    }


                    ArrayAdapter adapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_dropdown_item, mList);
                    spinnerUserIndTarget.setAdapter(adapter);

                } else {
                    Toast.makeText(getContext(), "No Data Found!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


}
