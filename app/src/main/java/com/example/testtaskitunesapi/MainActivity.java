package com.example.testtaskitunesapi;

import android.app.SearchManager;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


import com.example.testtaskitunesapi.Model.AlbumModel;
import com.example.testtaskitunesapi.Model.TrackModel;
import com.example.testtaskitunesapi.Presenter.MainPresenter;
import com.example.testtaskitunesapi.Retrofit.APIService;
import com.example.testtaskitunesapi.Retrofit.RetrofitClient;
import com.example.testtaskitunesapi.Adapter.AlbumAdapter;
import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.ArrayList;
import java.util.Iterator;
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
    //private MaterialSearchBar searchBar;

    private AlbumAdapter adapter;
    private List<AlbumModel> albumModels;

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
        //searchBar=(MaterialSearchBar)findViewById(R.id.material_search_bar);

        rvAlbum = (RecyclerView) findViewById(R.id.rvAlbum);
        rvAlbum.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvAlbum.setLayoutManager(layoutManager);

        albumModels=new ArrayList<>();




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


        //this.menu = menu;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            //SearchManager manager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
            final SearchView search = (SearchView) menu.findItem(R.id.search).getActionView();
            //search.setQueryRefinementEnabled(false);
            //search.setIconifiedByDefault(false);
            //search.setSubmitButtonEnabled(true);

            search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String s) {
                    Log.d(TAG,"onQueryTextSubmit "+s);
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String s) {
                    Log.d(TAG,"onQueryTextChange "+s);
                    searchGet(s);
                   /* result = new ArrayList<>();
                    result = sqLiteHelper.getSearch(sqLiteDatabase,s, TABLE_NAME);
                    adapter = new AnswerAdapter(context, result);
                    mRecyclerView.setAdapter(adapter);*/
                    return false;
                }
            });

            search.setOnCloseListener(new SearchView.OnCloseListener() {
                @Override
                public boolean onClose() {
                    return true;
                }
            });


        }
        return true;
    }

    private void searchGet(String request) {
        mCompositedisposable = new CompositeDisposable();
        Retrofit retrofit = new RetrofitClient().getInstance("https://itunes.apple.com/" );
        mService = retrofit.create(APIService.class);


        mCompositedisposable.add(mService.getTracks(filter(request))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<TrackModel>() {
                    @Override
                    public void accept(TrackModel trackModel) throws Exception {
                        Log.d(TAG, "trackModel  " + trackModel.getTracks().size());
                        albumModels=trackModel.getAlbumModels();
                        adapter = new AlbumAdapter(MainActivity.this, albumModels);

                        if (adapter != null) {
                            rvAlbum.setAdapter(adapter);
                        }
                        Log.d(TAG, "albumModels  " + albumModels.size());

                    }
                }, new Consumer<Throwable>() {

                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d(TAG, "mService " + throwable.getMessage());
                        Snackbar.make(rvAlbum,"Что то пошло не так!",Snackbar.LENGTH_SHORT).show();
                    }
                })
        );
    }

    private String filter(String request) {
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
    return  mWhere;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        Log.d(TAG,"id "+id);
        //noinspection SimplifiableIfStatement
        if (id == R.id.search) {

            //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            return true;
        }
        if(id == android.R.id.home){

            //getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
