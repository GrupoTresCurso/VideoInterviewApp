package com.example.tictum.appcandidatos.adapter;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.example.tictum.appcandidatos.beans.Entrevista;

public class AdaptadorEntrevistas extends ArrayAdapter<Entrevista> {

    public AdaptadorEntrevistas(Context context,int resource) {
        super(context, resource);
    }
}
