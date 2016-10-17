package com.example.tomek.firetest;

/**
 * Created by Tomek on 2016-09-12.
 */

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
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
        buttonAddDog = (Button) findViewById(R.id.buttonAddDog);
        listViewShowDogs = (ListView) findViewById((R.id.listViewShowDogs));

        //displaying logged in user name
        //textViewUserEmail.setText("Welcome "+user.getEmail());

        //adding listener to button
        buttonAddDog.setOnClickListener(this);

        Firebase ref = new Firebase("https://firetest-49b5c.firebaseio.com");

        Query r = ref.child("Dogs").orderByChild("owner").equalTo(user.getUid());

        r.addValueEventListener(new ValueEventListener() {
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


        listViewShowDogs.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return 0;
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                View v = convertView;
                mMessages.get(position);

                if (position % 2 == 1){
                    ((TextView)convertView).setTextColor(Color.BLUE);
                }

                return v;
            }
        });




        listViewShowDogs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String var = ((TextView) view).getText().toString();

               // ((TextView) view).setTextColor(Color.BLUE);

                Intent intent = new Intent(getApplicationContext(), ShowAnimalDetails.class);
                intent.putExtra("var", var);
                startActivity(intent);
            }
        });

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.logout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                logout();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void logout() {
        //if logout is pressed
        firebaseAuth.signOut();
        //closing activity
        finish();
        //starting login activity
        startActivity(new Intent(this, LoginActivity.class));
    }

    @Override
    public void onClick(View view) {

        if(view == buttonAddDog){
            //textViewUserEmail.setText("Welcome ");
            startActivity(new Intent(this, AddDogActivity.class));
        }
    }
}