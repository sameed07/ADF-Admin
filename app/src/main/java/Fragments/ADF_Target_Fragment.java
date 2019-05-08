package Fragments;

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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sameedshah.adfapp.R;

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
        mRef = mDatabase.getReference("ADFTarget");

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

            }
        });



        return view;
    }

    public void dataSaved(){

    }

}
