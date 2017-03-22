package com.example.tictum.appcandidatos.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.tictum.appcandidatos.R;

/**
 * Created by Usuario on 22/03/2017.
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
        return opciones.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.custom_spinner_items, null);
        TextView names = (TextView) view.findViewById(R.id.textView);
        names.setText(opciones[i]);
        return view;
    }

}
