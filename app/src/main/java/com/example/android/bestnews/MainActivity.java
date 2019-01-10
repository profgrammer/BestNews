package com.example.android.bestnews;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URL;

public class MainActivity extends AppCompatActivity implements NewsAdapter.NewsItemClickHandler{


    ProgressBar loadingPb;
    RecyclerView mRecyclerView;
    NewsAdapter mNewsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        loadingPb = (ProgressBar) findViewById(R.id.pb_loading);

        mRecyclerView = (RecyclerView) findViewById(R.id.rv_newsrv);


        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayout.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);

        mNewsAdapter = new NewsAdapter(this);
        mRecyclerView.setAdapter(mNewsAdapter);

        new NewsTaskManager().execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.refreshmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int clickedId = item.getItemId();
        if (clickedId == R.id.menu_refresh){
            mNewsAdapter.setNewsItems(null);
            new NewsTaskManager().execute();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(String newsItemClicked) {
        Context context = MainActivity.this;
        Class toGoClass = DetailsActivity.class;
        Intent intent = new Intent(context, toGoClass);
        intent.putExtra("data", newsItemClicked);
        startActivity(intent);
    }

    class NewsTaskManager extends AsyncTask<Void, Void, String[]>{

        @Override
        protected void onPreExecute() {
            loadingPb.setVisibility(View.VISIBLE);
            mRecyclerView.setVisibility(View.INVISIBLE);
        }

        @Override
        protected String[] doInBackground(Void... voids) {
            String temp = null;
            String s[] = null;
            try{
                temp = NetworkUtils.getHTTPResponseFromUrl(NetworkUtils.buildUrl());
                s = JSONUtils.getStringArrayFromJSONResponse(temp);
            } catch (Exception e){
                e.printStackTrace();
            }
            return s;
        }

        @Override
        protected void onPostExecute(String[] strings) {
            loadingPb.setVisibility(View.INVISIBLE);
            mRecyclerView.setVisibility(View.VISIBLE);
            mNewsAdapter.setNewsItems(strings);
        }
    }
}
