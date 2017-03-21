package com.example.tictum.appcandidatos.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tictum.appcandidatos.R;

/**
 * Created by Usuario on 21/03/2017.
 */

public class AdaptadorSpinner extends BaseAdapter {

    Context context;
    String[] opciones;
    LayoutInflater inflter;

    public AdaptadorSpinner(Context applicationContext, String[] opciones) {
        this.context = applicationContext;
        this.opciones = opciones;
        inflter = (LayoutInflater.from(applicationContext));
    }

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
        convertView = inflter.inflate(R.layout.custom_spinner_items, null);
        TextView names = (TextView) convertView.findViewById(R.id.textView);
        names.setText(opciones[position]);
        return convertView;
    }
}
