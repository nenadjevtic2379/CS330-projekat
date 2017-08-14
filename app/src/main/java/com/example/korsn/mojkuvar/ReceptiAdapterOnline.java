package com.example.korsn.mojkuvar;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Korsn on 13.8.2017..
 */

public class ReceptiAdapterOnline extends ArrayAdapter<ReceptOnline> {


    public ReceptiAdapterOnline(Activity context, List<ReceptOnline> recepti) {

        super(context, 0 , recepti);
    }


    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {


      ReceptOnline rOnline = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.recepti_online ,parent,false);

        }

        TextView tv = (TextView) convertView.findViewById(R.id.textViewNameOnline);
        TextView tv1 = (TextView) convertView.findViewById(R.id.textViewEMailOnline);

        tv.setText(rOnline.getIme());
        tv1.setText(rOnline.getVrsta());

        return convertView;

    }
}
