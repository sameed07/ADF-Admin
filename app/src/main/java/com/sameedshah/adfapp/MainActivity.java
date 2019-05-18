package com.sameedshah.adfapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView adminNotes = findViewById(R.id.btnAdminNotes);
        adminNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AdminActivity.class));
            }
        });
        ImageView add_user = findViewById(R.id.image_addUsers);
        add_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AddUser.class));
            }
        });

        ImageView addTarget = findViewById(R.id.btnAddTarget);
        addTarget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,AddTarget.class));

            }
        });
        ImageView individualTarget = findViewById(R.id.individualTarget);

        individualTarget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,AddIndividualTargetActivity.class));

            }
        });

    }
}
