package Fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sameedshah.adfapp.R;

import java.util.HashMap;
import java.util.Map;

import Model.TargetModel;

public class ADF_Target_Fragment extends Fragment {

    EditText edt_target,edt_done,edt_lessValue,edt_vsTarget,edt_lastYear,edt_trend,aramis_target;
    ImageView add_brand;
    FirebaseDatabase mDatabase;
    DatabaseReference mRef;
    Button btnSave;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.adf_target_fragment, container, false);

        mDatabase = FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference("Targets").child("ADF");

        edt_target = view.findViewById(R.id.edt_target);
        edt_done = view.findViewById(R.id.edt_done);
        edt_lessValue = view.findViewById(R.id.edt_lessValue);
        edt_vsTarget = view.findViewById(R.id.edt_vsTarget);
        edt_lastYear = view.findViewById(R.id.edt_lastYear);
        edt_trend = view.findViewById(R.id.edt_trend);
        add_brand = view.findViewById(R.id.add_brand);

        btnSave = view.findViewById(R.id.onSave);

        add_brand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getActivity(), "asdf", Toast.LENGTH_SHORT).show();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dataSaved();
            }
        });

      //  loadData();
        return view;
    }

    private void loadData() {

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                TargetModel target = dataSnapshot.getValue(TargetModel.class);
                //edt_done.setText(target.getDone());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void dataSaved(){
        if(!edt_target.getText().toString().equals("") && !edt_done.getText().toString().equals("")
                && !edt_vsTarget.getText().toString().equals("") && !edt_lessValue.getText().toString().equals("")
                && !edt_trend.getText().toString().equals("")&& !edt_lastYear.getText().toString().equals("")) {
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle("Uploading...");
        progressDialog.show();


            Map<String, Object> map = new HashMap<>();
            map.put("Target", edt_target.getText().toString());
            map.put("Done", edt_done.getText().toString());
            map.put("LessValueToAchieve", edt_lessValue.getText().toString());
            map.put("VSTarget", edt_vsTarget.getText().toString());
            map.put("LastYear", edt_lastYear.getText().toString());
            map.put("Trend", edt_trend.getText().toString());

            mRef.setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {

                        progressDialog.dismiss();
                        Toast.makeText(getContext(), "Saved!", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(getContext(), "err: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }else{
            Toast.makeText(getContext(), "All fields must not be empty!", Toast.LENGTH_SHORT).show();
        }
    }


}
