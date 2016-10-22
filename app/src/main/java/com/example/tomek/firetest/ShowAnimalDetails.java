package com.example.tomek.firetest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.tomek.firetest.adapter.AnimalsAdapter;
import com.example.tomek.firetest.adapter.VaccinesAdapter;
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

    private FirebaseAuth firebaseAuth;

    private Button addVaccineButton;

    private RecyclerView rvVaccines;
    private LinearLayoutManager layoutManager;
    private VaccinesAdapter adapter;

    private ArrayList<String> mMessages = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_animal_details);
        renderView();

        text = (TextView) findViewById(R.id.textViewRasa);

        addVaccineButton = (Button) findViewById(R.id.addVaccineButton);

        Firebase.setAndroidContext(this);

        final Firebase ref = new Firebase("https://firetest-49b5c.firebaseio.com");

        firebaseAuth = FirebaseAuth.getInstance();

        FirebaseUser user = firebaseAuth.getCurrentUser();
        String u = user.getUid();

        Query r = ref.child("Vaccines").orderByChild("ownerid").equalTo(u);

        Intent intent = getIntent();

        final Bundle b = intent.getExtras();

        r.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                ArrayList<Vaccine> vaccines = new ArrayList<>();

                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                    Vaccine vaccine = dataSnapshot1.getValue(Vaccine.class);

                    if (b != null) {
                        String s = (String) b.get(ANIMAL_NAME);
                        text.setText(s);

                        if(vaccine.getDogName().equals(s)){
                            vaccines.add(vaccine);
                        }
                }
                }
                adapter.setVaccines(vaccines);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

//        addVaccineButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//                Firebase ref1 = new Firebase("https://firetest-49b5c.firebaseio.com/VacTest");
//
//                ref1.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//                        for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
//
//
//                            VacciType vac = dataSnapshot1.getValue(VacciType.class);
//                            // dogs.add(dog);
//                            String message = vac.getName();
//                            // mMessages.add(message)
//                            test(message);
//
//                        }}
//
//
//
//                    @Override
//                    public void onCancelled(FirebaseError firebaseError) {
//
//                    }
//                });

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

//            }
//        });

    }

    private void renderView() {
        rvVaccines = (RecyclerView) findViewById(R.id.rv_vaccines);

        layoutManager = new LinearLayoutManager(this);
        rvVaccines.setLayoutManager(layoutManager);

        adapter = new VaccinesAdapter();
        rvVaccines.setAdapter(adapter);

        // Drawable dividerDrawable = ContextCompat.getDrawable(this, R.drawable.divider);
        // RecyclerView.ItemDecoration dividerItemDecoration = new DividerItemDecoration(dividerDrawable);
        //  rvAnimals.addItemDecoration(dividerItemDecoration);
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
