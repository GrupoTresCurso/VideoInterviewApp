package com.example.tictum.appcandidatos.sqliteDAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.tictum.appcandidatos.beans.Candidato;
import com.example.tictum.appcandidatos.parsers.JsonEntrevistaParser;

import java.util.ArrayList;

public class CandidatoDAO {

    public CandidatoDAO() {
    }

    // Creamos un objeto jsonParser que es el que va a traer json convertidos en objetos
    JsonEntrevistaParser jsonParser = new JsonEntrevistaParser();
    // Obtenemos el objeto entrevista a partir de un metodo

    // debemos crear una version para la base de datos para futuros cambios
    private static final int VERSION = 1;

    // nombre de la base de datos donde vamos a tener todas las tablas
    private static final String NOMBRE_BBDD = "tictalent";

    private static final String TABLA_CANDIDATO = "tabla_candidato";

    // atributos necesarios para usar en esta clase
    private SQLiteDatabase db;
    private SqliteDB sqliteDB;

    // constructor para usar la base de datos que tenemos creada con sus tablas
    public CandidatoDAO(Context context) {
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

    public long insertCandidato(Candidato candidato){

        ContentValues nuevoCandidato = new ContentValues();
        nuevoCandidato.put("idCandidato",candidato.getIdCandidato());
        nuevoCandidato.put("nombre",candidato.getNombre());
        nuevoCandidato.put("apellidos",candidato.getApellidos());
        nuevoCandidato.put("dni",candidato.getDni());
        nuevoCandidato.put("email",candidato.getEmail());
        nuevoCandidato.put("edad",candidato.getEdad());
        nuevoCandidato.put("isHombre",candidato.getSexo());
        nuevoCandidato.put("telefono",candidato.getNumeroTelefono());
        nuevoCandidato.put("rutaCurriculum",candidato.getRutaCurriculum());
        return db.insert(TABLA_CANDIDATO, null, nuevoCandidato);
    }

    public Candidato getCandidato(int idCandidato){
        // Creamos un cursor que va a contener los resultados de la query en este caso solo obtendremos un resultado
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLA_CANDIDATO + " WHERE idEntrevista = " + idCandidato, null);
        // si el cursor no devuelve resultados lo cerramos
        if (cursor.getCount() == 0){
            cursor.close();
            return  null;
        }

        Candidato candidato = new Candidato();

        if (cursor.moveToFirst()){
            candidato.setIdCandidato(cursor.getInt(0));
            candidato.setNombre(cursor.getString(1));
            candidato.setApellidos(cursor.getString(2));
            candidato.setDni(cursor.getString(3));
            candidato.setEmail(cursor.getString(4));
            candidato.setEdad(cursor.getInt(5));
            candidato.setSexo(cursor.getString(6));
            candidato.setNumeroTelefono(cursor.getString(7));
            candidato.setRutaCurriculum(cursor.getString(8));
        }
        // cerramos cursor para que elimine lo que tiene
        cursor.close();
        return candidato;
    }

    public ArrayList<Candidato> getAllCandidatos(){
        // obtenemos todos los registros de la tabla candidato
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLA_CANDIDATO, null);
        // si el cursor no devuelve resultados lo cerramos
        if (cursor.getCount() == 0){
            cursor.close();
            return  null;
        }
        ArrayList<Candidato> listaCandidatos = new ArrayList<Candidato>();
        while (cursor.moveToNext()){
            Candidato candidato = new Candidato();
            candidato.setIdCandidato(cursor.getInt(0));
            candidato.setNombre(cursor.getString(1));
            candidato.setApellidos(cursor.getString(2));
            candidato.setDni(cursor.getString(3));
            candidato.setEmail(cursor.getString(4));
            candidato.setEdad(cursor.getInt(5));
            candidato.setSexo(cursor.getString(6));
            candidato.setNumeroTelefono(cursor.getString(7));
            candidato.setRutaCurriculum(cursor.getString(8));
            listaCandidatos.add(candidato);
        }
        cursor.close();
        return listaCandidatos;
    }
}
