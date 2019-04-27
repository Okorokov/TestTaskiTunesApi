package com.example.testtaskitunesapi;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.testtaskitunesapi.Adapter.AlbumAdapter;
import com.example.testtaskitunesapi.Adapter.TrackAdapter;
import com.example.testtaskitunesapi.Model.AlbumModel;
import com.example.testtaskitunesapi.Presenter.MainPresenter;
import com.example.testtaskitunesapi.Presenter.PlayListPresenter;
import com.example.testtaskitunesapi.Utils.Common;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;

public class PlayListActivity extends AppCompatActivity implements MainContract.PlayListView{

    private MainContract.PlayListPresenter mPresenter;

    private AlbumModel albumModel;
    private ImageView ivArtworkUrl;
    private TextView tvCollectionName;
    private TextView tvArtistName;

    private TextView tvReleaseDate;
    private Button btnPlay;
    private RecyclerView rvTrack;
    private TrackAdapter adapter;
    private BottomSheetBehavior bottomSheetBehavior;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ivArtworkUrl=(ImageView) findViewById(R.id.ivArtworkUrl);
        tvCollectionName=(TextView)findViewById(R.id.tvCollectionName);
        tvArtistName=(TextView)findViewById(R.id.tvArtistName);
        tvReleaseDate=(TextView)findViewById(R.id.tvReleaseDate);
        btnPlay=(Button) findViewById(R.id.btnPlay);
        rvTrack=(RecyclerView) findViewById(R.id.rvTrack);

        Intent intent = getIntent();
        albumModel=new AlbumModel();

        albumModel.setCollectionName(intent.getStringExtra(Common.MO_COLLECTIONAME));
        albumModel.setArtistName(intent.getStringExtra(Common.MO_ARTISTNAME));
        albumModel.setReleaseDate(intent.getStringExtra(Common.MO_RELEASEDATE));
        albumModel.setArtworkUrl(intent.getStringExtra(Common.MO_ARTWORKURL));
        albumModel.setCollectionPrice(intent.getStringExtra(Common.MO_COLLECTIONPRICE));

        albumModel.setPrimaryGenreName(intent.getStringExtra(Common.MO_PRIMARYGENRENAME));

        albumModel.setTrackNames(intent.getStringArrayListExtra(Common.MO_TRACKMAMES));
        albumModel.setPreviewUrls(intent.getStringArrayListExtra(Common.MO_PREVIEWURLS));
        albumModel.setTrackTimeMillis(intent.getStringArrayListExtra(Common.MO_TRACKTIMEMILLIS));
        albumModel.setTrackPrices(intent.getStringArrayListExtra(Common.MO_TRACKPRICES));
        albumModel.setCurrencys(intent.getStringArrayListExtra(Common.MO_CURRENCYS));

        tvCollectionName.setText(albumModel.getCollectionName());
        tvArtistName.setText(albumModel.getArtistName());
        tvReleaseDate.setText(albumModel.getReleaseDate());
        Picasso.get().load(albumModel.getArtworkUrl()).into(ivArtworkUrl);


        rvTrack.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvTrack.setLayoutManager(layoutManager);

        adapter = new TrackAdapter(this, albumModel.getListtotracks(),mPresenter);

        Log.d("XXX", "listPlay.size()  " + albumModel.getListtotracks().get(0).getTrackName());
        if (adapter != null) {
            rvTrack.setAdapter(adapter);
        }

        View bottomSheet=findViewById(R.id.nsvBtnSheet);
        bottomSheetBehavior=BottomSheetBehavior.from(bottomSheet);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            }
        });

        mPresenter = new PlayListPresenter(this,this);
        mPresenter.onCreate();


    }
}
