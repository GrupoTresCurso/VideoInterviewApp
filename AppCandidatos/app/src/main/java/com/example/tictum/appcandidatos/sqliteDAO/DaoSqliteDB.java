package com.example.tictum.appcandidatos.sqliteDAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.tictum.appcandidatos.beans.Entrevista;
import com.example.tictum.appcandidatos.beans.Formulario;
import com.example.tictum.appcandidatos.beans.Pregunta;
import com.example.tictum.appcandidatos.beans.Video;
import com.example.tictum.appcandidatos.parsers.JsonEntrevistaParser;

import java.util.ArrayList;

public class DaoSqliteDB {

    // Creamos un objeto jsonParser que es el que va a traer json convertidos en objetos
    JsonEntrevistaParser jsonParser = new JsonEntrevistaParser();
    // Obtenemos el objeto entrevista a partir de un metodo
    // Entrevista entrevista = (Entrevista)jsonParser.getBeanById();

    // debemos crear una version para la base de datos para futuros cambios
    private static final int VERSION = 1;

    // nombre de la base de datos donde vamos a tener todas las tablas
    private static final String NOMBRE_BBDD = "tictalent";

    // constantes para las tablas que estan en la base de datos
    private static final String TABLA_FORMULARIO = "tabla_formulario";
    private static final String TABLA_ENTREVISTA = "tabla_entrevista";
    private static final String TABLA_PREGUNTA = "tabla_pregunta";
    private static final String TABLA_VIDEO = "tabla_video";
    private static final String TABLA_CANDIDATO = "tabla_candidato";
    private static final String TABLA_ENTREVISTA_FORMULARIO = "tabla_entrevista_formulario";
    private static final String TABLA_FORMULARIO_PREGUNTA = "tabla_formulario_pregunta";
    private static final String TABLA_ENTREVISTA_VIDEO = "tabla_entrevista_video";
    private static final String TABLA_ENTREVISTA_CANDIDATO = "tabla_entrevista_candidato";

    // atributos necesarios para usar en esta clase
    private SQLiteDatabase db;
    private DaoSqlite daoSqlite;

    // constructor para usar la base de datos que tenemos creada con sus tablas
    public DaoSqliteDB(Context context) {
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

}
