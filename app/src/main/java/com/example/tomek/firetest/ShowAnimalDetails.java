package com.example.tomek.firetest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class ShowAnimalDetails extends AppCompatActivity {

    public static final String ANIMAL_NAME = "animal_name";

    private TextView text;
    private TextView text2;
    private TextView text3;
    private TextView text4;

    private FirebaseAuth firebaseAuth;

    private Button addVaccineButton;
    private ListView listViewTemp;

    private ArrayList<String> mMessages = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_animal_details);

        text = (TextView) findViewById(R.id.textViewImie);
        text2 = (TextView) findViewById(R.id.textViewData);
        text3 = (TextView) findViewById(R.id.textViewUmaszczenie);
        text4 = (TextView) findViewById(R.id.textViewRasa);

        addVaccineButton = (Button) findViewById(R.id.addVaccineButton);

        listViewTemp = (ListView) findViewById(R.id.listViewTemp);


        Firebase.setAndroidContext(this);

        final Firebase ref = new Firebase("https://firetest-49b5c.firebaseio.com");

        firebaseAuth = FirebaseAuth.getInstance();

        FirebaseUser user = firebaseAuth.getCurrentUser();
        String u = user.getUid();

        Query r = ref.child("Dogs").orderByChild("owner").equalTo(u);



//        r.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
//
//                    Intent intent = getIntent();
//
//                    Bundle b = intent.getExtras();
//
//                    if (b != null) {
//                        String s = (String) b.get(ANIMAL_NAME);
//
//                    Dog dog = dataSnapshot1.getValue(Dog.class);
//
//                        if(dog.getName().equals(s)){
//
//                            String name = dog.getName();
//                            String color = dog.getColor();
//                            String race = dog.getRace();
//                            String birthdate = dog.getBirthdate();
//
//                            text.setText(name);
//                            text2.setText(birthdate);
//                            text3.setText(color);
//                            text4.setText(race);
//                        }
//                }
//                }
//            }
//
//            @Override
//            public void onCancelled(FirebaseError firebaseError) {
//
//            }
//        });




        addVaccineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Firebase ref1 = new Firebase("https://firetest-49b5c.firebaseio.com/VacTest");

                ref1.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {


                            VacciType vac = dataSnapshot1.getValue(VacciType.class);
                            // dogs.add(dog);

                            String message = vac.getName();
                           // mMessages.add(message);



                            test(message);




                        }
                            ArrayAdapter<String> adapter = new ArrayAdapter<>(
                                    ShowAnimalDetails.this,
                                    android.R.layout.simple_list_item_1,
                                    mMessages);
                            listViewTemp.setAdapter(adapter);


                    }


                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }
                });




//                firebaseAuth = FirebaseAuth.getInstance();
//
//                FirebaseUser user = firebaseAuth.getCurrentUser();
//                String u = user.getUid();
//
//                Firebase ref = new Firebase("https://firetest-49b5c.firebaseio.com/");
//
//                Firebase newref = ref.child("VacTest").push();
//
//                VacciType vac = new VacciType();
//                Calendar c = Calendar.getInstance();
//                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
//                String data = sdf.format(c.getTime());
//
//                vac.setName("nosowka");
//                newref.setValue(vac);


            }
        });

    }

    public void test(String message) {
        final Firebase ref = new Firebase("https://firetest-49b5c.firebaseio.com");

        Query q = ref.child("Vaccines").orderByChild("type").equalTo(message);
        q.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot2 : dataSnapshot.getChildren()) {

                    Vaccine v = dataSnapshot2.getValue(Vaccine.class);

                    String m = v.getDogName();

                    mMessages.add(m);
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

    }
}
