package com.example.tictum.appcandidatos.sqliteDAO;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.tictum.appcandidatos.beans.Entrevista;
import com.example.tictum.appcandidatos.beans.Formulario;
import com.example.tictum.appcandidatos.parsers.JsonEntrevistaParser;

import java.util.ArrayList;

public class EntrevistaDAO {

    // Creamos un objeto jsonParser que es el que va a traer json convertidos en objetos
    JsonEntrevistaParser jsonParser = new JsonEntrevistaParser();
    // Obtenemos el objeto entrevista a partir de un metodo
    // Entrevista entrevista = (Entrevista)jsonParser.getBeanById();

    // debemos crear una version para la base de datos para futuros cambios
    private static final int VERSION = 1;

    // nombre de la base de datos donde vamos a tener todas las tablas
    private static final String NOMBRE_BBDD = "tictalent";

    private static final String TABLA_ENTREVISTA = "tabla_entrevista";

    // atributos necesarios para usar en esta clase
    private SQLiteDatabase db;
    private DaoSqlite daoSqlite;
    private FormularioDAO formulario;

    // constructor para usar la base de datos que tenemos creada con sus tablas
    public EntrevistaDAO(Context context) {
        // Creamos objeto Sqlite con el respectivo constructor pasando el nombre de la base de datos y la version
        daoSqlite = new DaoSqlite(context, NOMBRE_BBDD, null, VERSION);
    formulario = new FormularioDAO();
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

    public long insertEntrevista(Entrevista entrevista){

        int ToF;
        if (entrevista.TieneVideoIntro()){ // Si contiene un true guardamos 1 en base de datos
            ToF = 1;
        } else { // si no guardamos un 0
            ToF = 0;
        }

        ContentValues nuevaEntrevista = new ContentValues();
        // obtenemos formulario que viene con objeto entrevista para guardar su id
        Formulario formulario = entrevista.getCuestionarioSatisfaccion();
        nuevaEntrevista.put("idEntrevista",entrevista.getIdEntrevista());
        nuevaEntrevista.put("nombreEntrevista",entrevista.getNombreEntrevista());
        nuevaEntrevista.put("nombrePuesto",entrevista.getNombrePuesto());
        nuevaEntrevista.put("tieneVideoIntro",ToF);
        nuevaEntrevista.put("cuestionarioSatisfaccion", formulario.getIdFormulario());
        nuevaEntrevista.put("mensaje",entrevista.getMensaje());
        return db.insert(TABLA_ENTREVISTA, null, nuevaEntrevista);
    }

    // metodo para recuperar una Entrevista de la base de datos
    public Entrevista getEntrevista(int idEntrevista){
        // Creamos un cursor que va a contener los resultados de la query en este caso solo obtendremos un resultado
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLA_ENTREVISTA + " WHERE idEntrevista = " + idEntrevista, null);
        // si el cursor no devuelve resultados lo cerramos
        if (cursor.getCount() == 0){
            cursor.close();
            return  null;
        }
        // Creamos objeto formulario que vamos a devolver posteriormente
        Entrevista entrevista = new Entrevista();
        // si el cursor contiene un resultado en este caso
        // cursor es un array donde en cada posicion esta cada campo de la tabla

        boolean ToF;
        if (cursor.getInt(3) == 1){ // Si contiene un 1 devolvemos true
            ToF = true;
        } else { // si no devolvemos false
            ToF = false;
        }

        if (cursor.moveToFirst()){
            entrevista.setIdEntrevista(cursor.getInt(0));
            entrevista.setNombreEntrevista(cursor.getString(1));
            entrevista.setNombrePuesto(cursor.getString(2));
            entrevista.setTieneVideoIntro(ToF);
            entrevista.setCuestionarioSatisfaccion(formulario.getFormulario(cursor.getInt(4)));
            entrevista.setMensaje(cursor.getString(5));
        }
        // cerramos cursor para que elimine lo que tiene
        cursor.close();
        // devolvemos objeto formulario con los campos pertenecientes a su id
        return entrevista;
    }

    public ArrayList<Entrevista> getAllEntrevistas(){
        // obtenemos todos los registros de la tabla entrevista
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLA_ENTREVISTA, null);
        // si el cursor no devuelve resultados lo cerramos
        if (cursor.getCount() == 0){
            cursor.close();
            return  null;
        }
        //  creamos la lista donde vamos a tener todos los objetos formularios
        ArrayList<Entrevista> listaEntrevistas = new ArrayList<Entrevista>();
        // mientras que haya resultados en el cursor los convertimos en objetos formulario
        while (cursor.moveToNext()){
            Entrevista entrevista = new Entrevista();
            entrevista.setIdEntrevista(cursor.getInt(0));
            entrevista.setNombreEntrevista(cursor.getString(1));
            entrevista.setNombrePuesto(cursor.getString(2));

            boolean ToF;
            if (cursor.getInt(3) == 1){ // Si contiene un 1 devolvemos true
                ToF = true;
            } else { // si no devolvemos false
                ToF = false;
            }
            entrevista.setTieneVideoIntro(ToF);
            entrevista.setCuestionarioSatisfaccion(formulario.getFormulario(cursor.getInt(4)));
            entrevista.setMensaje(cursor.getString(5));
            listaEntrevistas.add(entrevista);
        }
        cursor.close();
        // devolvemos la lista de formularios
        return listaEntrevistas;
    }
}
