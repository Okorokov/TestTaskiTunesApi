package com.example.testtaskitunesapi.Presenter;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;

import com.example.testtaskitunesapi.MainContract;

import java.io.IOException;

public class PlayListPresenter implements MainContract.PlayListPresenter{

    private static final String TAG = "PlayListPresenter";

    private MainContract.PlayListView mView;
    private MediaPlayer mediaPlayer;
    private AudioManager am;
    private Context context;

    public PlayListPresenter(MainContract.PlayListView mView, Context context) {
        this.mView = mView;
        this.context=context;
        Log.d(TAG, "Constructor");
    }

    @Override
    public void onCreate() {
        //mediaPlayer=new MediaPlayer();
        //am = (AudioManager) getSystemService(AUDIO_SERVICE);
        mediaPlayer=new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onPlay(String url) {
       // mediaPlayer =  MediaPlayer.create(context, Uri.parse(url));
       // mediaPlayer.prepareAsync();
       // mediaPlayer.start();
        Log.d(TAG, "e.onPlay() ");
     /*   try {
            mediaPlayer.setDataSource(context, Uri.parse(url));
            mediaPlayer.prepareAsync();
            mediaPlayer.start();
            Log.d(TAG, "e.start() ");
        } catch (IOException e) {
            e.printStackTrace();
            Log.d(TAG, "e.printStackTrace() ");
        }*/
        //Log.d(LOG_TAG, "prepareAsync");
        //mediaPlayer.setOnPreparedListener(mView);
       // mediaPlayer.prepareAsync();
    }
}
