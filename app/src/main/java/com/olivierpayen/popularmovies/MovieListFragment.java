package com.olivierpayen.popularmovies;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.olivierpayen.popularmovies.api.TheMovieDbClient;
import com.olivierpayen.popularmovies.api.TheMovieDbInterface;
import com.olivierpayen.popularmovies.model.Movie;
import com.olivierpayen.popularmovies.model.MovieResponse;

import java.util.ArrayList;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class MovieListFragment extends Fragment {

    private static final String TAG = MovieListFragment.class.getSimpleName();

    public MovieListFragment() {

    }

    private MovieAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_list, container, false);


        ArrayList<Movie> movies = new ArrayList<>();
        adapter = new MovieAdapter(getContext(), movies);
        GridView gridView = (GridView) view.findViewById(R.id.movie_list_gridview);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> parent, final View view, final int position, final long id) {
                Context context = getActivity();
                Movie movie = adapter.getItem(position);
                Intent detailIntent = new Intent(context, MovieDetailsActivity.class);
                detailIntent.putExtra("movie", movie);
                startActivity(detailIntent);
            }
        });

        TheMovieDbInterface service = new TheMovieDbClient()
                .getApiService();

        Call<MovieResponse> call = service.listMovies();

        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Response<MovieResponse> response, Retrofit retrofit) {

                int statusCode = response.code();
                Log.v(TAG, Integer.toString(statusCode));
                MovieResponse movieResponse = response.body();
                ArrayList<Movie> movies = (ArrayList<Movie>) movieResponse.results;
                adapter.clear();

                for (int i = 0; i < movies.size(); i++) {
                    adapter.add(movies.get(i));
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e(TAG, "Error while fetching the movies", t);
            }
        });


        return view;
    }
}
