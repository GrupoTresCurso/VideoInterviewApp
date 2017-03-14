package com.example.tictum.appcandidatos.sqliteDAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.tictum.appcandidatos.beans.Video;
import com.example.tictum.appcandidatos.parsers.JsonEntrevistaParser;

public class VideoDAO {

    // Creamos un objeto jsonParser que es el que va a traer json convertidos en objetos
    JsonEntrevistaParser jsonParser = new JsonEntrevistaParser();

    // debemos crear una version para la base de datos para futuros cambios
    private static final int VERSION = 1;

    // nombre de la base de datos donde vamos a tener todas las tablas
    private static final String NOMBRE_BBDD = "tictalent";

    private static final String TABLA_VIDEO = "tabla_video";

    // atributos necesarios para usar en esta clase
    private SQLiteDatabase db;
    private DaoSqlite daoSqlite;

    // constructor para usar la base de datos que tenemos creada con sus tablas
    public VideoDAO(Context context) {
        // Creamos objeto Sqlite con el respectivo constructor pasando el nombre de la base de datos y la version
        daoSqlite = new DaoSqlite(context, NOMBRE_BBDD, null, VERSION);
    }

    // metodo para obtener la base de datos
    public SQLiteDatabase getDB(){
        return db;
    }

    //metodo para poder escribir en la base de datos (insert,update,delete)
    public void openForWrite(){
        db = daoSqlite.getWritableDatabase();
    }

    // metodo para leer de la base de datos los registros que hay en ella
    public void openForRead(){
        db = daoSqlite.getReadableDatabase();
    }

    // metodo para cerrar la conexion con la base de datos
    public void close(){
        db.close();
    }

    // metodo para recuperar un video de la base de datos
    public Video getVideo(int idVideo){
        // Creamos un cursor que va a contener los resultados de la query en este caso solo obtendremos un resultado
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLA_VIDEO + " WHERE idVideo = " + idVideo, null);
        // si el cursor no devuelve resultados lo cerramos
        if (cursor.getCount() == 0){
            cursor.close();
            return  null;
        }
        // Creamos objeto video que vamos a devolver posteriormente
        Video video = new Video();
        // si el cursor contiene un resultado en este caso
        // cursor es un array donde en cada posicion esta cada campo de la tabla
        if (cursor.moveToFirst()){
            video.setIdVideo(cursor.getInt(0));
            video.setNombreVideo(cursor.getString(1));
            video.setPosicionEnEntrevista(cursor.getInt(2));
        }
        // cerramos cursor para que elimine lo que tiene
        cursor.close();
        // devolvemos objeto video con los campos pertenecientes a su id
        return video;
    }

}
