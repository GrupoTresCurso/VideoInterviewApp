package com.example.tictum.appcandidatos.sqliteDAO;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.tictum.appcandidatos.beans.Formulario;
import com.example.tictum.appcandidatos.beans.Pregunta;
import com.example.tictum.appcandidatos.parsers.JsonEntrevistaParser;

import java.util.ArrayList;
import java.util.List;

public class FormularioDAO {



    // Creamos un objeto jsonParser que es el que va a traer json convertidos en objetos
    JsonEntrevistaParser jsonParser = new JsonEntrevistaParser();

    // debemos crear una version para la base de datos para futuros cambios
    private static final int VERSION = 1;

    // nombre de la base de datos donde vamos a tener todas las tablas
    private static final String NOMBRE_BBDD = "tictalent";

    private static final String TABLA_FORMULARIO = "tabla_formulario";
    private static final String TABLA_FORMULARIO_PREGUNTA = "tabla_formulario_pregunta";

    // atributos necesarios para usar en esta clase
    private SQLiteDatabase db;
    private SqliteDB sqliteDB;
    private PreguntaDAO preguntaDAO;


    public FormularioDAO() {
    }

    // constructor para usar la base de datos que tenemos creada con sus tablas
    public FormularioDAO(Context context) {
        // Creamos objeto Sqlite con el respectivo constructor pasando el nombre de la base de datos y la version
        sqliteDB = new SqliteDB(context, NOMBRE_BBDD, null, VERSION);
        preguntaDAO = new PreguntaDAO();
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

    // metodo de insercion de formularios recibimos un objeto formulario y lo insertamos en bd
    public long insertFormulario(Formulario formulario){
        ContentValues nuevoFormulario = new ContentValues();
        nuevoFormulario.put("idFormulario",formulario.getIdFormulario());
        nuevoFormulario.put("nombreFormulario",formulario.getNombreFormulario());
        nuevoFormulario.put("posicionEnEntrevista",formulario.getPosicionEnEntrevista());
        return db.insert(TABLA_FORMULARIO, null, nuevoFormulario);
    }

    // metodo para recuperar un formulario de la base de datos
    public Formulario getFormulario(int idFormulario){
        // Creamos un cursor que va a contener los resultados de la query en este caso solo obtendremos un resultado
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLA_FORMULARIO + " WHERE idFormulario = " + idFormulario, null);
        // si el cursor no devuelve resultados lo cerramos
        if (cursor.getCount() == 0){
            cursor.close();
            return  null;
        }
        // Creamos objeto formulario que vamos a devolver posteriormente
        Formulario formulario = new Formulario();
        // si el cursor contiene un resultado en este caso
        // cursor es un array donde en cada posicion esta cada campo de la tabla
        if (cursor.moveToFirst()){
            formulario.setIdFormulario(cursor.getInt(0));
            formulario.setNombreFormulario(cursor.getString(1));
            formulario.setPosicionEnEntrevista(cursor.getInt(2));
        }
        // cerramos cursor para que elimine lo que tiene
        cursor.close();
        // devolvemos objeto formulario con los campos pertenecientes a su id
        return formulario;
    }

    // metodo para obtener todos los objetos formulario de la base de datos
    public ArrayList<Formulario> getAllFormularios(){
        // obtenemos todos los registros de la tabla formulario
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLA_FORMULARIO, null);
        // si el cursor no devuelve resultados lo cerramos
        if (cursor.getCount() == 0){
            cursor.close();
            return  null;
        }
        //  creamos la lista donde vamos a tener todos los objetos formularios
        ArrayList<Formulario> listaFormularios = new ArrayList<Formulario>();
        // mientras que haya resultados en el cursor los convertimos en objetos formulario
        while (cursor.moveToNext()){
            Formulario formulario = new Formulario();
            formulario.setIdFormulario(cursor.getInt(0));
            formulario.setNombreFormulario(cursor.getString(1));
            formulario.setPosicionEnEntrevista(cursor.getInt(2));
            listaFormularios.add(formulario);
        }
        cursor.close();
        // devolvemos la lista de formularios
        return listaFormularios;
    }

    // metodo de insercion de formularios recibimos un objeto formulario y lo insertamos en bd
    public long insertFormulario_Pregunta(Formulario formulario){
        ContentValues nuevoFormulario_Pregunta = new ContentValues();
        List<Pregunta> listaPreguntas = formulario.getPreguntas();
        // recorremos las preguntas y solo guardamos su id
        for (Pregunta pregunta: listaPreguntas) {
            nuevoFormulario_Pregunta.put("idFormulario",formulario.getIdFormulario());
            nuevoFormulario_Pregunta.put("idPregunta",pregunta.getIdPregunta());
        }
        return db.insert(TABLA_FORMULARIO_PREGUNTA, null, nuevoFormulario_Pregunta);
    }

    // metodo para recuperar un formulario de la base de datos
    public ArrayList<Pregunta> getPreguntas_Formulario(int idFormulario){
        // Creamos un cursor que va a contener los resultados de la query
        Cursor cursor = db.rawQuery("SELECT idPregunta FROM " + TABLA_FORMULARIO_PREGUNTA + " WHERE idFormulario = " + idFormulario, null);
        // si el cursor no devuelve resultados lo cerramos
        if (cursor.getCount() == 0){
            cursor.close();
            return  null;
        }
        // Creamos que vamos a devolver posteriormente
        ArrayList<Pregunta> listaPreguntas = new ArrayList<Pregunta>();
        // si el cursor contiene un resultado en este caso
        // cursor es un array donde en cada posicion esta cada campo de la tabla
        while (cursor.moveToNext()){
            Pregunta pregunta = preguntaDAO.getPregunta(cursor.getInt(0));
            listaPreguntas.add(pregunta);
        }
        // cerramos cursor para que elimine lo que tiene
        cursor.close();
        // devolvemos listaPreguntas de un Formulario
        return listaPreguntas;
    }

}
