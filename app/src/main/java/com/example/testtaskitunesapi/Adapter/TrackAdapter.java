package com.example.testtaskitunesapi.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.testtaskitunesapi.Model.Track;
import com.example.testtaskitunesapi.R;
import java.util.List;

public class TrackAdapter extends RecyclerView.Adapter<TrackAdapter.TrackViewHolder>{
    private Context context;
    private LayoutInflater inflater;
    private List<Track> tracks;


    public TrackAdapter(Context context, List<Track> tracks) {
        this.context=context;
        this.tracks = tracks;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public TrackViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.track_item, viewGroup, false);
        return new TrackViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrackViewHolder trackViewHolder,final int i) {
        Track track = tracks.get(i);
        trackViewHolder.tvTrackName.setText(track.getTrackName());
        trackViewHolder.tvTrackTimeMillis.setText(formatTrackTime(track.getTrackTimeMillis()));
        trackViewHolder.tvTrackNumber.setText(String.valueOf(track.getTrackNumber()));
        trackViewHolder.tvTrackPrice.setText(String.valueOf(track.getTrackPrice()));
        trackViewHolder.tvCurrency.setText(track.getCurrency());

    }

    private String formatTrackTime(Integer trackTimeMillis) {
      int  sc = (int) trackTimeMillis/1000;
     int   min = sc / 60;
     int   sec = sc - (min * 60);
       return String.format("%02d:%02d", min, sec);
    }

    @Override
    public int getItemCount() {
        return tracks.size();
    }


    public class TrackViewHolder extends RecyclerView.ViewHolder{

        private TextView tvTrackName;
        private TextView tvTrackTimeMillis;
        private TextView tvTrackNumber;
        private TextView tvTrackPrice;
        private TextView tvCurrency;


        public TrackViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTrackName = itemView.findViewById(R.id.tvTrackName);
            tvTrackTimeMillis = itemView.findViewById(R.id.tvTrackTimeMillis);
            tvTrackNumber = itemView.findViewById(R.id.tvTrackNumber);
            tvTrackPrice = itemView.findViewById(R.id.tvTrackPrice);
            tvCurrency = itemView.findViewById(R.id.tvCurrency);
        }
    }
}
