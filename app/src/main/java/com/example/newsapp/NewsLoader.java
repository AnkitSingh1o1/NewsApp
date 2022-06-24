package com.example.newsapp;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

public class NewsLoader extends AsyncTaskLoader<List<News>> {
    /** Query URL */
    private String bUrl;

    public NewsLoader(Context context, String url) {
        super(context);
        bUrl = url;
    }

    /**
     * @param context
     * @deprecated
     */
    public NewsLoader(Context context) {
        super(context);
    }


    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<News> loadInBackground() {
        if (bUrl == null) {
            return null;
        }

        // Perform the network request, parse the response, and extract a list of earthquakes.
        List<News> books = QueryUtils.fetchNewsData(bUrl);
        return books;
    }
}
