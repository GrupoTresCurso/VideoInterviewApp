package com.example.tictum.appcandidatos.parsers;

import com.example.tictum.appcandidatos.beans.Entrevista;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;



public class GsonEntrevistaParser {
    public List leerFlujoJson(InputStream in) throws IOException {
        Gson gson = new Gson();
        JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
        List entrevistas = new ArrayList();

        reader.beginArray();

        while (reader.hasNext()) {
            Entrevista animal = gson.fromJson(reader, Entrevista.class);
            entrevistas.add(animal);
        }


        reader.endArray();
        reader.close();
        return entrevistas;
    }
}
