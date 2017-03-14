package com.example.tictum.appcandidatos.sqliteDAO;


import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DaoSqlite extends SQLiteOpenHelper {

    // Constantes que creamos para la creacion de tabla formulario a traves de los atributos del objeto formulario
    private static final String TABLA_FORMULARIO = "tabla_formulario";
    private static final String COL_ID_FORMULARIO = "idFormulario";
    private static final String COL_NOMBRE_FORMULARIO = "nombreFormulario";
    private static final String COL_POS_EN_ENTREVISTA = "posicionEnEntrevista";

    // Creamos la tabla formulario
    private static final String TABLE_FORMULARIO = "CREATE TABLE " + TABLA_FORMULARIO + " ("
            + COL_ID_FORMULARIO + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COL_NOMBRE_FORMULARIO + "TEXT NOT NULL, "
            + COL_POS_EN_ENTREVISTA + "INTEGER"
            +");";

    // Constantes que creamos para la creacion de tabla entrevista a traves de los atributos del objeto entrevista
    private static final String TABLA_ENTREVISTA = "tabla_entrevista";
    private static final String COL_ID_ENTREVISTA = "idEntrevista";
    private static final String COL_NOMBRE_ENTREVISTA = "nombreEntrevista";
    private static final String COL_NOMBRE_PUESTO = "nombrePuesto";
    private static final String COL_TIENE_VIDEO_INTRO = "tieneVideoIntro";
    private static final String COL_FORMULARIO = "cuestionarioSatisfaccion";
    private static final String COL_MENSAJE = "mensaje";

    // creamos la tabla entrevista con su respectiva clave foranea
    private static final String TABLE_ENTREVISTA = "CREATE TABLE " + TABLA_ENTREVISTA + " ("
            + COL_ID_ENTREVISTA + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COL_NOMBRE_ENTREVISTA + "TEXT NOT NULL, "
            + COL_NOMBRE_PUESTO + "TEXT NOT NULL, "
            + COL_TIENE_VIDEO_INTRO + "INTEGER(1), "
            + COL_FORMULARIO + "INTEGER, "
            + COL_MENSAJE + "TEXT NOT NULL,"
            + "FOREIGN KEY("+ COL_FORMULARIO +") REFERENCES "+ TABLA_FORMULARIO +"("+ COL_ID_FORMULARIO +")"
            +");";

    // Constantes que creamos para la creacion de tabla pregunta a traves de los atributos del objeto pregunta
    private static final String TABLA_PREGUNTA = "tabla_pregunta";
    private static final String COL_ID_PREGUNTA = "idPregunta";
    private static final String COL_LABEL_PREGUNTA = "labelPregunta";
    private static final String COL_TIPO_PREGUNTA = "tipoPregunta";
    private static final String COL_OPCIONES = "opciones";
    private static final String COL_POS_FORMULARIO = "posicionEnFormulario";

    // Creamos la tabla pregunta
    private static final String TABLE_PREGUNTA = "CREATE TABLE " + TABLA_PREGUNTA + " ("
            + COL_ID_PREGUNTA + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COL_LABEL_PREGUNTA + "TEXT NOT NULL, "
            + COL_TIPO_PREGUNTA + "TEXT NOT NULL, "
            + COL_OPCIONES + "TEXT NOT NULL, "
            + COL_POS_FORMULARIO + "INTEGER"
            + ");";

    // Constantes que creamos para la creacion de tabla video a traves de los atributos del objeto video
    private static final String TABLA_VIDEO = "tabla_video";
    private static final String COL_ID_VIDEO = "idVideo";
    private static final String COL_NOMBRE_VIDEO = "nombreVideo";
    private static final String COL_LINK_VIDEO = "linkVideo";
    private static final String COL_POS_ENTREVISTA = "posicionEnEntrevista";
    private static final String COL_TIPO_VIDEO = "tipoVideo";

    // Creamos la tabla video
    private static final String TABLE_VIDEO = "CREATE TABLE " + TABLA_VIDEO + " ("
            + COL_ID_VIDEO + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COL_NOMBRE_VIDEO + "TEXT NOT NULL, "
            + COL_LINK_VIDEO + "TEXT NOT NULL, "
            + COL_POS_ENTREVISTA + "INTEGER, "
            + COL_TIPO_VIDEO + "TEXT NOT NULL"
            + ");";

    // Constantes que creamos para la creacion de tabla candidato a traves de los atributos del objeto candidato
    private static final String TABLA_CANDIDATO = "tabla_candidato";
    private static final String COL_ID_CANDIDATO = "idCandidato";
    private static final String COL_NOMBRE = "nombre";
    private static final String COL_APELLIDOS = "apellidos";
    private static final String COL_DNI = "dni";
    private static final String COL_EMAIL = "email";
    private static final String COL_EDAD = "edad";
    private static final String COL_IS_HOMBRE = "isHombre";
    private static final String COL_TELEFONO = "numeroTelefono";
    private static final String COL_RUTA_CURRICULUM = "rutaCurriculum";

    // Creamos la tabla candidato
    private static final String TABLE_CANDIDATO = "CREATE TABLE " + TABLA_CANDIDATO + " ("
            + COL_ID_CANDIDATO + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COL_NOMBRE + "TEXT NOT NULL, "
            + COL_APELLIDOS + "TEXT NOT NULL, "
            + COL_DNI + "TEXT NOT NULL, "
            + COL_EMAIL + "TEXT NOT NULL, "
            + COL_EDAD + "INTEGER NOT NULL, "
            + COL_IS_HOMBRE + "TEXT NOT NULL, "
            + COL_TELEFONO + "TEXT NOT NULL, "
            + COL_RUTA_CURRICULUM + "TEXT NOT NULL"
            + ");";

    // Constantes que creamos para la creacion de tabla Entrevista-Formulario
    private static final String TABLA_ENTREVISTA_FORMULARIO = "tabla_entrevista_formulario";
    private static final String COL_IDENTREVISTA = "idEntrevista";
    private static final String COL_IDFORMULARIO = "idFormulario";

    // Creamos la tabla Entrevista-Formulario
    private static final String TABLE_ENTREVISTA_FORMULARIO = "CREATE TABLE " + TABLA_ENTREVISTA_FORMULARIO + " ("
            + COL_IDENTREVISTA + " INTEGER NOT NULL, "
            + COL_IDFORMULARIO + " INTEGER NOT NULL, "
            + "FOREIGN KEY("+ COL_IDENTREVISTA +") REFERENCES "+ TABLA_ENTREVISTA +"("+ COL_ID_ENTREVISTA +"), "
            + "FOREIGN KEY("+ COL_IDFORMULARIO +") REFERENCES "+ TABLA_FORMULARIO +"("+ COL_ID_FORMULARIO +")"
            +");";

    // Constantes que creamos para la creacion de tabla Formulario_Pregunta
    private static final String TABLA_FORMULARIO_PREGUNTA = "tabla_formulario_pregunta";
    private static final String COL_ID_FORM = "idFormulario";
    private static final String COL_IDPREGUNTA = "idPregunta";

    // Creamos la tabla Formulario_Pregunta
    private static final String TABLE_FORMULARIO_PREGUNTA = "CREATE TABLE " + TABLA_FORMULARIO_PREGUNTA + " ("
            + COL_ID_FORM + " INTEGER NOT NULL, "
            + COL_IDPREGUNTA + " INTEGER NOT NULL, "
            + "FOREIGN KEY("+ COL_ID_FORM +") REFERENCES "+ TABLA_FORMULARIO +"("+ COL_ID_FORMULARIO +"), "
            + "FOREIGN KEY("+ COL_IDPREGUNTA +") REFERENCES "+ TABLA_PREGUNTA +"("+ COL_ID_PREGUNTA +")"
            +");";

    // Constantes que creamos para la creacion de tabla Entrevista-Video
    private static final String TABLA_ENTREVISTA_VIDEO = "tabla_entrevista_video";
    private static final String COL_ID_ENTREVIST = "idEntrevista";
    private static final String COL_IDVIDEO = "idVideo";

    // Creamos la tabla Entrevista-Video
    private static final String TABLE_ENTREVISTA_VIDEO = "CREATE TABLE " + TABLA_ENTREVISTA_VIDEO + " ("
            + COL_ID_ENTREVIST + " INTEGER NOT NULL, "
            + COL_IDVIDEO + " INTEGER NOT NULL, "
            + "FOREIGN KEY("+ COL_ID_ENTREVIST +") REFERENCES "+ TABLA_ENTREVISTA +"("+ COL_ID_ENTREVISTA +"), "
            + "FOREIGN KEY("+ COL_IDVIDEO +") REFERENCES "+ TABLA_VIDEO +"("+ COL_ID_VIDEO +")"
            +");";

    // Constantes que creamos para la creacion de tabla Entrevista-Candidato
    private static final String TABLA_ENTREVISTA_CANDIDATO = "tabla_entrevista_candidato";
    private static final String COL_ID_NTREVIST = "idEntrevista";
    private static final String COL_IDCANDIDATO = "idCandidato";

    // Creamos la tabla Entrevista-Candidato
    private static final String TABLE_ENTREVISTA_CANDIDATO = "CREATE TABLE " + TABLA_ENTREVISTA_CANDIDATO + " ("
            + COL_ID_NTREVIST + " INTEGER NOT NULL, "
            + COL_IDCANDIDATO + " INTEGER NOT NULL, "
            + "FOREIGN KEY("+ COL_ID_NTREVIST +") REFERENCES "+ TABLA_ENTREVISTA +"("+ COL_ID_ENTREVISTA +"), "
            + "FOREIGN KEY("+ COL_IDCANDIDATO +") REFERENCES "+ TABLA_CANDIDATO +"("+ COL_ID_CANDIDATO +")"
            +");";

    // contructores por defecto de la clase DaoSqlite
    public DaoSqlite(Context context, String nameDB, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, nameDB, factory, version);
    }

    public DaoSqlite(Context context, String nameDB, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, nameDB, factory, version, errorHandler);
    }

    // Para la creacion de las tablas
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_FORMULARIO);
        db.execSQL(TABLE_ENTREVISTA);
        db.execSQL(TABLE_PREGUNTA);
        db.execSQL(TABLE_VIDEO);
        db.execSQL(TABLE_CANDIDATO);
        db.execSQL(TABLE_ENTREVISTA_FORMULARIO);
        db.execSQL(TABLE_FORMULARIO_PREGUNTA);
        db.execSQL(TABLE_ENTREVISTA_VIDEO);
        db.execSQL(TABLE_ENTREVISTA_CANDIDATO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}