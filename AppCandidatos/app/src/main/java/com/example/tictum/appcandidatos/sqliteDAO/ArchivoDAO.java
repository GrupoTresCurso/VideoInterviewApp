package com.example.tictum.appcandidatos.sqliteDAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.tictum.appcandidatos.beans.Archivo;
import com.example.tictum.appcandidatos.parsers.JsonEntrevistaParser;

import java.util.ArrayList;

public class ArchivoDAO {

    public ArchivoDAO() {
    }

    // Creamos un objeto jsonParser que es el que va a traer json convertidos en objetos
    JsonEntrevistaParser jsonParser = new JsonEntrevistaParser();


    // debemos crear una version para la base de datos para futuros cambios
    private static final int VERSION = 1;

    // nombre de la base de datos donde vamos a tener todas las tablas
    private static final String NOMBRE_BBDD = "tictalent";

    private static final String TABLA_ARCHIVO = "tabla_archivo";

    // atributos necesarios para usar en esta clase
    private SQLiteDatabase db;
    private SqliteDB sqliteDB;

    // constructor para usar la base de datos que tenemos creada con sus tablas
    public ArchivoDAO(Context context) {
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

    public long insertArchivo(Archivo archivo){
        ContentValues nuevoArchivo = new ContentValues();
        nuevoArchivo.put("idArchivo",archivo.getIdArchivo());
        nuevoArchivo.put("nombreArchivo",archivo.getNombreArchivo());
        nuevoArchivo.put("rutaArchivo",archivo.getRutaArchivo());
        return db.insert(TABLA_ARCHIVO, null, nuevoArchivo);
    }

    public Archivo getArchivo(int idArchivo){
        // Creamos un cursor que va a contener los resultados de la query en este caso solo obtendremos un resultado
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLA_ARCHIVO + " WHERE idArchivo = " + idArchivo, null);
        // si el cursor no devuelve resultados lo cerramos
        if (cursor.getCount() == 0){
            cursor.close();
            return  null;
        }

        Archivo archivo = new Archivo();

        if (cursor.moveToFirst()){
            archivo.setIdArchivo(cursor.getInt(0));
            archivo.setNombreArchivo(cursor.getString(1));
            archivo.setRutaArchivo(cursor.getString(2));
        }
        // cerramos cursor para que elimine lo que tiene
        cursor.close();
        return archivo;
    }

    public ArrayList<Archivo> getAllArchivos(){
        // obtenemos todos los registros de la tabla candidato
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLA_ARCHIVO, null);
        // si el cursor no devuelve resultados lo cerramos
        if (cursor.getCount() == 0){
            cursor.close();
            return  null;
        }

        ArrayList<Archivo> listaArchivos = new ArrayList<Archivo>();

        while (cursor.moveToNext()){
            Archivo archivo = new Archivo();
            archivo.setIdArchivo(cursor.getInt(0));
            archivo.setNombreArchivo(cursor.getString(1));
            archivo.setRutaArchivo(cursor.getString(2));
            listaArchivos.add(archivo);
        }
        cursor.close();
        return listaArchivos;
    }

}
