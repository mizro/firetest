package com.example.tomek.firetest;

/**
 * Created by Tomek on 2016-09-12.
 */

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
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

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    //firebase auth object
    private FirebaseAuth firebaseAuth;

    //view objects
    private TextView textViewUserEmail;
    private Button buttonLogout;
    private Button buttonAddDog;

    private ListView listViewShowDogs;
    private ArrayList<String> mMessages = new ArrayList<>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Firebase.setAndroidContext(this);

        //initializing firebase authentication object
        firebaseAuth = FirebaseAuth.getInstance();

        //if the user is not logged in
        //that means current user will return null
        if(firebaseAuth.getCurrentUser() == null){
            //closing this activity
            finish();
            //starting login activity
            startActivity(new Intent(this, LoginActivity.class));
        }

        //getting current user
        FirebaseUser user = firebaseAuth.getCurrentUser();

        //initializing views
        textViewUserEmail = (TextView) findViewById(R.id.textViewUserEmail);
        buttonLogout = (Button) findViewById(R.id.buttonLogout);
        buttonAddDog = (Button) findViewById(R.id.buttonAddDog);
        listViewShowDogs = (ListView) findViewById((R.id.listViewShowDogs));

        //displaying logged in user name
        //textViewUserEmail.setText("Welcome "+user.getEmail());

        //adding listener to button
        buttonLogout.setOnClickListener(this);
        buttonAddDog.setOnClickListener(this);

        Firebase ref = new Firebase("https://firetest-49b5c.firebaseio.com");

        Query r = ref.child("Dogs").orderByChild("owner").equalTo(user.getUid());

        r.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                    Dog dog = dataSnapshot1.getValue(Dog.class);

                    String message = dog.getName();

                    mMessages.add(message);


                    ArrayAdapter<String> adapter = new ArrayAdapter<>(
                            ProfileActivity.this,
                            android.R.layout.simple_list_item_1,
                            mMessages);
                    listViewShowDogs.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });




    }



    @Override
    public void onClick(View view) {
        //if logout is pressed
        if(view == buttonLogout){
            //logging out the user
            firebaseAuth.signOut();
            //closing activity
            finish();
            //starting login activity
            startActivity(new Intent(this, LoginActivity.class));
        }

        if(view == buttonAddDog){
            textViewUserEmail.setText("Welcome ");
            startActivity(new Intent(this, AddDogActivity.class));
        }
    }
}