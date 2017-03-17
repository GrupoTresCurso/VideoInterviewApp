package com.example.tictum.appcandidatos.activities;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.CountDownTimer;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Surface;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tictum.appcandidatos.R;
import com.example.tictum.appcandidatos.previews.CameraPreview;

import java.util.concurrent.TimeUnit;

public class Activity_GrabarRespuesta extends AppCompatActivity {

    private Camera mCamera;
    private CameraPreview mPreview;
    public static final int MY_PERMISSIONS_REQUEST_CAMERA = 1;

    private TextView tv_timer;
    private Button botonComenzar;
    private Button botonResponder;
    private ImageView recIcon;
    private CountDownTimer countDownTimer;
    private boolean isRunning=true;
    private long counterTime=0;
    private int minutos;
    private int segundos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.layout__grabar_respuesta);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        //Solicitar permisos
        getPermissions();

        //Iniciacion de vistas
        tv_timer = (TextView) findViewById(R.id.temporizador);
        botonComenzar = (Button) findViewById(R.id.botonComenzarGrabarVideo);
        botonResponder = (Button) findViewById(R.id.botonMandarVideo);
        recIcon = (ImageView) findViewById(R.id.rec_icon);
        isRunning = true;

        //Determinar los segundos
        String tiempoRespuesta = (String) tv_timer.getText();
        String minutosString = tiempoRespuesta.substring(0, 2);
        int minutos = Integer.parseInt(minutosString);
        counterTime= TimeUnit.SECONDS.toMillis(minutos *60);



        botonComenzar.setOnClickListener (new View.OnClickListener() {
            public void onClick(View v) {
                start(counterTime);
                botonComenzar.setEnabled(false);
                botonResponder.setEnabled(true);
            }
        });

    }

    //-------CUENTA ATRAS-----------//



    public void stop(){
        if (countDownTimer!=null){
            countDownTimer.cancel();
        }
    }

    public void start(long time){
        countDownTimer = new CountDownTimer(time, 1000) {
            public void onTick(long millisUntilFinished) {
                minutos = (int)TimeUnit.MILLISECONDS.toMinutes( millisUntilFinished);
                segundos =  (int)(TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)));

                if(recIcon.getVisibility() != View.VISIBLE){
                    recIcon.setVisibility(View.VISIBLE);
                }
                else if(recIcon.getVisibility() == View.VISIBLE){
                    recIcon.setVisibility(View.INVISIBLE);
                }
                tv_timer.setText(showTime(minutos, segundos));
            }
            public void onFinish() {
                tv_timer.setText("00:00");
                //MOSTRAR ALERT PARA QUE PASE A LA SIGUIENTE PREGUNTA
                //startActivity(new Intent(TimerActivity.this,MainActivity.class));
                finish();
            }
        };
        if (countDownTimer!=null){
            countDownTimer.start();
        }
    }



    public String showTime(int minutos, int segundos){
        String minutosString = String.valueOf(minutos);
        String segundosString = String.valueOf(segundos);

        int lenMinutos = minutosString.length();
        int lenSegundos = segundosString.length();

        if(lenMinutos == 2 && lenSegundos == 2){
            return minutos + ":"+ segundos;
        }
        else if(lenMinutos == 2 && lenSegundos == 1){
            return minutos + ":0"+ segundos;
        }
        else if(lenMinutos == 1 && lenSegundos == 1){
            return "0" + minutos + ":0"+ segundos;
        }
        else if(lenMinutos == 1 && (lenSegundos == 2 || segundos == 0) ){
            return "0" + minutos + ":"+ segundos;
        }
        else{
            return minutos + ":"+ segundos;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer!=null){
            countDownTimer.cancel();
        }
    }


    //-----------------------------//




    //----------CAMARA-----------//

    private boolean checkCameraHardware(Context context) {
        if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)){
            // this device has a camera
            return true;
        } else {
            // no camera on this device
            return false;
        }
    }

    /** Check if this device has a front camera */
    private boolean checkFrontCameraHardware(Camera camera) {
        if (camera.getNumberOfCameras() > 1){
            // this device has more than two cameras
            return true;
        } else {
            // this device has less than two cameras
            return false;
        }
    }

    /** A safe way to get an instance of the Camera object.  */
    //Abre la camara normal
    public static Camera getCameraInstance(){
        Camera camera = null;
        try {
            camera = Camera.open(1); // attempt to get a Camera instance
        }
        catch (Exception e){
            Log.d("Excepcion", "getCameraInstance", e);
        }
        return camera; // returns null if camera is unavailable
    }

    //Abre la camara frontal
    private Camera getFrontCameraInstance() {
        int cameraCount = 0;
        Camera camera = null;
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        cameraCount = Camera.getNumberOfCameras();
        for (int camIdx = 0; camIdx < cameraCount; camIdx++) {
            Camera.getCameraInfo(camIdx, cameraInfo);
            if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
                try {
                    camera = Camera.open(camIdx);
                } catch (RuntimeException e) {
                    Log.e("Excepcion", "Camara frontal falla al abrirse: " + e.getLocalizedMessage());
                }
            }
        }

        return camera;
    }

    //Orientación de la camara
    public static void setCameraDisplayOrientation(Activity activity,
                                                   int cameraId, android.hardware.Camera camera) {
        android.hardware.Camera.CameraInfo info =
                new android.hardware.Camera.CameraInfo();
        android.hardware.Camera.getCameraInfo(cameraId, info);
        int rotation = activity.getWindowManager().getDefaultDisplay()
                .getRotation();
        int degrees = 0;
        switch (rotation) {
            case Surface.ROTATION_0: degrees = 0; break;
            case Surface.ROTATION_90: degrees = 90; break;
            case Surface.ROTATION_180: degrees = 180; break;
            case Surface.ROTATION_270: degrees = 270; break;
        }

        int result;
        if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
            result = (info.orientation + degrees) % 360;
            result = (360 - result) % 360;  // compensate the mirror
        } else {  // back-facing
            result = (info.orientation - degrees + 360) % 360;
        }
        camera.setDisplayOrientation(result);
    }

    //Permisos para la camara
    public void getPermissions(){
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA},
                    MY_PERMISSIONS_REQUEST_CAMERA);
        }
        else{
            mCamera = getCameraInstance();

            // Configurar orientación de la camara
            setCameraDisplayOrientation(this, 1, mCamera);


            // Create our Preview view and set it as the content of our activity.
            mPreview = new CameraPreview(this, mCamera);
            FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview);
            preview.addView(mPreview);
        }
    }

    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CAMERA: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    mCamera = getCameraInstance();

                    // Configurar orientación de la camara
                    setCameraDisplayOrientation(this, 1, mCamera);


                    // Create our Preview view and set it as the content of our activity.
                    mPreview = new CameraPreview(this, mCamera);
                    FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview);
                    preview.addView(mPreview);



                } else {

                    // permission denied,
                }
            }

            return;
        }

        // other 'case' lines to check for other
        // permissions this app might request
    }

    //-----------------------------//


}
