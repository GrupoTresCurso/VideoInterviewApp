package com.example.tictum.appcandidatos.business;

import android.content.Context;

import com.example.tictum.appcandidatos.beans.Video;
import com.example.tictum.appcandidatos.sqliteDAO.VideoDAO;

import java.util.ArrayList;

public class VideoManager {

    private VideoDAO videoDAO;

    public VideoManager(Context context) {
    }

    public long insertVideo(Video video){
        return videoDAO.insertVideo(video);
    }

    public Video getVideo(int idVideo){
        return videoDAO.getVideo(idVideo);
    }

    public ArrayList<Video> getAllVideos(){
        return videoDAO.getAllVideos();
    }
}
