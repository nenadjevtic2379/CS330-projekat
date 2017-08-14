package com.example.korsn.mojkuvar;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;



public class MainActivity extends AppCompatActivity {

   // private ListView objListView;

    DBHelper db;

    ArrayList<Recept> niz;
    ArrayList<Recept> niz2;
    Spinner spiner;
    Spinner spinner2;
    ReceptiAdapter adapter;
    ReceptiAdapter2 adapter2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DBHelper(this);
         niz = db.getAllRecepti();
        niz2 = db.getAllRecepti2();
        spiner = (Spinner) findViewById(R.id.spinner);
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        // adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, niz);

        adapter2 = new ReceptiAdapter2(this,niz2);
        adapter = new ReceptiAdapter(this, niz);
        adapter2.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spiner.setAdapter(adapter);
        spinner2.setAdapter(adapter2);
        spiner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                if (position > 0) {

                    spiner.setSelection(0);

                    Bundle dataBundle = new Bundle();

                    dataBundle.putInt("id", niz.get(position).getId());


                    Intent intent = new Intent(getApplicationContext(), DisplayActivity.class);
                    //  intent.putExtra("id", niz.get(position).getIme());
                    intent.putExtras(dataBundle);
                    startActivity(intent);

                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            if (position > 0) {

                spinner2.setSelection(0);
                Bundle dataBundle = new Bundle();

                dataBundle.putInt("id", niz2.get(position).getId());


                Intent intent = new Intent(getApplicationContext(), DisplayActivity.class);
                //  intent.putExtra("id", niz.get(position).getIme());
                intent.putExtras(dataBundle);
                startActivity(intent);
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    });



    /*   objListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

               Bundle dataBundle = new Bundle();

               dataBundle.putInt("id" , niz.get(position).getId());


               Intent intent = new Intent(getApplicationContext(), DisplayActivity.class);
              //  intent.putExtra("id", DBHelper.RECEPTI_ID);
              intent.putExtras(dataBundle);
                startActivity(intent);

             //   Toast.makeText(MainActivity.this, (int) id,Toast.LENGTH_SHORT).show();
              //  int pos = objListView.getPositionForView(view);

//int s = parent.getId();

         //       Toast.makeText(MainActivity.this, pos, Toast.LENGTH_SHORT).show();
            }
        });  */

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
       /* int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);*/
        super.onOptionsItemSelected(item);

        switch(item.getItemId())
        {
            case R.id.item1:
                Bundle dataBundle = new Bundle();
                dataBundle.putInt("id", 0);

                Intent intent = new Intent(getApplicationContext(),DisplayActivity.class);
                intent.putExtras(dataBundle);

                startActivityForResult(intent, 0);
                return true;
            case R.id.item2:
                Bundle dataBundle2 = new Bundle();
                dataBundle2.putInt("id",1);
                Intent intent2 = new Intent(getApplicationContext(), LogIn.class);
                intent2.putExtras(dataBundle2);
                startActivity(intent2);



                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        db=new DBHelper(this);

    /*  ArrayList<Recept> arrayList = db.getAllRecepti();
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_list_item_single_choice, arrayList);
        Spinner s = (Spinner) findViewById(R.id.spinner);
        s.setAdapter(aa);

        ArrayList<Recept> arrayList2 = db.getAllRecepti2();
        ArrayAdapter aa2 = new ArrayAdapter(this, android.R.layout.simple_list_item_single_choice, arrayList2);
        Spinner s2 = (Spinner) findViewById(R.id.spinner2);
        s2.setAdapter(aa2);*/
    }


}
