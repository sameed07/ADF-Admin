package Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.sameedshah.adfapp.R;

public class FYTD_Fragment extends Fragment {

    EditText edt_target,edt_done,edt_lessValue,edt_vsTarget,edt_lastYear,edt_trend,aramis_target;
    EditText aramis_trend,aramis_mtd,lessValue;
    EditText dkny_target,dkny_trend,dkny_mtd,dkny_lessValue;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fytd_fragment, container, false);

        edt_target = view.findViewById(R.id.edt_target);
        edt_done = view.findViewById(R.id.edt_done);
        edt_lessValue = view.findViewById(R.id.edt_lessValue);
        edt_vsTarget = view.findViewById(R.id.edt_vsTarget);
        edt_lastYear = view.findViewById(R.id.edt_lastYear);
        edt_trend = view.findViewById(R.id.edt_trend);

        aramis_target = view.findViewById(R.id.aramis_target);
        aramis_trend = view.findViewById(R.id.aramis_trend);
        aramis_mtd = view.findViewById(R.id.aramis_mtd);
        lessValue = view.findViewById(R.id.aramis_target);

        dkny_target = view.findViewById(R.id.dkny_target);
        dkny_trend = view.findViewById(R.id.dkny_trend);
        dkny_mtd = view.findViewById(R.id.dkny_mtd);
        dkny_lessValue = view.findViewById(R.id.dkny_lessValue);



        return view;
    }
}
