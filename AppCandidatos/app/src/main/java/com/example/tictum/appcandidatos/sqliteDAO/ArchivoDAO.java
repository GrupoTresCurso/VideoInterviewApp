package com.example.tictum.appcandidatos.sqliteDAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.tictum.appcandidatos.parsers.JsonEntrevistaParser;

public class ArchivoDAO {

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

}
