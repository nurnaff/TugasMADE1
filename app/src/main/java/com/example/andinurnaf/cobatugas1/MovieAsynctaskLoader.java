package com.example.andinurnaf.cobatugas1;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.SyncHttpClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MovieAsynctaskLoader extends AsyncTaskLoader<ArrayList<Movie>> {
    private ArrayList <Movie> mData = new ArrayList<Movie>();
    private boolean mHasResult = false;
    private static final String API_KEY = "6c063cdb5fc9520684745b5408008e68";
    private String mMovieTitle;

    public MovieAsynctaskLoader(final Context context, String MovieTitle) {
        super(context);

        onContentChanged();
        this.mMovieTitle = MovieTitle;
    }

    // Connecting to MovieDB API
    @Override
    public ArrayList<Movie> loadInBackground() {
        SyncHttpClient client = new SyncHttpClient();

        final ArrayList <Movie> movieList = new ArrayList<>();

        String url = "https://api.themoviedb.org/3/search/movie?api_key=" + API_KEY + "&language=en-US&query=" + mMovieTitle;

        client.get(url, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Log.d("MEAD URL", "onSuccess: OK");
                try {

                    JSONObject object = new JSONObject(new String(responseBody));
                    JSONArray result = object.getJSONArray("results");

                    for (int i = 0; i < result.length(); i++){
                        JSONObject movieitem = result.getJSONObject(i);
                        int id = movieitem.getInt("id");
                        String title = movieitem.getString("title");
                        String date = movieitem.getString("release_date");
                        String url = movieitem.getString("poster_path");
                        String overview = movieitem.getString("overview");
                        int vote_count = movieitem.getInt("vote_count");
                        Double vote_average = movieitem.getDouble("vote_average");
                        Double popularity = movieitem.getDouble("popularity");
                        String adult = movieitem.getString("adult");

                        Movie newMovie = new Movie(id,
                                title,
                                overview,
                                date,
                                url,
                                vote_count,
                                vote_average,
                                popularity,
                                adult);
                        movieList.add(newMovie);
                    }
                    Log.d("EXAMPLE URL" , movieList.get(1).getUrl());

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });

        return movieList;
    }

    @Override
    protected void onStartLoading (){
        if (takeContentChanged())forceLoad();
        else if (mHasResult)deliverResult(mData);
    }

    // If data has been retrieved
    @Override
    public void deliverResult (final ArrayList<Movie> data){
        mData = data;
        mHasResult = true;
        super.deliverResult(data);
    }

    @Override
    protected void onReset (){
        super.onReset();
        onStopLoading();
        if (mHasResult){
            onReleaseResources (mData);
            mData = null;
            mHasResult = false;
        }
    }

    private void onReleaseResources(ArrayList<Movie> mData) {
    }
}