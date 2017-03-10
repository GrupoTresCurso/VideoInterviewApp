package com.example.tictum.appcandidatos.daoSqlite;


import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.tictum.appcandidatos.parsers.JsonEntrevistaParser;

public class DaoSqlite extends SQLiteOpenHelper {

    // Creamos un objeto jsonParser que es el que va a traer json convertidos en objetos
    JsonEntrevistaParser jsonParser = new JsonEntrevistaParser();
    // Obtenemos el objeto entrevista a partir de un metodo
    // Entrevista entrevista = (Entrevista)jsonParser.getBeanById();

    // Constantes que creamos para la creacion de tabla entrevistas a traves de los atributos del objeto entrevista
    private static final String TABLA_ENTREVISTAS = "tabla_entrevistas";
    private static final String COL_ID_ENTREVISTA = "idEntrevista";
    private static final String COL_NOMBRE_ENTREVISTA = "nombreEntrevista";
    private static final String COL_NOMBRE_PUESTO = "nombrePuesto";
    private static final String COL_TIENE_VIDEO_INTRO = "tieneVideoIntro";
    private static final String COL_FORMULARIO = "cuestionarioSatisfaccion";
    private static final String COL_MENSAJE = "mensaje";

    private static final String tictalent = "CREATE TABLE " + TABLA_ENTREVISTAS + " ("
            + COL_ID_ENTREVISTA + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COL_NOMBRE_ENTREVISTA + "TEXT NOT NULL )";

    public DaoSqlite(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DaoSqlite(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }




}