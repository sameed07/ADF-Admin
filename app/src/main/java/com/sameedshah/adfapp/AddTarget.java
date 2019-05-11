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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Fragments.ADF_Target_Fragment;
import Fragments.AdminNotesFrag;
import Fragments.Admin_Schedule_fragment;
import Fragments.FYTD_Fragment;
import Fragments.Quarterly_Fragment;

public class AddTarget extends AppCompatActivity {

    private AddTarget.SectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;
    FirebaseDatabase mDatabase;
    DatabaseReference mRef;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_target);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDatabase = FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference("Targets").child("ADF");
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new AddTarget.SectionsPagerAdapter(getSupportFragmentManager());


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
                startActivity(new Intent(AddTarget.this,MainActivity.class));
                finish();
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_admin_notes, menu);
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
       if (id == R.id.action_adf_target) {
//
           ADF_dialog();
           //Toast.makeText(this, "ADF TARGET Function called", Toast.LENGTH_SHORT).show();
//
           return true;
      }else if( id == R.id.action_quarterly){

           Toast.makeText(this, "Quarterly TARGET Function called", Toast.LENGTH_SHORT).show();
            return true;
        }
       else if(id == R.id.action_fytd){
           Toast.makeText(this, "FYTD TARGET Function called", Toast.LENGTH_SHORT).show();
           return true;
       }

       return super.onOptionsItemSelected(item);
   }

    private void ADF_dialog() {


        final Dialog mdialog = new Dialog(this,android.R.style.Theme_DeviceDefault_NoActionBar_Fullscreen);
        mdialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mdialog.setContentView(R.layout.add_target_dialog);
        mdialog.show();

        final EditText edtLocation = mdialog.findViewById(R.id.edtLocation);
        final EditText edtTarget  = mdialog.findViewById(R.id.edtTarget);
        final EditText edtDone =  mdialog.findViewById(R.id.edtDone);
        final EditText edtLessValue = mdialog.findViewById(R.id.edt_lessValue);
        final EditText edtVsTarget = mdialog.findViewById(R.id.edt_vsTarget);
        final EditText edtLastyear = mdialog.findViewById(R.id.edt_lastYear);
        final EditText edtTrend  = mdialog.findViewById(R.id.edt_trend);
        Button btnUpdate = mdialog.findViewById(R.id.btnUpdate);
        
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!edtLocation.getText().toString().equals("") && !edtTarget.getText().toString().equals("")
                        && !edtDone.getText().toString().equals("") && !edtLessValue.getText().toString().equals("")
                        && !edtVsTarget.getText().toString().equals("") && !edtLastyear.getText().toString().equals("")
                        && !edtTrend.getText().toString().equals("")){

                    Map<String, Object> map = new HashMap<>();
                    map.put("location", edtLocation.getText().toString());
                    map.put("target", edtTarget.getText().toString());
                    map.put("done",edtDone.getText().toString());
                    map.put("less_value",edtLessValue.getText().toString());
                    map.put("vs_target",edtVsTarget.getText().toString());
                    map.put("last_year",edtLastyear.getText().toString());
                    map.put("trend",edtTrend.getText().toString());
                    mRef = mRef.child(edtLocation.getText().toString());

                    mRef.setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(AddTarget.this, "Updated", Toast.LENGTH_SHORT).show();
                                finish();
                            }else{
                                Toast.makeText(AddTarget.this, "Error : " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
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
        public static AddTarget.PlaceholderFragment newInstance(int sectionNumber) {
            AddTarget.PlaceholderFragment fragment = new AddTarget.PlaceholderFragment();
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
                    fragment = new ADF_Target_Fragment();
                    break;
                }
                case 1: {
                    fragment = new Quarterly_Fragment();
                    break;
                }

                case 2:{
                    fragment = new FYTD_Fragment();
                    break;
                }
                default:{
                    Toast.makeText(AddTarget.this, "Stop!", Toast.LENGTH_SHORT).show();
                }

            }
            return fragment;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }
    }
}
