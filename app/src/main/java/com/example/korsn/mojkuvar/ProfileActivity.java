package com.example.korsn.mojkuvar;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;



/**
 * Created by Korsn on 11.8.2017..
 */

public class ProfileActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;

    //view objects
    private TextView textViewUserEmail;
    private Button buttonLogout;

    private EditText et1;
    private EditText et2;
    private EditText et3;
    private RadioGroup rg;
    private RadioButton rb1;
    private RadioButton rb2;
    private Button b1;

    private Button b2;

    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_activity);

        //initializing firebase authentication object
        firebaseAuth = FirebaseAuth.getInstance();

        //if the user is not logged in
        //that means current user will return null
        if (firebaseAuth.getCurrentUser() == null) {
            //closing this activity
            finish();
            //starting login activity
            startActivity(new Intent(this, LogIn.class));
        }

        //getting current user
        final FirebaseUser user = firebaseAuth.getCurrentUser();

        //initializing views
        textViewUserEmail = (TextView) findViewById(R.id.textViewUserEmail);
        buttonLogout = (Button) findViewById(R.id.buttonLogout);

        et1 = (EditText) findViewById(R.id.editTextNameOnl);
        et2 = (EditText) findViewById(R.id.editSastOnl);
        et3 = (EditText) findViewById(R.id.editOpOnl);
        rg = (RadioGroup) findViewById(R.id.radio_groupOnl);
        rb1 = (RadioButton) findViewById(R.id.slatkoOnl);
        rb2 = (RadioButton) findViewById(R.id.slanoOnl);
        b1 = (Button) findViewById(R.id.buttonSaveOnl);
        b2 = (Button) findViewById(R.id.buttonSeeOnl);


        reference = FirebaseDatabase.getInstance().getReference("Recepti");
// Write a message to the database


        //displaying logged in user name
        textViewUserEmail.setText("Welcome " + user.getEmail());

        //adding listener to button
        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //logging out the user
                firebaseAuth.signOut();
                //closing activity
                finish();
                //starting login activity
                startActivity(new Intent(getApplicationContext(), LogIn.class));

            }
        });


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String vrsta = "";

                String name = et1.getText().toString().trim();
                String sastojci = et2.getText().toString().trim();
                String opis = et3.getText().toString().trim();
                String email = user.getEmail().toString().trim();

                if (rb1.isChecked()) {
                    vrsta = "slatko";
                    vrsta.trim();
                }
                if (rb2.isChecked()) {
                    vrsta = "slano";
                    vrsta.trim();
                }

                if(!TextUtils.isEmpty(name) && !TextUtils.isEmpty(sastojci) && !TextUtils.isEmpty(opis)) {

                    String id = reference.push().getKey();
                    ReceptOnline recepti = new ReceptOnline(id, name, vrsta, sastojci, opis, email);

                    reference.child(id).setValue(recepti);

                    et1.setText("");
                    et2.setText("");
                    et3.setText("");
                    rb1.setChecked(true);
                    Toast.makeText(ProfileActivity.this, "Recept dodat", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(ProfileActivity.this, "Morate uneti sve podatke", Toast.LENGTH_SHORT).show();
                }

            }
        });


    b2.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(getApplicationContext(),DisplayActivityOnline.class);
            startActivity(i);
        }
    });

    }

}



