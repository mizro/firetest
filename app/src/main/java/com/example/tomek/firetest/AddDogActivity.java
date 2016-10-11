package com.example.tomek.firetest;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class AddDogActivity extends AppCompatActivity {

    private Button buttonInsertDog;
    private EditText editTextDogName;
    private EditText editTextBirthDate;
    private TextView textViewDogs;
    private EditText editTextColor;
    private EditText editTextSpecialSigns;
    private EditText editTextUniqNumber;
    private EditText editTextRace;


    private FirebaseAuth firebaseAuth;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_dog);

       // ActionBar actionBar = getActionBar();
       // actionBar.setDisplayHomeAsUpEnabled(true);

        Firebase.setAndroidContext(this);

        buttonInsertDog = (Button) findViewById(R.id.buttonInsertDog);
        editTextBirthDate = (EditText) findViewById(R.id.editTextBirthDate);
        editTextDogName = (EditText) findViewById(R.id.editTextDogName);
        textViewDogs = (TextView) findViewById(R.id.textViewDogs);
        editTextUniqNumber = (EditText) findViewById(R.id.editTextUniqNumber);
        editTextRace = (EditText) findViewById(R.id.editTextRace);
        editTextColor = (EditText) findViewById(R.id.editTextColor);
        editTextSpecialSigns = (EditText) findViewById(R.id.editTextSpecialSigns);


        progressDialog = new ProgressDialog(this);

        Firebase ref = new Firebase("https://firetest-49b5c.firebaseio.com");

        Query r = ref.child("Dogs").orderByChild("name").equalTo("lucy");

        Query qv = ref.child("Vaccines").orderByChild("dogName").equalTo("lucy");

        qv.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Vaccine v = dataSnapshot1.getValue(Vaccine.class);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                    Calendar c1 = Calendar.getInstance();


                    try {
                        c.setTime(sdf.parse(v.getDate().toString()));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    c.add(Calendar.DATE, 100);
                    String data = sdf.format(c.getTime());

                    long diff = c.getTimeInMillis() - c1.getTimeInMillis();
                    long days = diff /(24*60*60*1000);
                    String dayss = Long.toString(days);
//                    mMessages.add("Do szczepienia pozostało: " + dayss + " dni.");
//
//
//                        ArrayAdapter<String> adapter = new ArrayAdapter<>(
//                                AddDogActivity.this,
//                                android.R.layout.simple_list_item_1,
//                                mMessages);
//                        listViewDogs.setAdapter(adapter);


                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });



//        r.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
////
//               // String message = (String) dataSnapshot.getValue();
//                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
//
//                    Dog dog = dataSnapshot1.getValue(Dog.class);
//                    if(dog.getBirthdate().equals("data")) {
//
//
//                        String message = dog.getName();
//
//
//                        mMessages.add(message);
//
//
//                        ArrayAdapter<String> adapter = new ArrayAdapter<>(
//                                AddDogActivity.this,
//                                android.R.layout.simple_list_item_1,
//                                mMessages);
//                        listViewDogs.setAdapter(adapter);
//                    }
//                }
//            }
//
//
//            @Override
//            public void onCancelled(FirebaseError firebaseError) {
//
//            }
//        });

        buttonInsertDog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



//                StorageReference storageRef = FirebaseStorage.getInstance().getReference().child("pics/file.jpg");
//                Uri file = Uri.fromFile(new File("storage/emulated/0/Pictures/file.jpg"));
//                UploadTask uploadTask = storageRef.putFile(file);
//                uploadTask.addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
//                    @Override
//                    public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
//                    System.out.println(taskSnapshot.getBytesTransferred());
//                    }
//                });

               // textViewDogs.setText("lala");
                firebaseAuth = FirebaseAuth.getInstance();

                FirebaseUser user = firebaseAuth.getCurrentUser();
                String u = user.getUid();

                Firebase ref = new Firebase("https://firetest-49b5c.firebaseio.com/");

                String name = editTextDogName.getText().toString().trim();
                String birthdate = editTextBirthDate.getText().toString().trim();
                String userID = u;
                String color = editTextColor.getText().toString().trim();
                String specialSigns = editTextSpecialSigns.toString().trim();
                String uniqNumber = editTextUniqNumber.toString().trim();
                String race = editTextRace.toString().trim();

                Dog dog = new Dog();

                dog.setBirthdate(birthdate);
                dog.setName(name);
                dog.setOwner(userID);
                dog.setColor(color);
                dog.setRace(race);
                dog.setSpecialSigns(specialSigns);
                dog.setUniqNumber(uniqNumber);

                Firebase newref = ref.child("Dogs").push();
                newref.setValue(dog);

                Firebase newref1 = ref.child("Vaccines").push();

                newref.setValue(dog);


                Vaccine vac = new Vaccine();
                Calendar c = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                String data = sdf.format(c.getTime());

                vac.setDate(data);
                vac.setOwnerid(u);
                vac.setDogName("lucy");
                vac.setType("wscieklizna");


               // newref1.setValue(vac);
                //  newref1.setValue(vac);
                //ref.child("Dogs").setValue(dog);
               // progressDialog.setMessage(u);
               // progressDialog.show();

            }
        });
    }
//    public boolean onOptionsItemSelected(MenuItem item){
//        Intent myIntent = new Intent(getApplicationContext(), ProfileActivity.class);
//        startActivityForResult(myIntent, 0);
//        return true;
//
//    }
}
