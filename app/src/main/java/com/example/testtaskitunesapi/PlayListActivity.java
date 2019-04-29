package com.example.testtaskitunesapi;

import android.content.Intent;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.testtaskitunesapi.Adapter.TrackAdapter;
import com.example.testtaskitunesapi.Model.AlbumModel;
import com.example.testtaskitunesapi.Utils.Common;
import com.squareup.picasso.Picasso;


public class PlayListActivity extends AppCompatActivity implements MainContract.PlayListView {

    private AlbumModel albumModel;
    private ImageView ivArtworkUrl;
    private TextView tvCollectionName;
    private TextView tvArtistName;
    private TextView tvReleaseDate;
    private Button btnPlay;
    private RecyclerView rvTrack;
    private TrackAdapter adapter;
    private BottomSheetBehavior bottomSheetBehavior;
    private TextView tvTrackCount;
    private TextView tvCollectionPrice;
    private Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        init();
        mainActToPlayList();

        rvTrack.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvTrack.setLayoutManager(layoutManager);
        adapter = new TrackAdapter(this, albumModel.getListtotracks());
        if (adapter != null) {
            rvTrack.setAdapter(adapter);
        }

        View bottomSheet = findViewById(R.id.nsvBtnSheet);
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            }
        });
    }

    private void mainActToPlayList() {
        albumModel.setCollectionName(intent.getStringExtra(Common.MO_COLLECTIONAME));
        albumModel.setArtistName(intent.getStringExtra(Common.MO_ARTISTNAME));
        albumModel.setReleaseDate(intent.getStringExtra(Common.MO_RELEASEDATE));
        albumModel.setArtworkUrl(intent.getStringExtra(Common.MO_ARTWORKURL));
        albumModel.setCollectionPrice(intent.getStringExtra(Common.MO_COLLECTIONPRICE));

        albumModel.setPrimaryGenreName(intent.getStringExtra(Common.MO_PRIMARYGENRENAME));
        albumModel.setTrackCount(intent.getStringExtra(Common.MO_TRACKCOUNT));

        albumModel.setTrackNames(intent.getStringArrayListExtra(Common.MO_TRACKMAMES));
        albumModel.setPreviewUrls(intent.getStringArrayListExtra(Common.MO_PREVIEWURLS));
        albumModel.setTrackTimeMillis(intent.getStringArrayListExtra(Common.MO_TRACKTIMEMILLIS));
        albumModel.setTrackPrices(intent.getStringArrayListExtra(Common.MO_TRACKPRICES));
        albumModel.setCurrencys(intent.getStringArrayListExtra(Common.MO_CURRENCYS));
        albumModel.setTrackNumber(intent.getStringArrayListExtra(Common.MO_TRACKNUMBER));

        tvCollectionName.setText(albumModel.getCollectionName());
        tvArtistName.setText(albumModel.getArtistName());
        tvReleaseDate.setText(albumModel.getReleaseDate());

        tvTrackCount.setText(albumModel.getTrackCount());
        tvCollectionPrice.setText(albumModel.getCollectionPrice());


        Picasso.get().load(albumModel.getArtworkUrl()).into(ivArtworkUrl);
    }

    private void init() {
        ivArtworkUrl = (ImageView) findViewById(R.id.ivArtworkUrl);
        tvCollectionName = (TextView) findViewById(R.id.tvCollectionName);
        tvArtistName = (TextView) findViewById(R.id.tvArtistName);
        tvReleaseDate = (TextView) findViewById(R.id.tvReleaseDate);
        btnPlay = (Button) findViewById(R.id.btnPlay);
        rvTrack = (RecyclerView) findViewById(R.id.rvTrack);

        tvTrackCount = (TextView) findViewById(R.id.tvTrackCount);
        tvCollectionPrice = (TextView) findViewById(R.id.tvCollectionPrice);


        intent = getIntent();
        albumModel = new AlbumModel();
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
