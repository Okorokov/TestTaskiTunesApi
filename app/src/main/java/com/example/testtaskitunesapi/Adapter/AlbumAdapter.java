package com.example.testtaskitunesapi.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testtaskitunesapi.Model.AlbumModel;

import com.example.testtaskitunesapi.PlayListActivity;
import com.example.testtaskitunesapi.R;
import com.example.testtaskitunesapi.Utils.Common;
import com.squareup.picasso.Picasso;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder>{
    private Context context;
    private LayoutInflater inflater;
    private List<AlbumModel> albumModels;

    public AlbumAdapter(Context context, List<AlbumModel> albumModels) {
        this.context=context;
        this.albumModels = albumModels;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public AlbumViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.album_item, viewGroup, false);
        return new AlbumViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumViewHolder albumViewHolder,final int i) {
        AlbumModel albumModel = albumModels.get(i);
        albumViewHolder.tvCollectionName.setText(albumModel.getCollectionName());
        albumViewHolder.tvArtistName.setText(albumModel.getArtistName());
        albumViewHolder.tvReleaseDate.setText(formatDate(albumModel.getReleaseDate()));
        Picasso.get().load(albumModel.getArtworkUrl()).into(albumViewHolder.ivArtworkUrl);

        albumViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"item "+i,Toast.LENGTH_SHORT).show();

                Intent detail = new Intent(context, PlayListActivity.class);
                detail.putExtra(Common.MO_COLLECTIONAME,albumModels.get(i).getCollectionName());
                detail.putExtra(Common.MO_ARTISTNAME,albumModels.get(i).getArtistName());
                detail.putExtra(Common.MO_RELEASEDATE,formatDate(albumModels.get(i).getReleaseDate()));
                detail.putExtra(Common.MO_ARTWORKURL,albumModels.get(i).getArtworkUrl());
                detail.putExtra(Common.MO_COLLECTIONPRICE,albumModels.get(i).getCollectionPrice());
                detail.putExtra(Common.MO_PRIMARYGENRENAME,albumModels.get(i).getPrimaryGenreName());
                detail.putExtra(Common.MO_TRACKCOUNT,albumModels.get(i).getTrackCount());


                detail.putStringArrayListExtra(Common.MO_TRACKMAMES, albumModels.get(i).getTrackNames());
                detail.putStringArrayListExtra(Common.MO_PREVIEWURLS, albumModels.get(i).getPreviewUrls());
                detail.putStringArrayListExtra(Common.MO_TRACKTIMEMILLIS, albumModels.get(i).getTrackTimeMillis());
                detail.putStringArrayListExtra(Common.MO_TRACKPRICES, albumModels.get(i).getTrackPrices());
                detail.putStringArrayListExtra(Common.MO_CURRENCYS, albumModels.get(i).getCurrencys());
                detail.putStringArrayListExtra(Common.MO_TRACKNUMBER, albumModels.get(i).getTrackNumber());


                context.startActivity(detail);
            }
        });
    }

    private String formatDate(String releaseDate) {
        SimpleDateFormat sdfbefore = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        SimpleDateFormat sdfafter = new SimpleDateFormat("yyyy");
        String result = "0000";
        try {
            Date date=sdfbefore.parse(releaseDate);
            result=sdfafter.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int getItemCount() {
        return albumModels.size();
    }


    public class AlbumViewHolder extends RecyclerView.ViewHolder{

        private TextView tvCollectionName;
        private TextView tvArtistName;
        private TextView tvReleaseDate;
        private ImageView ivArtworkUrl;

        public AlbumViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCollectionName = itemView.findViewById(R.id.tvCollectionName);
            tvArtistName = itemView.findViewById(R.id.tvArtistName);
            tvReleaseDate = itemView.findViewById(R.id.tvReleaseDate);
            ivArtworkUrl = itemView.findViewById(R.id.ivArtworkUrl);
        }
    }
}
