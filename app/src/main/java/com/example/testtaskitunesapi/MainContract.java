package com.example.testtaskitunesapi;

import com.example.testtaskitunesapi.Adapter.AlbumAdapter;

public interface MainContract {
    interface View {}
    interface Presenter {
        void onCreate();
        void onStart();
        void onStop();
        void onDestroy();
        String filterSearch(String request);
    }
    interface Repository {}
    interface PlayListView {}
    interface PlayListPresenter {
        void onCreate();
        void onStart();
        void onStop();
        void onDestroy();
    }
}
