package com.example.korsn.mojkuvar;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.app.AlertDialog;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Korsn on 14.8.2017..
 */

public class DisplayActivityOnline extends AppCompatActivity {

    private DatabaseReference reference;
    private List<ReceptOnline> recepti;
    private ListView listView;
    private Context context = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_online);

        reference = FirebaseDatabase.getInstance().getReference("Recepti");
        recepti = new ArrayList<>();
        listView = (ListView) findViewById(R.id.listViewRecepti);

    }

    @Override
    protected void onStart() {
        super.onStart();


        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //clearing the previous artist list
                recepti.clear();

                //iterating through all the nodes
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    //svi recepti
                    ReceptOnline ro = ds.getValue(ReceptOnline.class);
                    //dodavanje u listu
                    recepti.add(ro);

                }

                //kreiranje adaptera

                ReceptiAdapterOnline adapter = new ReceptiAdapterOnline(DisplayActivityOnline.this, recepti);

                listView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ReceptOnline r = recepti.get(position);

                AlertDialog.Builder dialog = new AlertDialog.Builder(context);
                LayoutInflater lInflater = getLayoutInflater();
                final View dialogView = lInflater.inflate(R.layout.display_online , null);
                dialog.setView(dialogView);

                final TextView tv1 = (TextView) dialogView.findViewById(R.id.dialogEmail);
                final TextView tv2 = (TextView) dialogView.findViewById(R.id.dialogSastojci);
                final TextView tv3 = (TextView) dialogView.findViewById(R.id.dialogOpis);

                dialog.setTitle(r.getIme());

                tv1.setText(r.getEmail());
                tv2.setText(r.getSastojci());
                tv3.setText(r.getOpis());

                final AlertDialog ad = dialog.create();
                ad.show();

            }
        });

    }
}
