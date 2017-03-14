package com.example.tictum.appcandidatos.daoSqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.tictum.appcandidatos.beans.Entrevista;
import com.example.tictum.appcandidatos.beans.Formulario;
import com.example.tictum.appcandidatos.beans.Pregunta;
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
            entrevista.setCuestionarioSatisfaccion(getFormulario(cursor.getInt(4)));
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
            entrevista.setCuestionarioSatisfaccion(getFormulario(cursor.getInt(4)));
            entrevista.setMensaje(cursor.getString(5));
            listaEntrevistas.add(entrevista);
        }
        cursor.close();
        // devolvemos la lista de formularios
        return listaEntrevistas;
    }
    // metodo de insercion de formularios recibimos un objeto formulario y lo insertamos en bd
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
        // obtenemos todos los registros de la tabla formulario
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
        // devolvemos la lista de formularios
        return listaPreguntas;
    }




}
