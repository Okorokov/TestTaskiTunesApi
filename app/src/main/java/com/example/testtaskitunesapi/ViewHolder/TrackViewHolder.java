package com.example.testtaskitunesapi.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

public class TrackViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    IRecyclerItemClickListener iRecyclerItemClickListener;

    public void setiRecyclerItemClickListener(IRecyclerItemClickListener iRecyclerItemClickListener) {
        this.iRecyclerItemClickListener = iRecyclerItemClickListener;
    }

    public TrackViewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        iRecyclerItemClickListener.onItemClickListener(v,getAdapterPosition());
    }
}
