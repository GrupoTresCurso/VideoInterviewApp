package com.example.tictum.appcandidatos.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.tictum.appcandidatos.R;
import com.example.tictum.appcandidatos.beans.Entrevista;
import com.example.tictum.appcandidatos.beans.Formulario;
import com.example.tictum.appcandidatos.beans.Pregunta;
import com.example.tictum.appcandidatos.beans.Respuesta;
import com.example.tictum.appcandidatos.beans.Video;

import java.io.File;

public class Activity_GrabarRespuesta extends AppCompatActivity {

    // se crean para usar en los permisos
    private final int REQUEST_WRITE_EXTERNAL_STORAGE = 1;
    private final int REQUEST_CAMERA = 2;
    private final int VIDEO_CAMERA = 3;
    private final int VIDEO_CAPTURE = 4;

    //datos del video creado
    private Video videoCreado;
    private File linkVideo;


    // boolean para ver si tengo o no permiso
    boolean tengoPermisoCamara = false;
    boolean tengoPermisoEscritura = false;

    //elementos intent
    private Intent intent;
    private Entrevista entrevista;
    private Respuesta respuesta;
    private int numeroPreguntasVideo;
    private int numeroPreguntaVideo;
    private boolean isUltimaPregunta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // setContentView(R.layout.layout_grabar_respuesta);

        //recogemos los extras del inten
        entrevista = (Entrevista)getIntent().getSerializableExtra("entrevista");
        respuesta = (Respuesta) getIntent().getSerializableExtra("respuesta");
        numeroPreguntaVideo = (int) getIntent().getSerializableExtra("numeroPreguntaVideo");
        numeroPreguntasVideo = (int) getIntent().getSerializableExtra("numeroPreguntasVideo");
        isUltimaPregunta = (boolean) getIntent().getSerializableExtra("isUltimaPregunta");


        checkPermissionCamara();
    }


    // funcion para ver si tengo permiso para usar la camara en nuestra app
    private boolean checkPermissionCamara(){

        int permisoCamara = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);

        // si no tengo permiso para acceder a la camara
        if (permisoCamara != PackageManager.PERMISSION_GRANTED) {
            // pido permiso solo si la version es igual o superior a la 6.0
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA);
                tengoPermisoCamara = true;
            }
            return tengoPermisoCamara;
            // si tengo permiso para acceder a la camara del dispositivo llamamos a la aplicacion de la camara
        }else {
            return tengoPermisoCamara;
        }
    }

    // funcion para ver si tengo permiso para usar la camara en nuestra app
    private boolean checkPermissionWrite() {

        int permisoEscritura = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        // si no tengo permiso para escribir en la memoria del telefono
        if (permisoEscritura != PackageManager.PERMISSION_GRANTED) {
            // pido permiso solo si la version es igual o superior a la 6.0
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.CAMERA}, REQUEST_WRITE_EXTERNAL_STORAGE);
                tengoPermisoEscritura = true;
            }
            return tengoPermisoEscritura;
        } else {
            // si no devuelvo false
            return tengoPermisoEscritura;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
            // si el permiso era de la camara
        if (REQUEST_CAMERA == requestCode) {
            // si el permiso es obtenido
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // llamar a la camara nativa del dispositivo android
                Intent intent = new Intent(MediaStore.INTENT_ACTION_VIDEO_CAMERA);
                startActivityForResult(intent, VIDEO_CAMERA);
            } else {
                // si el usuario no da los permisos terminamos la actividad
                finish();
            }
        }

        // si el permiso era de escribir en memoria del telefono
        if (REQUEST_WRITE_EXTERNAL_STORAGE == requestCode){
            // si el permiso es obtenido
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // llamar a la camara nativa del dispositivo android
                linkVideo = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/video.mp4");
                Log.d("link video verdad", Environment.getExternalStorageDirectory().getAbsolutePath() + "/video.mp4");

                Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);

                Uri videoUri = Uri.fromFile(linkVideo);

                intent.putExtra(MediaStore.EXTRA_OUTPUT, videoUri);
                startActivityForResult(intent, VIDEO_CAPTURE);

            } else {
                // si el usuario no da los permisos terminamos la actividad
                finish();
            }

        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == VIDEO_CAPTURE) {
            if (resultCode == RESULT_OK) {
               // Toast.makeText(this, "Video saved to:\n" +data.getData(), Toast.LENGTH_LONG).show();

                //Crear el bean video
                videoCreado = new Video();
                videoCreado.setLinkVideo(linkVideo.getPath());
                Log.d("link video guardado", linkVideo.getPath());
                videoCreado.setNombreVideo("VideoRespuesta-Pregunta" + numeroPreguntaVideo);
                videoCreado.setTipoVideo("VideoRespuesta");

                respuesta.addVideoRespuesta(videoCreado);

                // comprobar si es la ultima respuesta
                if(isUltimaPregunta){
                    //crear intent mirando que tipo de pregunta es la primera del cuetionario de satisfacción
                    Formulario cuestionarioStisfaccion = entrevista.getCuestionarioSatisfaccion();
                    Pregunta preguntaActual = cuestionarioStisfaccion.getPreguntas().get(0);

                    if (preguntaActual.getTipoPregunta().equals("text")){

                        intent = new Intent(Activity_GrabarRespuesta.this, Activity_PreguntaText.class);

                    } else if (preguntaActual.getTipoPregunta().equals("textArea")){

                        intent = new Intent(Activity_GrabarRespuesta.this, Activity_PreguntaTextArea.class);

                    } else if (preguntaActual.getTipoPregunta().equals("checkBox")){

                        intent = new Intent(Activity_GrabarRespuesta.this, Activity_PreguntaCheckBox.class);

                    } else if (preguntaActual.getTipoPregunta().equals("select")){

                        intent = new Intent(Activity_GrabarRespuesta.this, Activity_PreguntaSelect.class);

                    } else if (preguntaActual.getTipoPregunta().equals("radioButton")){

                        intent = new Intent(Activity_GrabarRespuesta.this, Activity_PreguntaRadioButton.class);
                    }


                    intent.putExtra("entrevista", entrevista);
                    intent.putExtra("respuesta", respuesta);
                    intent.putExtra("formulario", cuestionarioStisfaccion);
                    intent.putExtra("isCuestionarioSatisfaccion", true);
                    intent.putExtra("preguntaActual", preguntaActual);
                    intent.putExtra("numeroPreguntasFormulario", cuestionarioStisfaccion.getPreguntas().size());
                    intent.putExtra("numeroPregunta", 1);

                    startActivity(intent);


                }
                else{
                    //crear inten
                    intent = new Intent(Activity_GrabarRespuesta.this, Activity_VideoPregunta.class);

                    intent.putExtra("entrevista", entrevista);
                    intent.putExtra("respuesta", respuesta);
                    intent.putExtra("numeroPreguntasVideo", numeroPreguntasVideo);
                    intent.putExtra("numeroPreguntaVideo", numeroPreguntaVideo);

                    startActivity(intent);
                }




            } else if (resultCode == RESULT_CANCELED) {
              //  Toast.makeText(this, "Video recording cancelled.", Toast.LENGTH_LONG).show();
            } else {
               // Toast.makeText(this, "Failed to record video",Toast.LENGTH_LONG).show();
            }
        }
    }

}
