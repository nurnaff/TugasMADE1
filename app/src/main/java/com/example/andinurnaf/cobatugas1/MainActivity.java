package com.example.andinurnaf.cobatugas1;

import android.support.v4.app.LoaderManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.Loader;

import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<Movie>>{

    ListView listViewMovie;
    EditText searchMovie;
    Button buttonSearch;
    ProgressBar progressBar;

    MovieAdapter movieAdapter;
    static final  String EXTRA_MOVIE = "EXTRA_MOVIE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listViewMovie = (ListView)findViewById(R.id.movie_list);
        searchMovie=(EditText)findViewById(R.id.searchMovie);
        buttonSearch=(Button)findViewById(R.id.submit_button);
        progressBar=(ProgressBar)findViewById(R.id.progressbar);

        movieAdapter = new MovieAdapter(this);
        movieAdapter.notifyDataSetChanged();

        ButterKnife.bind(this);
        listViewMovie.setAdapter(movieAdapter);

        // Listen to click on the list item
        listViewMovie.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Movie item = (Movie) parent.getItemAtPosition(position);

                Intent detailIntent = new Intent(MainActivity.this, DetailActivity.class);
                detailIntent.putExtra("MOVIE",item);
                startActivity(detailIntent);
            }
        });

    }

    @Override
    public Loader<ArrayList<Movie>> onCreateLoader(int i, Bundle bundle) {
        String movieTitle = bundle.getString(EXTRA_MOVIE);

        progressBar.setVisibility(View.VISIBLE);
        if (progressBar.getVisibility() == View.VISIBLE){
            listViewMovie.setVisibility(View.GONE);
        }
        return new MovieAsynctaskLoader(this, movieTitle);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<Movie>> loader, ArrayList<Movie> movieList) {
        movieAdapter.setData(movieList);
        progressBar.setVisibility(View.GONE);
        if (progressBar.getVisibility() == View.GONE){
            listViewMovie.setVisibility(View.VISIBLE);
        }
    }

    public void onLoaderReset(Loader<ArrayList<Movie>> loader) {
        movieAdapter.setData(null);

    }
    public void onSearch(View view){
        String movieTitle = searchMovie.getText().toString();
        if (TextUtils.isEmpty(movieTitle)){
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_MOVIE, movieTitle);
        getSupportLoaderManager().restartLoader(0,bundle,MainActivity.this);

    }
}