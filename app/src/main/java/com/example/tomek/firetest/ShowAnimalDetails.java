package com.example.tomek.firetest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ShowAnimalDetails extends AppCompatActivity {


    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_animal_details);

        text = (TextView) findViewById(R.id.textView2);

        Intent intent = getIntent();

        Bundle b = intent.getExtras();

        if (b != null){
            String s = (String) b.get("var");
            text.setText(s);
        }


    }
}
