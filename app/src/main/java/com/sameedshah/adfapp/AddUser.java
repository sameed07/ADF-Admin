package com.sameedshah.adfapp;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class AddUser extends AppCompatActivity {

    EditText edt_username,edt_email,edt_pasword,edt_confpassword;

    FirebaseDatabase mDatabase;
    DatabaseReference mRef;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        //init
        mDatabase = FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference("Users");

        edt_username = findViewById(R.id.edt_username);
        edt_email = findViewById(R.id.edt_email);
        edt_pasword = findViewById(R.id.edt_password);
        edt_confpassword = findViewById(R.id.edt_Confirmpassword);



    }

    public void  onAdd(View view){


        if(edt_username.getText().toString().equals("") && edt_email.getText().toString().equals("")
        && edt_pasword.getText().toString().equals("") && edt_confpassword.getText().toString().equals("")){

            Toast.makeText(this, "Fill all fields", Toast.LENGTH_SHORT).show();
        }else {
            if (edt_pasword.getText().toString().equals(edt_confpassword.getText().toString())) {


                final ProgressDialog progressDialog = new ProgressDialog(this);
                progressDialog.setTitle("Uploading...");
                progressDialog.show();
                Map<String, Object> map = new HashMap<>();
                map.put("Username", edt_username.getText().toString());
                map.put("Email", edt_email.getText().toString().trim());
                map.put("Password", edt_pasword.getText().toString().trim());


                mRef.push().setValue(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(AddUser.this, "User added", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                });




            } else {
                Toast.makeText(this, "Password doesn't match", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
