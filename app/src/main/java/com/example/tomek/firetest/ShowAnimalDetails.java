package com.example.tomek.firetest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ShowAnimalDetails extends AppCompatActivity {


    private TextView text;
    private TextView text2;
    private TextView text3;
    private TextView text4;


    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_animal_details);

        text = (TextView) findViewById(R.id.textViewImie);
        text2 = (TextView) findViewById(R.id.textViewData);
        text3 = (TextView) findViewById(R.id.textViewUmaszczenie);
        text4 = (TextView) findViewById(R.id.textViewRasa);


        Firebase.setAndroidContext(this);

        Firebase ref = new Firebase("https://firetest-49b5c.firebaseio.com");

        firebaseAuth = FirebaseAuth.getInstance();

        FirebaseUser user = firebaseAuth.getCurrentUser();
        String u = user.getUid();

        Query r = ref.child("Dogs").orderByChild("owner").equalTo(u);

        r.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                    Intent intent = getIntent();

                    Bundle b = intent.getExtras();

                    if (b != null) {
                        String s = (String) b.get("var");

                    Dog dog = dataSnapshot1.getValue(Dog.class);

                        if(dog.getName().toString().equals(s)){

                            String name = dog.getName();
                            String color = dog.getColor();
                            String race = dog.getRace();
                            String birthdate = dog.getBirthdate();

                            text.setText(name);
                            text2.setText(birthdate);
                            text3.setText(color);
                            text4.setText(race);
                        }
                }
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });




    }
}
