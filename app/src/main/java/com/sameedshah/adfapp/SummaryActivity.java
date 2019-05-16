package com.sameedshah.adfapp;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import com.sameedshah.adfapp.Adapters.NotesAdapter;
import com.sameedshah.adfapp.Model.Notes;

public class SummaryActivity extends AppCompatActivity {

    private RecyclerView notesRecycler;
    private RecyclerView.LayoutManager layoutManager;
    private FirebaseDatabase database;
    private DatabaseReference mRef;

    private ArrayList<Notes> notesList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        database = FirebaseDatabase.getInstance();
        mRef = database.getReference("Admin").child("AdminNotes");
        notesRecycler = findViewById(R.id.notesRecycler);
        layoutManager = new LinearLayoutManager(this);
        notesRecycler.setLayoutManager(layoutManager);
        notesRecycler.setHasFixedSize(true);

        loadNotes();

    }

    private void loadNotes() {


        final NotesAdapter adapter = new NotesAdapter(SummaryActivity.this, notesList);
        notesRecycler.setAdapter(adapter);

        mRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                Notes obj = dataSnapshot.getValue(Notes.class);
                obj.ref=dataSnapshot.getKey();
                notesList.add(obj);
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

    }


}
