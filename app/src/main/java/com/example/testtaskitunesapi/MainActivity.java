package com.example.testtaskitunesapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;


import com.example.testtaskitunesapi.Model.TrackModel;
import com.example.testtaskitunesapi.Presenter.MainPresenter;
import com.example.testtaskitunesapi.Retrofit.APIService;
import com.example.testtaskitunesapi.Retrofit.RetrofitClient;
import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.List;


import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;


public class MainActivity extends AppCompatActivity implements MainContract.View {

    private static final String TAG = "MainActivity";

    private MainContract.Presenter mPresenter;

    private CompositeDisposable mCompositedisposable;
    private APIService mService;

    private Button btn;
    private RecyclerView rvAlbum;
    MaterialSearchBar searchBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPresenter = new MainPresenter(this);
        mPresenter.onCreate();


        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();

        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCompositedisposable = new CompositeDisposable();
                Retrofit retrofit = new RetrofitClient().getInstance("https://itunes.apple.com/" );
                mService = retrofit.create(APIService.class);

                    mCompositedisposable.add(mService.getTracks("jack+johnson")
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Consumer<TrackModel>() {


                                @Override
                                public void accept(TrackModel trackModel) throws Exception {

                                }
                            }, new Consumer<Throwable>() {

                                @Override
                                public void accept(Throwable throwable) throws Exception {
                                    Log.d(TAG, "mService " + throwable.getMessage());
                                }
                            })
                    );

            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mPresenter.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
    }
}
