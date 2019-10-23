package com.example.andinurnaf.cobatugas1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Objects;

import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {
    ImageView image_extras;
    TextView title_extras;
    TextView date_extras;
    TextView count_extras;
    TextView average_extras;
    TextView popularity_extras;
    TextView adult_extras;
    TextView overview_extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        image_extras=(ImageView)findViewById(R.id.image_movie_extras);
        title_extras=(TextView)findViewById(R.id.title_movie_extras);
        date_extras=(TextView)findViewById(R.id.date_movie_extras);
        count_extras=(TextView)findViewById(R.id.count_movie_extras);
        average_extras=(TextView)findViewById(R.id.average_movie_extras);
        popularity_extras=(TextView)findViewById(R.id.popularity_movie_extras);
        adult_extras=(TextView)findViewById(R.id.adult_movie_extras);
        overview_extras=(TextView)findViewById(R.id.overview_movie_extras);

        ButterKnife.bind(this);

        Movie dataMovie = getIntent().getParcelableExtra("MOVIE");
        Log.d("Title Movie", dataMovie.getTitle());
        Log.d("Title Movie", dataMovie.isAdult());

        // Change action bar become movie title
        Objects.requireNonNull(getSupportActionBar()).setTitle(dataMovie.getTitle());
        // Fill movie information
        title_extras.setText(dataMovie.getTitle());
        date_extras.setText(dataMovie.getDate());
        count_extras.setText(Integer.toString(dataMovie.getVote_count()));
        average_extras.setText(Double.toString(dataMovie.getVote_average()));
        popularity_extras.setText(Double.toString(dataMovie.getPopularity()));
        overview_extras.setText(dataMovie.getOverview());
        if (dataMovie.isAdult().equals("true")) {
            adult_extras.setText("18+");
        } else {
            adult_extras.setText("Everyone");
        }

        // Using Picasso to download and display picture
        Picasso.with(this).load(dataMovie.getUrl()).into(image_extras);
    }
}
