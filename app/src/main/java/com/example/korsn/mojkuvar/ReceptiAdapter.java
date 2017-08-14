package com.example.korsn.mojkuvar;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Korsn on 10.8.2017..
 */

public class ReceptiAdapter extends ArrayAdapter<Recept> {


    public ReceptiAdapter(Context context, ArrayList<Recept> lista) {

        super (context,0,lista);
        Recept r = new Recept(0, "Slano", "", "" , "");
        lista.add(0, r);
    }


    @Override
    public View getView(int position, View convertView,  ViewGroup parent) {
        Recept r = getItem(position);



        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.show_name ,parent,false);

        }

            TextView tv = (TextView) convertView.findViewById(R.id.ime);
            tv.setText(r.getIme());



        return convertView;
    }
}
