package com.example.usuario.cameraview;

import android.content.Context;
import android.hardware.Camera;
import android.media.MediaRecorder;
import android.os.Environment;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.File;
import java.io.IOException;
import android.content.Context;
import android.hardware.Camera;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;


public class CameraPreview extends SurfaceView implements SurfaceHolder.Callback {

    private SurfaceHolder holder;
    private MediaRecorder recorder;

    public CameraPreview(Context context, AttributeSet attrs) {
        super(context,attrs);

        // Install a SurfaceHolder.Callback so we get notified when the
        // underlying surface is created and destroyed.
        holder = getHolder();
        holder.addCallback(this);
        // deprecated setting, but required on Android versions prior to 3.0
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        recorder = new MediaRecorder();
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setVideoSource(MediaRecorder.VideoSource.DEFAULT);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        recorder.setVideoEncoder(MediaRecorder.VideoEncoder.MPEG_4_SP);
    }

    public void surfaceCreated(SurfaceHolder holder) {

        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/data/video.mp4";
        File directory = new File(path).getParentFile();
        // The Surface has been created, now tell the camera where to draw the preview.

        if (!directory.exists() && !directory.mkdirs()) {
            try {
                throw new IOException("Path to file could not be created.");
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        recorder.setOutputFile(path);
        recorder.setPreviewDisplay(holder.getSurface());

        if (recorder != null) {

            try {
                recorder.prepare();

            } catch (IllegalStateException e) {

                Log.e("YO.1.Illegal", e.toString());

            } catch (IOException e) {

                Log.e("YO.2.IOException", e.toString());

            }
        }

    }

    public MediaRecorder getRecorder() {

        return recorder;
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        // empty. Take care of releasing the Camera preview in your activity.

    }

    public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
        // If your preview can change or rotate, take care of those events here.
        // Make sure to stop the preview before resizing or reformatting it.

    }
}
