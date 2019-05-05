package com.sameedshah.adfapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import Adapters.CalenderClassAdapter;
import Model.ModelClassCalanderAdapter;

public class Admin_Schedule_fragment extends Fragment {

    CompactCalendarView compactCalendarView;
    RecyclerView mRecycler;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<ModelClassCalanderAdapter> modelClassCalanderAdapterArrayList = new ArrayList<>();
    private CalenderClassAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.schedule_frag,container,false);
        compactCalendarView = view.findViewById(R.id.compactcalendar_view);
        Event ev0 = new Event(getResources().getColor(R.color.colorAccent), 1548961200000L, "Some extra data that I want to store.");

        compactCalendarView.addEvent(ev0);
        mRecycler = view.findViewById(R.id.resNewNotes);
        layoutManager = new LinearLayoutManager(getContext());
        mRecycler.setLayoutManager(layoutManager);

        view.findViewById(R.id.add_fab_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getActivity(),Add_new_notes_activity.class));
            }
        });
       // btnCamera = view.findViewById(R.id,mCamera);

         adapter = new CalenderClassAdapter(getContext(),modelClassCalanderAdapterArrayList);

         mRecycler.setAdapter(adapter);

        DatabaseReference mRef = FirebaseDatabase.getInstance().getReference("Admin").child("AdminSchedule");

        mRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                ModelClassCalanderAdapter value = dataSnapshot.getValue(ModelClassCalanderAdapter.class);

                modelClassCalanderAdapterArrayList.add(value);

                 adapter.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

//        date = jsonObject.getString("date");
//
//        SimpleDateFormat dateFo = new SimpleDateFormat("dd-M-yyyy", Locale.getDefault());
//        Date d = dateFo.parse(date);
//
//        long dateinlong = d.getTime();
//
//        Event event1 = new Event(getResources().getColor(R.color.colorAccent), dateinlong);
//        compactCalendarView.addEvent(event1);
//
//
//        time = jsonObject.getString("time");
//
//        modelClassReminders.add(new ModelClassReminder(id, title, description, date, time, timebefore, pendingIntentId, calanderid2));
//






        return view;
    }
}
