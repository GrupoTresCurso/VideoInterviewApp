package com.example.tictum.appcandidatos.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.tictum.appcandidatos.R;
import com.example.tictum.appcandidatos.beans.Entrevista;

public class AdaptadorEntrevistas extends ArrayAdapter<Entrevista> {

public AdaptadorEntrevistas(Context context,int resource) {
        super(context, resource);
        }

    class ViewHolder{
    TextView nombreEntrevista;
    }

    // constructor del adaptador para mostrar la lista de entrevistas
    public AdaptadorEntrevistas(Context context,Entrevista[] listaEntrevistas) {
        super(context, R.layout.layout_mostrar_entrevistas,listaEntrevistas);
    }

    // obtenemos la vista de cada objeto entrevista y lo mostramos
    public View getView(int posicion,View convertView, ViewGroup parent){
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View itemView = inflater.inflate(R.layout.layout_mostrar_entrevistas,null);
        AdaptadorEntrevistas.ViewHolder holder;
        holder = new AdaptadorEntrevistas.ViewHolder();

        holder.nombreEntrevista = (TextView) itemView.findViewById(R.id.nombreEntrevista);

        Entrevista entrevista = getItem(posicion);

        holder.nombreEntrevista.setText(entrevista.getNombrePuesto());

        return (itemView);
    }

}
