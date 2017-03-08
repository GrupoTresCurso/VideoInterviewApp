package com.example.tictum.appcandidatos.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.tictum.appcandidatos.R;
import com.example.tictum.appcandidatos.beans.Adjunto;
import com.example.tictum.appcandidatos.beans.Entrevista;

/**
 * Created by carmenicuadrado on 08/03/2017.
 */

public class AdaptadorAdjuntos extends ArrayAdapter<Adjunto> {

    class ViewHolder{
        TextView etiquetaAdjunto;
    }
    // constructor del adaptador para mostrar la lista de adjuntos
    public AdaptadorAdjuntos(Context context, Adjunto[] listaAdjuntos) {
        super(context, R.layout.item_adjunto, listaAdjuntos);
    }

    // obtenemos la vista de cada objeto adjunto y lo mostramos
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = convertView;
        ViewHolder holder;

        if(itemView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            itemView = inflater.inflate(R.layout.item_adjunto, null);

            holder = new ViewHolder();
            holder.etiquetaAdjunto = (TextView) itemView.findViewById(R.id.etiquetaAdjunto);

            itemView.setTag(holder);
        }
        else{
            holder = (ViewHolder)itemView.getTag();
        }


        holder.etiquetaAdjunto.setText(getItem(position).getEtiquetaAdjunto());
        return (itemView);
    }
}
