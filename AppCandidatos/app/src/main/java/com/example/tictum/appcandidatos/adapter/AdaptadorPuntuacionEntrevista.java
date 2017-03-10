package com.example.tictum.appcandidatos.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.tictum.appcandidatos.R;
import com.example.tictum.appcandidatos.beans.Pais;
import com.example.tictum.appcandidatos.beans.PuntuacionEntrevista;

/**
 * Created by Juanjo on 08/03/2017.
 */

public class AdaptadorPuntuacionEntrevista extends ArrayAdapter<PuntuacionEntrevista> {

    public AdaptadorPuntuacionEntrevista(Context context,PuntuacionEntrevista[] puntuacion ) {
        super(context, R.layout.item_puntuacion, puntuacion);
    }





}
