package com.example.testtaskitunesapi.Presenter;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;

import com.example.testtaskitunesapi.Adapter.AlbumAdapter;
import com.example.testtaskitunesapi.MainContract;
import com.example.testtaskitunesapi.Model.AlbumModel;
import com.example.testtaskitunesapi.Model.TrackModel;
import com.example.testtaskitunesapi.Retrofit.APIService;
import com.example.testtaskitunesapi.Retrofit.RetrofitClient;
import com.example.testtaskitunesapi.Utils.Common;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class MainPresenter implements MainContract.Presenter{

    private static final String TAG = "MainPresenter";

    private MainContract.View mView;
    private MainContract.Repository mRepository;
    private AlbumAdapter adapter;
    private List<AlbumModel> albumModels;
    private CompositeDisposable mCompositedisposable;
    private APIService mService;


    public MainPresenter(MainContract.View mView) {
        this.mView = mView;
        Log.d(TAG, "Constructor");
    }

    @Override
    public void onCreate() {
        albumModels = new ArrayList<>();
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
    public String filterSearch(String request) {
        String[] searchTrim = request.trim().split("\\s+");
        String mWhere = "";
        for (String st : searchTrim) {
            //Log.d(TAG, "test " + st);
            if (mWhere.equals("")) {
                mWhere += st;
            } else {
                mWhere += "+" + st;
            }
        }
        return mWhere;
    }

    @Override
    public void search(CharSequence request) {
        mCompositedisposable = new CompositeDisposable();
        Retrofit retrofit = new RetrofitClient().getInstance(Common.BASE_URL);
        mService = retrofit.create(APIService.class);
        mCompositedisposable.add(mService.getTracks(filterSearch(String.valueOf(request)))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<TrackModel>() {
                    @Override
                    public void accept(TrackModel trackModel) throws Exception {
                        Log.d(TAG, "trackModel  " + trackModel.getTracks().size());
                        albumModels = trackModel.getAlbumModels();
                        adapter = new AlbumAdapter((Context) mView, albumModels);
                        mView.searchGet(adapter);
                    }
                }, new Consumer<Throwable>() {

                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d(TAG, "mService " + throwable.getMessage());
                        Snackbar.make((View) mView, Common.ERROR_MSG, Snackbar.LENGTH_SHORT).show();
                    }
                })
        );
    }


}
