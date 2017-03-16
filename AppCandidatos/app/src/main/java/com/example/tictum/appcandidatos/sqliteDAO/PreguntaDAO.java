package com.example.tictum.appcandidatos.sqliteDAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.tictum.appcandidatos.beans.Pregunta;
import com.example.tictum.appcandidatos.parsers.JsonEntrevistaParser;

import java.util.ArrayList;

public class PreguntaDAO {



    // Creamos un objeto jsonParser que es el que va a traer json convertidos en objetos
    JsonEntrevistaParser jsonParser = new JsonEntrevistaParser();

    // debemos crear una version para la base de datos para futuros cambios
    private static final int VERSION = 1;

    // nombre de la base de datos donde vamos a tener todas las tablas
    private static final String NOMBRE_BBDD = "tictalent";

    private static final String TABLA_PREGUNTA = "tabla_pregunta";

    // atributos necesarios para usar en esta clase
    private SQLiteDatabase db;
    private SqliteDB sqliteDB;

    public PreguntaDAO() {
    }

    // constructor para usar la base de datos que tenemos creada con sus tablas
    public PreguntaDAO(Context context) {
        // Creamos objeto Sqlite con el respectivo constructor pasando el nombre de la base de datos y la version
        sqliteDB = new SqliteDB(context, NOMBRE_BBDD, null, VERSION);
    }

    // metodo para obtener la base de datos
    public SQLiteDatabase getDB(){
        return db;
    }

    //metodo para poder escribir en la base de datos (insert,update,delete)
    public void openForWrite(){
        db = sqliteDB.getWritableDatabase();
    }

    // metodo para leer de la base de datos los registros que hay en ella
    public void openForRead(){
        db = sqliteDB.getReadableDatabase();
    }

    // metodo para cerrar la conexion con la base de datos
    public void close(){
        db.close();
    }

    // metodo de insercion de preguntas recibimos un objeto pregunta y lo insertamos en bd
    public long insertPregunta(Pregunta pregunta){
        ContentValues nuevaPregunta = new ContentValues();
        nuevaPregunta.put("idPregunta", pregunta.getIdPregunta());
        nuevaPregunta.put("labelPregunta",pregunta.getLabelPregunta());
        nuevaPregunta.put("tipoPregunta", pregunta.getTipoPregunta());
        nuevaPregunta.put("opciones", pregunta.getOpciones().toString());
        nuevaPregunta.put("posicionEnFormulario",pregunta.getPosicionEnFormulario());
        return db.insert(TABLA_PREGUNTA, null, nuevaPregunta);
    }

    // metodo para recuperar un formulario de la base de datos
    public Pregunta getPregunta(int idPregunta){
        // Creamos un cursor que va a contener los resultados de la query en este caso solo obtendremos un resultado
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLA_PREGUNTA + " WHERE idPregunta = " + idPregunta, null);
        // si el cursor no devuelve resultados lo cerramos
        if (cursor.getCount() == 0){
            cursor.close();
            return  null;
        }
        // Creamos objeto formulario que vamos a devolver posteriormente
        Pregunta pregunta = new Pregunta();
        // si el cursor contiene un resultado en este caso
        // cursor es un array donde en cada posicion esta cada campo de la tabla
        String[] arrayOpciones = cursor.getString(3).split(",");
        if (cursor.moveToFirst()){
            pregunta.setIdPregunta(cursor.getInt(0));
            pregunta.setLabelPregunta(cursor.getString(1));
            pregunta.setTipoPregunta(cursor.getString(2));
            pregunta.setOpciones(arrayOpciones);
            pregunta.setPosicionEnFormulario(cursor.getInt(4));
        }
        // cerramos cursor para que elimine lo que tiene
        cursor.close();
        // devolvemos objeto pregunta con los campos pertenecientes a su id
        return pregunta;
    }

    // metodo para obtener todos los objetos formulario de la base de datos
    public ArrayList<Pregunta> getAllPreguntas(){
        // obtenemos todos los registros de la tabla pregunta
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLA_PREGUNTA, null);
        // si el cursor no devuelve resultados lo cerramos
        if (cursor.getCount() == 0){
            cursor.close();
            return  null;
        }
        //  creamos la lista donde vamos a tener todos los objetos pregunta
        ArrayList<Pregunta> listaPreguntas = new ArrayList<Pregunta>();
        // mientras que haya resultados en el cursor los convertimos en objetos pregunta
        while (cursor.moveToNext()){
            Pregunta pregunta = new Pregunta();
            pregunta.setIdPregunta(cursor.getInt(0));
            pregunta.setLabelPregunta(cursor.getString(1));
            pregunta.setTipoPregunta(cursor.getString(2));
            String[] arrayOpciones = cursor.getString(3).split(",");
            pregunta.setOpciones(arrayOpciones);
            pregunta.setPosicionEnFormulario(cursor.getInt(4));
            listaPreguntas.add(pregunta);
        }
        cursor.close();
        // devolvemos la lista de preguntas
        return listaPreguntas;
    }

}
