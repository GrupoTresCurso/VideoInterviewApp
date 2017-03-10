package com.example.tictum.appcandidatos.parsers;

import android.util.JsonReader;

import com.example.tictum.appcandidatos.beans.Adjunto;
import com.example.tictum.appcandidatos.beans.Entrevista;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


//http://www.hermosaprogramacion.com/2015/01/android-json-parsing/

public class JsonEntrevistaParser {

    public List<Entrevista> readJsonStream(InputStream in) throws IOException {
        // Nueva instancia JsonReader
        JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
        try {
            // Leer Array
            return leerArrayEntrevista(reader);
        } finally {
            reader.close();
        }

    }

    public List leerArrayEntrevista(JsonReader reader) throws IOException {
        // Lista temporal
        ArrayList entrevistas = new ArrayList();

        reader.beginArray();
        while (reader.hasNext()) {
            // Leer objeto
            //entrevistas.add(leerEntrevista(reader));
        }
        reader.endArray();
        return entrevistas;
    }

    //ADAPTAR A JSON ENTREVISTAS
    /*public Entrevista leerEntrevista(JsonReader reader) throws IOException {
        String especie = null;
        String descripcion = null;
        String imagen = null;

        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            switch (name) {
                case "especie":
                    especie = reader.nextString();
                    break;
                case "descripcion":
                    descripcion = reader.nextString();
                    break;
                case "imagen":
                    imagen = reader.nextString();
                    break;
                default:
                    reader.skipValue();
                    break;
            }
        }
        reader.endObject();
        //return new Animal(especie, descripcion, imagen);
    }*/
}
