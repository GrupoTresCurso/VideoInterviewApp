package com.example.tictum.appcandidatos.sqliteDAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.tictum.appcandidatos.beans.Respuesta;
import com.example.tictum.appcandidatos.parsers.JsonEntrevistaParser;

import java.util.ArrayList;

public class RespuestaDAO {

    // Creamos un objeto jsonParser que es el que va a traer json convertidos en objetos
    JsonEntrevistaParser jsonParser = new JsonEntrevistaParser();

    // debemos crear una version para la base de datos para futuros cambios
    private static final int VERSION = 1;

    // nombre de la base de datos donde vamos a tener todas las tablas
    private static final String NOMBRE_BBDD = "tictalent";

    private static final String TABLA_RESPUESTA = "tabla_respuesta";
    private static final String TABLA_RESPUESTA_VIDEO = "tabla_respuesta_video";
    private static final String TABLA_RESPUESTA_ARCHIVO = "tabla_respuesta_archivo";

    // atributos necesarios para usar en esta clase
    private SQLiteDatabase db;
    private SqliteDB sqliteDB;

    // constructor para usar la base de datos que tenemos creada con sus tablas
    public RespuestaDAO(Context context) {
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

    public long insertRespuesta(Respuesta respuesta){
        ContentValues nuevaRespuesta = new ContentValues();
        nuevaRespuesta.put("idRespuesta",respuesta.getIdRespuesta());
        nuevaRespuesta.put("idEntrevista",respuesta.getIdEntrevista());
        nuevaRespuesta.put("idCandidato",respuesta.getIdCandidato());
        nuevaRespuesta.put("notaCandidato",Float.toString(respuesta.getNotaCandidato()));
        nuevaRespuesta.put("respuestas",respuesta.getRespuestas().toString());
        return db.insert(TABLA_RESPUESTA, null, nuevaRespuesta);
    }

    public Respuesta getRespuesta(int idRespuesta){
        // Creamos un cursor que va a contener los resultados de la query en este caso solo obtendremos un resultado
            Cursor cursor = db.rawQuery("SELECT * FROM " + TABLA_RESPUESTA + " WHERE idRespuesta = " + idRespuesta, null);
        // si el cursor no devuelve resultados lo cerramos
        if (cursor.getCount() == 0){
            cursor.close();
            return  null;
        }
        Respuesta respuesta = new Respuesta();
        String[] arrayRespuestas = cursor.getString(4).split(",");
        if (cursor.moveToFirst()){
            respuesta.setIdRespuesta(cursor.getInt(0));
            respuesta.setIdEntrevista(cursor.getInt(1));
            respuesta.setIdCandidato(cursor.getInt(2));
            respuesta.setNotaCandidato(Float.parseFloat(cursor.getString(3)));
            respuesta.setRespuestas(arrayRespuestas);
        }
        // cerramos cursor para que elimine lo que tiene
        cursor.close();
        return respuesta;
    }

    public ArrayList<Respuesta> getAllRespuestas(){
        // obtenemos todos los registros de la tabla entrevista
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLA_RESPUESTA, null);
        // si el cursor no devuelve resultados lo cerramos
        if (cursor.getCount() == 0){
            cursor.close();
            return  null;
        }
        //  creamos la lista donde vamos a tener todos los objetos formularios
        ArrayList<Respuesta> listaRespuestas = new ArrayList<Respuesta>();
        // mientras que haya resultados en el cursor los convertimos en objetos formulario
        while (cursor.moveToNext()){
            Respuesta respuesta = new Respuesta();
            respuesta.setIdRespuesta(cursor.getInt(0));
            respuesta.setIdEntrevista(cursor.getInt(1));
            respuesta.setIdCandidato(cursor.getInt(2));
            respuesta.setNotaCandidato(Float.parseFloat(cursor.getString(3)));
            String[] arrayRespuestas = cursor.getString(4).split(",");
            respuesta.setRespuestas(arrayRespuestas);
            listaRespuestas.add(respuesta);
        }
        cursor.close();
        // devolvemos la lista de formularios
        return listaRespuestas;

    }

}
