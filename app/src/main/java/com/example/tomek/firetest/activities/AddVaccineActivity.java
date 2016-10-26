package com.example.tomek.firetest.activities;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.CalendarContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.tomek.firetest.R;

import java.util.Calendar;
import java.util.TimeZone;

public class AddVaccineActivity extends AppCompatActivity {

    private Button buttonAddVaccine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vaccine);

        buttonAddVaccine = (Button) findViewById(R.id.bAddVaccine);



        buttonAddVaccine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


//                Intent intent = new Intent(Intent.ACTION_EDIT);
//                intent.setType("vnd.android.cursor.item/event");
//                intent.putExtra("beginTime", cal.getTimeInMillis());
//                intent.putExtra("allDay", true);
//                intent.putExtra("rrule", "FREQ=YEARLY");
//                intent.putExtra("endTime", cal.getTimeInMillis()+60*60*1000);
//                intent.putExtra("title", "A Test Event from android app");
//                startActivity(intent);

                //ContentResolver cr = getContentResolver();

            }
        });

    }





}
