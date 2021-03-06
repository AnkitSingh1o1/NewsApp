package com.example.newsapp;

import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Context;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class NewsListFragment extends Fragment implements LoaderCallbacks<List<News>> {

    public static String NEWS_REQUEST_URL = "https://newsapi.org/v2/everything?q=";
    public static String API_KEY = "&apiKey=07165b2691ce47ff978ce1b24300631f";

    private NewsAdapter nAdapter;
    private TextView nEmptyStateTextView;

    private static final int NEWS_LOADER_ID = 1;
    //Get the parameter passed by MainActivity
    public String keyword = requireArguments().getString("some_string");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_news_list, container, false);

        ListView newsList = (ListView) rootView.findViewById(R.id.keywordlistview);
        nEmptyStateTextView = (TextView) rootView.findViewById(R.id.empty_view);
        newsList.setEmptyView(nEmptyStateTextView);

        // Create a new adapter that takes an empty list of earthquakes as input
        nAdapter = new NewsAdapter(getActivity(), new ArrayList<News>());

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        newsList.setAdapter(nAdapter);

        // Get a reference to the ConnectivityManager to check state of network connectivity
        ConnectivityManager connMgr = (ConnectivityManager)
                getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        // Get details on the currently active default data network
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        // If there is a network connection, fetch data
        if (networkInfo != null && networkInfo.isConnected()) {
            // Get a reference to the LoaderManager, in order to interact with loaders.
            LoaderManager loaderManager = requireActivity().getLoaderManager();

            // Initialize the loader. Pass in the int ID constant defined above and pass in null for
            // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
            // because this activity implements the LoaderCallbacks interface).
            loaderManager.initLoader(NEWS_LOADER_ID, null, this);
        } else {
            // Otherwise, display error
            // First, hide loading indicator so error message will be visible
            View loadingIndicator = getView().findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);

            // Update empty state with no connection error message
            nEmptyStateTextView.setText(R.string.no_internet_connection);
        }
            // Inflate the layout for this fragment
            return rootView;

    }


    @Override
    public Loader<List<News>> onCreateLoader(int i, Bundle bundle) {
        // Create a new loader for the given URL
        return new NewsLoader(getActivity(), NEWS_REQUEST_URL + keyword + API_KEY);
    }

    @Override
    public void onLoadFinished(Loader<List<News>> loader, List<News> books) {
        // Hide loading indicator because the data has been loaded
        View loadingIndicator = getView().findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);

        // Set empty state text to display "No earthquakes found."
        nEmptyStateTextView.setText(R.string.no_news);

        // Clear the adapter of previous earthquake data
        //mAdapter.clear();

        // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (books != null && !books.isEmpty()) {
            //mAdapter.addAll(earthquakes);
            updateUi(books);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<News>> loader) {
        // Loader reset, so we can clear out our existing data.
        nAdapter.clear();
    }
    /**
     * Update the screen to display information from the given {@link }.
     */
    private void updateUi(List news) {
        NewsAdapter adapter = new NewsAdapter(getActivity(), news);

        ListView booksList = (ListView) getView().findViewById(R.id.keywordlistview);

        booksList.setAdapter(adapter);
    }

}
