package com.sameedshah.adfapp;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

public class Add_new_notes_activity extends AppCompatActivity {

    ImageView back_img;
    String date_time = "";
    int mYear;
    int mMonth;
    int mDay;



    int dayodfmon,monthofmon;

    int mHour;
    int mMinute;

    EditText title,description;
    TextView date_time_txt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_notes_activity);

        title = findViewById(R.id.title_edt_new_notes);
        description = findViewById(R.id.discription_edt_new_notes);
        back_img = findViewById(R.id.img_back);
        back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(new Intent(Add_new_notes_activity.this,Admin_Schedule_fragment.class));
               finish();
            }
        });

        date_time_txt = findViewById(R.id.date_time_txt);
        date_time_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker();
            }
        });

        findViewById(R.id.submit_btn).setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                String titl = title.getText().toString();
                String des = description.getText().toString();
                String dt = date_time_txt.getText().toString();

                DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Admin").child("AdminSchedule");

                DatabaseReference newref = ref.push();
                 SimpleDateFormat dateFormatForMonth2 = new SimpleDateFormat("dd", Locale.getDefault());

                HashMap<String,String> map = new HashMap<>();
                map.put("id",newref.getKey());
                map.put("calanderTitle",titl);
                map.put("calanderSubTitle",des);
                String[] split = dt.split(",");
                map.put("date",split[0]);
                map.put("time",split[1]);
                map.put("dayofmonth",dayodfmon+"");
                map.put("month",monthofmon+"");
 newref.setValue(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(Add_new_notes_activity.this, "Added!", Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });

    }

    private void datePicker(){

        // Get Current Date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                        dayodfmon = dayOfMonth;
                        monthofmon = monthOfYear+1;
                        date_time = dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;
                        //*************Call Time Picker Here ********************
                        tiemPicker();
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    private void tiemPicker(){
        // Get Current Time
        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);

        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        mHour = hourOfDay;
                        mMinute = minute;

                        date_time_txt.setText(date_time+", "+hourOfDay + ":" + minute);
                    }
                }, mHour, mMinute, false);
        timePickerDialog.show();
    }
}
