package com.olivierpayen.popularmovies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.olivierpayen.popularmovies.api.TheMovieDbClient;
import com.olivierpayen.popularmovies.api.TheMovieDbInterface;
import com.olivierpayen.popularmovies.model.Movie;
import com.olivierpayen.popularmovies.model.MovieResponse;

import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TheMovieDbInterface service = new TheMovieDbClient()
                .getApiService();

        Call<MovieResponse> call = service.listMovies();

        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Response<MovieResponse> response, Retrofit retrofit) {

                int statusCode = response.code();
                Log.v(TAG, Integer.toString(statusCode));
                MovieResponse movieResponse = response.body();
                List<Movie> movies = movieResponse.results;

                for (int i = 0; i < movies.size(); i++) {
                    Log.v(TAG, movies.get(i).title);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e(TAG, "Error while fetching the movies", t);
            }
        });
    }
}
