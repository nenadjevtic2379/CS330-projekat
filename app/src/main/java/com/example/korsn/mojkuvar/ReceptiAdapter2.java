package com.example.korsn.mojkuvar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Korsn on 10.8.2017..
 */

public class ReceptiAdapter2 extends ArrayAdapter<Recept> {

    public ReceptiAdapter2(Context context, ArrayList<Recept> lista) {

        super (context,0,lista);
        Recept r = new Recept(0, "Slatko", "", "" , "");
        lista.add(0, r);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Recept r = getItem(position);



        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.show_name ,parent,false);

        }

        TextView tv = (TextView) convertView.findViewById(R.id.ime);
        tv.setText(r.getIme());



        return convertView;
    }

}
