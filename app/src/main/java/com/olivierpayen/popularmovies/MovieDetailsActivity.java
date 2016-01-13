package com.olivierpayen.popularmovies;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.olivierpayen.popularmovies.model.Movie;
import com.squareup.picasso.Picasso;

public class MovieDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_details_container);

        Intent intent = getIntent();
        Movie movie = intent.getParcelableExtra("movie");

        TextView titleTextView = (TextView) findViewById(R.id.movie_year_text_view);
        titleTextView.setText(movie.getMovieYear());

        TextView durationTextView = (TextView) findViewById(R.id.movie_rating_text_view);
        durationTextView.setText(movie.voteAverage.toString() + "/10");

        TextView overviewTextView = (TextView) findViewById(R.id.movie_overview);
        overviewTextView.setText(movie.overview);

        //        String posterUrl = movie.getPosterUrl();
        //        if(posterUrl != null) {
        //            ImageView imageView = (ImageView) convertView.findViewById(R.id.poster_image_view);
        //            Picasso.with(getContext()).load(posterUrl).into(imageView);
        //        }

        String backdropUrl = movie.getBackdropUrl();
        if (backdropUrl != null) {
            ImageView imageView = (ImageView) findViewById(R.id.backdrop_image_view);
            Picasso.with(this).load(backdropUrl).into(imageView);
        }

        String posterUrl = movie.getPosterUrl();
        if (posterUrl != null) {
            ImageView imageView = (ImageView) findViewById(R.id.movie_details_poster);
            Picasso.with(this).load(posterUrl).into(imageView);
        }


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        collapsingToolbar.setTitle(movie.title);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "TODO: launch movie trailer", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}
