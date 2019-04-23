package com.example.testtaskitunesapi.Presenter;

import android.util.Log;

import com.example.testtaskitunesapi.MainContract;

public class MainPresenter implements MainContract.Presenter{

    private static final String TAG = "MainPresenter";

    private MainContract.View mView;
    private MainContract.Repository mRepository;

    public MainPresenter(MainContract.View mView) {
        this.mView = mView;
        Log.d(TAG, "Constructor");
    }

    @Override
    public void onCreate() {

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
}
