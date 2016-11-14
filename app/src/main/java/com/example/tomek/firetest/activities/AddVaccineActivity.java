package com.example.tomek.firetest.activities;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.tomek.firetest.R;

import java.util.Calendar;

public class AddVaccineActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonAddVaccine;
    private EditText editNextDate;
    private int mYear, mMonth, mDay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vaccine);

        buttonAddVaccine = (Button) findViewById(R.id.bAddVaccine);
        editNextDate = (EditText) findViewById(R.id.etNextDate);

        editNextDate.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        if (v == editNextDate) {

            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            editNextDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }

    }


//        buttonAddVaccine.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
////                Intent intent = new Intent(Intent.ACTION_EDIT);
////                intent.setType("vnd.android.cursor.item/event");
////                intent.putExtra("beginTime", cal.getTimeInMillis());
////                intent.putExtra("allDay", true);
////                intent.putExtra("rrule", "FREQ=YEARLY");
////                intent.putExtra("endTime", cal.getTimeInMillis()+60*60*1000);
////                intent.putExtra("title", "A Test Event from android app");
////                startActivity(intent);
//
//                //ContentResolver cr = getContentResolver();
//
//            }
//        });


}






