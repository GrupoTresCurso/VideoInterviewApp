package com.example.tictum.appcandidatos.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.tictum.appcandidatos.R;
import com.example.tictum.appcandidatos.beans.Pais;

/**
 * Created by Juanjo on 08/03/2017.
 */

public class AdaptadorPaises  extends ArrayAdapter<Pais> {

    public AdaptadorPaises(Context context,Pais[] paises) {
        super(context, R.layout.item_paises, paises);
    }


    public View getView(int position, View convertView, ViewGroup parent) {

        View itemView = convertView;
        ViewHolderPaises holder;

        if (itemView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            itemView = inflater.inflate(R.layout.item_paises, null);
            holder = new ViewHolderPaises();


            holder.nombre = (TextView) itemView.findViewById(R.id.pais_origen);
            itemView.setTag(holder);

        } else {
            holder = (ViewHolderPaises) itemView.getTag();
        }

        Pais pais = getItem(position);


        holder.nombre.setText(pais.getNombre());

        return (itemView);
    }

    private class ViewHolderPaises {

        TextView nombre;
    }

}

