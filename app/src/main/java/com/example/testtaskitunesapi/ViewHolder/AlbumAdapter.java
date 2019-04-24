package com.example.testtaskitunesapi.ViewHolder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.testtaskitunesapi.Model.AlbumModel;
import com.example.testtaskitunesapi.R;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder>{

    private LayoutInflater inflater;
    private List<AlbumModel> albumModels;

    public AlbumAdapter(Context context, List<AlbumModel> albumModels) {
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
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        SimpleDateFormat sdfx = new SimpleDateFormat("yyyy");
        try {
            Date date=sdf.parse(albumModel.getReleaseDate());

            albumViewHolder.tvReleaseDate.setText(sdfx.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Picasso.get().load(albumModel.getArtworkUrl()).into(albumViewHolder.ivArtworkUrl);
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
