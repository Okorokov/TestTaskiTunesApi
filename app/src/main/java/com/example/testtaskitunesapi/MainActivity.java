package com.example.testtaskitunesapi;

import android.database.MatrixCursor;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;

import com.example.testtaskitunesapi.Adapter.CustomSuggestionsAdapter;
import com.example.testtaskitunesapi.Presenter.MainPresenter;
import com.example.testtaskitunesapi.Adapter.AlbumAdapter;
import com.mancj.materialsearchbar.MaterialSearchBar;
import com.mancj.materialsearchbar.adapter.SuggestionsAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    private static final String TAG = "MainActivity";
    private MainContract.Presenter mPresenter;
    private RecyclerView rvAlbum;
    private List<String> suggestions = new ArrayList<>();
    private CustomSuggestionsAdapter customSuggestionsAdapter;
    private MaterialSearchBar material_search_bar;

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

        material_search_bar = (MaterialSearchBar) findViewById(R.id.material_search_bar);
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        customSuggestionsAdapter = new CustomSuggestionsAdapter(inflater, this);
        suggestions = new ArrayList<>();
        suggestions.add("erty");
        suggestions.add("fgdnmdm");
        customSuggestionsAdapter.setSuggestions(suggestions);
        material_search_bar.setCustomSuggestionAdapter(customSuggestionsAdapter);
        material_search_bar.addTextChangeListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mPresenter.search(s);
            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.d(TAG, "afterTextChanged " + s);
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


    @Override
    public void searchGet(AlbumAdapter albumAdapter) {
        rvAlbum.setAdapter(albumAdapter);
    }

    @Override
    public void setTextSearch(String s) {
        material_search_bar.setText(s);
        //mPresenter.search(s);
        material_search_bar.hideSuggestionsList();
    }


}
