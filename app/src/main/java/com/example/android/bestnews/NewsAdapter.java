package com.example.android.bestnews;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    String[] newsItems;
    private final NewsItemClickHandler handler;

    interface NewsItemClickHandler{
        void onClick(String newsItemClicked);
    }

    NewsAdapter(NewsItemClickHandler handler){
        this.handler = handler;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        int listItemID = R.layout.newsrv;
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(listItemID, viewGroup, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder newsViewHolder, int i) {
        newsViewHolder.newsItem.setText(newsItems[i]);
    }

    @Override
    public int getItemCount() {
        if(newsItems == null) return 0;
        return newsItems.length;
    }

    class NewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView newsItem;
        NewsViewHolder(View view){
            super(view);
            newsItem = (TextView) view.findViewById(R.id.tv_newsitem);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int pos = getAdapterPosition();
            String newsItem = newsItems[pos];
            handler.onClick(newsItem);
        }
    }

    public void setNewsItems(String[] newsItems) {
        this.newsItems = newsItems;
        notifyDataSetChanged();
    }
}
