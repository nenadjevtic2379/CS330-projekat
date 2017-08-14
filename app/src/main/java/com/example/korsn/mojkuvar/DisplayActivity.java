package com.example.korsn.mojkuvar;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.IdRes;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * Created by Korsn on 9.8.2017..
 */

public class DisplayActivity extends ActionBarActivity{
    DBHelper db;
    TextView ime;
  //  TextView vrsta;
    TextView sastojci;
    TextView opis;
    RadioGroup rg;
    RadioButton slatko, slano;

    String svrsta;
    int id_to_update = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        rg = (RadioGroup) findViewById(R.id.radio_group) ;
        slatko = (RadioButton) findViewById(R.id.slatko) ;
        slano = (RadioButton) findViewById(R.id.slano) ;
        ime = (TextView) findViewById(R.id.editTextName);
       // vrsta = (TextView) findViewById(R.id.editTextVrsta);
        sastojci = (TextView) findViewById(R.id.editTextSastojci);
        opis = (TextView) findViewById(R.id.editTextOpis);


        db = new DBHelper(this);

        Bundle extras = getIntent().getExtras(); {


            int value = extras.getInt("id");

            if (value > 0) {
                Cursor c = db.getData(value);
                id_to_update = value;
                c.moveToFirst();

                String sime = c.getString(c.getColumnIndex(DBHelper.RECEPTI_IME));
                svrsta = c.getString(c.getColumnIndex(DBHelper.RECEPTI_VRSTA));
                String ssastojci = c.getString(c.getColumnIndex(DBHelper.RECEPTI_SASTOJCI));
                String sopis = c.getString(c.getColumnIndex(DBHelper.RECEPTI_OPIS));

                if(!c.isClosed()) {
                    c.close();
                }

                Button b1 = (Button) findViewById(R.id.button1);
                b1.setVisibility(View.INVISIBLE);


                ime.setText((CharSequence) sime);
                ime.setFocusable(false);
                ime.setClickable(false);


            if(svrsta.equalsIgnoreCase("slatko")) {
                slatko.setChecked(true);
            }
            if(svrsta.equalsIgnoreCase("slano")) {
                slano.setChecked(true);
            }

                slatko.setFocusable(false);
                slatko.setClickable(false);
                slano.setFocusable(false);
                slano.setClickable(false);
                

                sastojci.setText((CharSequence) ssastojci);
                sastojci.setFocusable(false);
                sastojci.setClickable(false);

                opis.setText((CharSequence) sopis);
                opis.setFocusable(false);
                opis.setClickable(false);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       Bundle extras = getIntent().getExtras();

        if (extras != null) {
            int value = extras.getInt("id");
            if (value > 0) {
                getMenuInflater().inflate(R.menu.menu_display, menu);
            }
            else {
                getMenuInflater().inflate(R.menu.menu_main, menu);
            }
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {

            case R.id.item2:
                startActivity(new Intent(getBaseContext(), LogIn.class));
                return true;

            case R.id.edit:
                Button b = (Button) findViewById(R.id.button1);
                b.setVisibility(View.VISIBLE);

                ime.setEnabled(true);
                ime.setFocusableInTouchMode(true);
                ime.setClickable(true);

                slatko.setFocusableInTouchMode(true);
                slatko.setClickable(true);
                slatko.setEnabled(true);
                slano.setFocusableInTouchMode(true);
                slano.setClickable(true);
                slano.setEnabled(true);

          /*      vrsta.setEnabled(true);
                vrsta.setFocusableInTouchMode(true);
                vrsta.setClickable(true);*/

                sastojci.setEnabled(true);
                sastojci.setFocusableInTouchMode(true);
                sastojci.setClickable(true);

                opis.setEnabled(true);
                opis.setFocusableInTouchMode(true);
                opis.setClickable(true);

                return true;

            case R.id.delete:

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Da li ste sigurni da želite da obrišete ovaj recept?")
                        .setPositiveButton("Da", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                db.deleteRecept(id_to_update);
                                Toast.makeText(getApplicationContext(), "Uspešno obrisano" , Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);

                            }

                        })
                        .setNegativeButton("Ne", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });

                        AlertDialog ad = builder.create();
                        ad.setTitle("Da li ste sigurni?");
                        ad.show();
                return true;

            default:
                return  super.onOptionsItemSelected(item);
        }
    }

public  void  saveData(View v) {

    Bundle extras = getIntent().getExtras();
    if (extras != null) {
        int Value = extras.getInt("id");
        if (Value > 0) {

            if (db.updateRecept(id_to_update, ime.getText().toString() ,((RadioButton)this.findViewById(rg.getCheckedRadioButtonId())).getText().toString(), sastojci.getText().toString(),opis.getText().toString())) {
                Toast.makeText(getApplicationContext(), "Uspešno ažurirano" , Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }

            else {
                Toast.makeText(getApplicationContext(), "Neuspešno ažurirano" , Toast.LENGTH_SHORT).show();
            }

        }

        else {

            if (db.addRecept(ime.getText().toString() , ((RadioButton)this.findViewById(rg.getCheckedRadioButtonId())).getText().toString(), sastojci.getText().toString(),opis.getText().toString())) {
                Toast.makeText(getApplicationContext(),"Uspešno dodat recept", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(getApplicationContext(), "Neuspešno dodat recept", Toast.LENGTH_SHORT).show();
            }

            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }

        }
}


}
