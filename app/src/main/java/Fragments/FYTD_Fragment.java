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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sameedshah.adfapp.R;

public class FYTD_Fragment extends Fragment {


//    EditText aramis_trend,aramis_mtd,lessValue;
//    EditText dkny_target,dkny_trend,dkny_mtd,dkny_lessValue;
//    EditText mk_target, mk_trend, mk_mtd, mk_lessValue;
//    EditText zegna_target, zegna_trend,zegma_mtd,zegna_lessValue;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fytd_fragment, container, false);


//
//        aramis_target = view.findViewById(R.id.aramis_target);
//        aramis_trend = view.findViewById(R.id.aramis_trend);
//        aramis_mtd = view.findViewById(R.id.aramis_mtd);
//        lessValue = view.findViewById(R.id.aramis_target);
//
//        dkny_target = view.findViewById(R.id.dkny_target);
//        dkny_trend = view.findViewById(R.id.dkny_trend);
//        dkny_mtd = view.findViewById(R.id.dkny_mtd);
//        dkny_lessValue = view.findViewById(R.id.dkny_lessValue);
//
//        mk_target = view.findViewById(R.id.mk_target);
//        mk_trend = view.findViewById(R.id.mk_trend);
//        mk_mtd = view.findViewById(R.id.mk_mtd);
//        mk_lessValue = view.findViewById(R.id.mk_lessValue) ;
        return view;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }


}
