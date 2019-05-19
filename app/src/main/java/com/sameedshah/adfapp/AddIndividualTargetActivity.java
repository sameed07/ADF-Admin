package com.sameedshah.adfapp;

import android.app.Dialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
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
import com.sameedshah.adfapp.Fragments.IndividualTargetFragment;
import com.sameedshah.adfapp.Fragments.Individual_Insentive_Fragment;
import com.sameedshah.adfapp.Model.TargetModel;
import com.sameedshah.adfapp.Model.User_Model_Invi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AddIndividualTargetActivity extends AppCompatActivity {

    private AddIndividualTargetActivity.SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private DatabaseReference mRef;
    ArrayList<String> mList = new ArrayList<>();

    User_Model_Invi tm;
    private Spinner spi_add_indvi_user_dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_individual_target);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new AddIndividualTargetActivity.SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        ImageView back_arrow = findViewById(R.id.backArrow);
        back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddIndividualTargetActivity.this,MainActivity.class));
                finish();
            }
        });

    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static AdminActivity.PlaceholderFragment newInstance(int sectionNumber) {
            AdminActivity.PlaceholderFragment fragment = new AdminActivity.PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_admin_notes, container, false);
//            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
//            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_admin_invi_target, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
        if (id == R.id.add_target) {
//
            addtarget_dialog();
            //Toast.makeText(this, "ADF TARGET Function called", Toast.LENGTH_SHORT).show();
//
            return true;
        }else if( id == R.id.add_incentive){

            addincentive_dialog();
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    private void addincentive_dialog() {


        mRef = FirebaseDatabase.getInstance().getReference("Users");

        final Dialog mdialog = new Dialog(this,android.R.style.Theme_DeviceDefault_NoActionBar_Fullscreen);
        mdialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mdialog.setContentView(R.layout.add_target_indivisual_dialog);
        mdialog.show();


        spi_add_indvi_user_dialog = (Spinner) mdialog.findViewById(R.id.spi_add_indvi_user_dialog);
        mList = new ArrayList<>();
        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){


                    for(DataSnapshot ds : dataSnapshot.getChildren()){
                        tm = ds.getValue(User_Model_Invi.class);
                        mList.add(tm.getUsername());



                    }

                    ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,mList);
                    spi_add_indvi_user_dialog.setAdapter(adapter);


                }else{
                    Toast.makeText(getApplicationContext(),"No Data Found!", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        final EditText spi_add_indvi_location_dialog = mdialog.findViewById(R.id.spi_add_indvi_location_dialog);

        final EditText edtTarget  = mdialog.findViewById(R.id.edtTarget);
        final EditText edtDone =  mdialog.findViewById(R.id.edtDone);
        final EditText edtLessValue = mdialog.findViewById(R.id.edt_lessValue);
        final EditText edtVsTarget = mdialog.findViewById(R.id.edt_vsTarget);

        Button btnUpdate = mdialog.findViewById(R.id.btnUpdate);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!spi_add_indvi_location_dialog.getText().toString().equals("") && !edtTarget.getText().toString().equals("")
                        && !edtDone.getText().toString().equals("") && !edtLessValue.getText().toString().equals("")
                        && !edtVsTarget.getText().toString().equals("")
                        && !spi_add_indvi_location_dialog.getText().toString().equals("")){




                    Map<String, Object> map = new HashMap<>();
                    map.put("location", spi_add_indvi_location_dialog.getText().toString());
                    map.put("user", edtTarget.getText().toString());
                    map.put("incentiveno",edtDone.getText().toString());
                    map.put("quantity",edtLessValue.getText().toString());
                    map.put("value",edtVsTarget.getText().toString());
                    mRef = FirebaseDatabase.getInstance().getReference("invisuialincentive");

                    mRef = mRef.child(spi_add_indvi_user_dialog.getSelectedItem().toString()).child(spi_add_indvi_location_dialog.getText().toString());

                    mRef.setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_SHORT).show();
                                finish();
                            }else{
                                Toast.makeText(getApplicationContext(), "Error : " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }else{
                    Toast.makeText(getApplicationContext(), "All fields Must not be Empty!", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    private void addtarget_dialog() {

        mRef = FirebaseDatabase.getInstance().getReference("Users");

        final Dialog mdialog = new Dialog(this,android.R.style.Theme_DeviceDefault_NoActionBar_Fullscreen);
        mdialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mdialog.setContentView(R.layout.add_target_indivisual_dialog);
        mdialog.show();


        spi_add_indvi_user_dialog = (Spinner) mdialog.findViewById(R.id.spi_add_indvi_user_dialog);
        mList = new ArrayList<>();
        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){


                    for(DataSnapshot ds : dataSnapshot.getChildren()){
                        tm = ds.getValue(User_Model_Invi.class);
                        mList.add(tm.getUsername());



                    }

                    ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,mList);
                    spi_add_indvi_user_dialog.setAdapter(adapter);


                }else{
                    Toast.makeText(getApplicationContext(),"No Data Found!", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        final EditText spi_add_indvi_location_dialog = mdialog.findViewById(R.id.spi_add_indvi_location_dialog);
final EditText edtTargetPerday = mdialog.findViewById(R.id.edtTargetPerday);
        final EditText edtTarget  = mdialog.findViewById(R.id.edtTarget);
        final EditText edtDone =  mdialog.findViewById(R.id.edtDone);
        final EditText edtLessValue = mdialog.findViewById(R.id.edt_lessValue);
        final EditText edtVsTarget = mdialog.findViewById(R.id.edt_vsTarget);

        Button btnUpdate = mdialog.findViewById(R.id.btnUpdate);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!spi_add_indvi_location_dialog.getText().toString().equals("") && !edtTarget.getText().toString().equals("")
                        && !edtDone.getText().toString().equals("") && !edtLessValue.getText().toString().equals("")
                        && !edtVsTarget.getText().toString().equals("")
                        && !spi_add_indvi_location_dialog.getText().toString().equals("")){




                    Map<String, Object> map = new HashMap<>();
                    map.put("location", spi_add_indvi_location_dialog.getText().toString());
                    map.put("target", edtTarget.getText().toString());
                    map.put("done",edtDone.getText().toString());
                    map.put("less_value",edtLessValue.getText().toString());
                    map.put("vs_target",edtVsTarget.getText().toString());
                    map.put("targetperday",edtTargetPerday.getText().toString());
                    mRef = FirebaseDatabase.getInstance().getReference("invisuialtarget");

                    mRef = mRef.child(spi_add_indvi_user_dialog.getSelectedItem().toString()).child(spi_add_indvi_location_dialog.getText().toString());

                    mRef.setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_SHORT).show();
                                finish();
                            }else{
                                Toast.makeText(getApplicationContext(), "Error : " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }else{
                    Toast.makeText(getApplicationContext(), "All fields Must not be Empty!", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            switch (position){
                case 0: {
                    fragment = new IndividualTargetFragment();
                    break;
                }
                case 1: {
                    fragment = new Individual_Insentive_Fragment();
                    break;
                }
                default:{
                    Toast.makeText(AddIndividualTargetActivity.this, "Stop!", Toast.LENGTH_SHORT).show();
                }

            }
            return fragment;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 2;
        }
    }
}
