package com.example.testtaskitunesapi;

public interface MainContract {
    interface View {}
    interface Presenter {
        void onCreate();
        void onStart();
        void onStop();
        void onDestroy();
    }

    interface Repository {}

    interface PlayListView {}
    interface PlayListPresenter {
        void onCreate();
        void onStart();
        void onStop();
        void onDestroy();

        void onPlay(String url);
    }
}
