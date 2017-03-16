package com.example.tictum.appcandidatos.sqliteDAO;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.tictum.appcandidatos.beans.Candidato;
import com.example.tictum.appcandidatos.beans.Entrevista;
import com.example.tictum.appcandidatos.beans.Formulario;
import com.example.tictum.appcandidatos.beans.Video;
import com.example.tictum.appcandidatos.parsers.JsonEntrevistaParser;

import java.util.ArrayList;
import java.util.List;

public class EntrevistaDAO {

    public EntrevistaDAO() {
    }

    // Creamos un objeto jsonParser que es el que va a traer json convertidos en objetos
    JsonEntrevistaParser jsonParser = new JsonEntrevistaParser();
    // Obtenemos el objeto entrevista a partir de un metodo
    // Entrevista entrevista = (Entrevista)jsonParser.getBeanById();

    // debemos crear una version para la base de datos para futuros cambios
    private static final int VERSION = 1;

    // nombre de la base de datos donde vamos a tener todas las tablas
    private static final String NOMBRE_BBDD = "tictalent";

    private static final String TABLA_ENTREVISTA = "tabla_entrevista";
    private static final String TABLA_ENTREVISTA_FORMULARIO = "tabla_entrevista_formulario";
    private static final String TABLA_ENTREVISTA_VIDEO = "tabla_entrevista_video";
    private static final String TABLA_ENTREVISTA_CANDIDATO = "tabla_entrevista_candidato";


    // atributos necesarios para usar en esta clase
    private SQLiteDatabase db;
    private SqliteDB sqliteDB;
    private FormularioDAO formularioDAO;

    // constructor para usar la base de datos que tenemos creada con sus tablas
    public EntrevistaDAO(Context context) {
        // Creamos objeto Sqlite con el respectivo constructor pasando el nombre de la base de datos y la version
        sqliteDB = new SqliteDB(context, NOMBRE_BBDD, null, VERSION);
        formularioDAO = new FormularioDAO();
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

        // true or false
        boolean tof;
        if (cursor.getInt(3) == 1){ // Si contiene un 1 devolvemos true
            tof = true;
        } else { // si no devolvemos false
            tof = false;
        }

        if (cursor.moveToFirst()){
            entrevista.setIdEntrevista(cursor.getInt(0));
            entrevista.setNombreEntrevista(cursor.getString(1));
            entrevista.setNombrePuesto(cursor.getString(2));
            entrevista.setTieneVideoIntro(tof);
            entrevista.setCuestionarioSatisfaccion(formularioDAO.getFormulario(cursor.getInt(4)));
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
        //  creamos la lista donde vamos a tener todos los objetos entrevista
        ArrayList<Entrevista> listaEntrevistas = new ArrayList<Entrevista>();
        // mientras que haya resultados en el cursor los convertimos en objetos entrevista
        while (cursor.moveToNext()){
            Entrevista entrevista = new Entrevista();
            entrevista.setIdEntrevista(cursor.getInt(0));
            entrevista.setNombreEntrevista(cursor.getString(1));
            entrevista.setNombrePuesto(cursor.getString(2));

            boolean tof;
            if (cursor.getInt(3) == 1){ // Si contiene un 1 devolvemos true
                tof = true;
            } else { // si no devolvemos false
                tof = false;
            }
            entrevista.setTieneVideoIntro(tof);
            entrevista.setCuestionarioSatisfaccion(formularioDAO.getFormulario(cursor.getInt(4)));
            entrevista.setMensaje(cursor.getString(5));
            listaEntrevistas.add(entrevista);
        }
        cursor.close();
        // devolvemos la lista
        return listaEntrevistas;
    }

    public long insertEntrevista_Formulario(Entrevista entrevista){
        ContentValues nuevaEntrevistaFormulario = new ContentValues();
        List<Formulario> listaFormularios = entrevista.getFormularios();
        for (Formulario formulario : listaFormularios) {
            nuevaEntrevistaFormulario.put("idEntrevista",entrevista.getIdEntrevista());
            nuevaEntrevistaFormulario.put("idFormulario",formulario.getIdFormulario());
        }
        return db.insert(TABLA_ENTREVISTA_FORMULARIO, null, nuevaEntrevistaFormulario);
    }

    public  long insertEntrevista_Video(Entrevista entrevista){
        ContentValues nuevaEntrevistaVideo = new ContentValues();
        List<Video> listaVideos = entrevista.getListaVideos();
        for (Video video : listaVideos) {
            nuevaEntrevistaVideo.put("idEntrevista",entrevista.getIdEntrevista());
            nuevaEntrevistaVideo.put("idVideo",video.getIdVideo());
        }
        return db.insert(TABLA_ENTREVISTA_VIDEO, null, nuevaEntrevistaVideo);
    }

    public long insertEntrevistaCandidato(Entrevista entrevista){
        ContentValues nuevaEntrevistaCandidato = new ContentValues();
        List<Candidato> listaCandidatos = entrevista.getListaCandidatos();
        for (Candidato candidato : listaCandidatos) {
            nuevaEntrevistaCandidato.put("idEntrevista",entrevista.getIdEntrevista());
            nuevaEntrevistaCandidato.put("idCandidato",candidato.getIdCandidato());
        }
        return db.insert(TABLA_ENTREVISTA_CANDIDATO, null, nuevaEntrevistaCandidato);

    }

}
