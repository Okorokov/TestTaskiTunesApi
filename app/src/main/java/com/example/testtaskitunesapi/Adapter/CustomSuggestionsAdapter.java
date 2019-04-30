package com.example.testtaskitunesapi.Adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.testtaskitunesapi.MainContract;
import com.example.testtaskitunesapi.R;
import com.mancj.materialsearchbar.adapter.SuggestionsAdapter;



public class CustomSuggestionsAdapter extends SuggestionsAdapter<String, CustomSuggestionsAdapter.SuggestionHolder> {

    private MainContract.View mView;
    public CustomSuggestionsAdapter(LayoutInflater inflater,MainContract.View mView) {
        super(inflater);
        this.mView=mView;
    }

    @Override
    public int getSingleViewHeight() {
        return 80;
    }

    @Override
    public SuggestionHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = getLayoutInflater().inflate(R.layout.search_item, parent, false);
        return new SuggestionHolder(view);
    }

    @Override
    public void onBindSuggestionHolder(final String suggestion, SuggestionHolder holder, int position) {
        holder.title.setText(suggestion);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mView.setTextSearch(suggestion);
            }
        });
    }



    static class SuggestionHolder extends RecyclerView.ViewHolder{
        protected TextView title;
        protected ImageView image;

        public SuggestionHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
        }
    }

}