package com.example.testtaskitunesapi;

import android.app.SearchManager;
import android.content.Context;
import android.database.MatrixCursor;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import com.example.testtaskitunesapi.Model.AlbumModel;
import com.example.testtaskitunesapi.Model.TrackModel;
import com.example.testtaskitunesapi.Presenter.MainPresenter;
import com.example.testtaskitunesapi.Retrofit.APIService;
import com.example.testtaskitunesapi.Retrofit.RetrofitClient;
import com.example.testtaskitunesapi.Adapter.AlbumAdapter;
import com.example.testtaskitunesapi.Utils.Common;
import java.util.ArrayList;
import java.util.List;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;


public class MainActivity extends AppCompatActivity implements MainContract.View {

    private static final String TAG = "MainActivity";
    private MainContract.Presenter mPresenter;

    private RecyclerView rvAlbum;
    private AlbumAdapter adapter;
    private List<AlbumModel> albumModels;
    private CompositeDisposable mCompositedisposable;
    private APIService mService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPresenter = new MainPresenter(this);
        mPresenter.onCreate();

        rvAlbum = (RecyclerView) findViewById(R.id.rvAlbum);
        rvAlbum.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvAlbum.setLayoutManager(layoutManager);
        albumModels = new ArrayList<>();
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

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            final SearchView search = (SearchView) menu.findItem(R.id.search).getActionView();
            search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String s) {
                    Log.d(TAG, "onQueryTextSubmit " + s);
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String s) {
                    Log.d(TAG, "onQueryTextChange " + s);
                    searchGet(s);
                    return false;
                }
            });
        }
        return true;
    }

    private void searchGet(String request) {
        mCompositedisposable = new CompositeDisposable();
        Retrofit retrofit = new RetrofitClient().getInstance(Common.BASE_URL);
        mService = retrofit.create(APIService.class);
        mCompositedisposable.add(mService.getTracks(mPresenter.filterSearch(request))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<TrackModel>() {
                    @Override
                    public void accept(TrackModel trackModel) throws Exception {
                        Log.d(TAG, "trackModel  " + trackModel.getTracks().size());
                        albumModels = trackModel.getAlbumModels();
                        adapter = new AlbumAdapter(MainActivity.this, albumModels);

                        if (adapter != null) {
                            rvAlbum.setAdapter(adapter);
                        }
                    }
                }, new Consumer<Throwable>() {

                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d(TAG, "mService " + throwable.getMessage());
                        Snackbar.make(rvAlbum, Common.ERROR_MSG, Snackbar.LENGTH_SHORT).show();
                    }
                })
        );
    }

}
