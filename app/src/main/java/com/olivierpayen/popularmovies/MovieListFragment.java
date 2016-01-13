package com.olivierpayen.popularmovies;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

    @Override
    public void onCreateOptionsMenu(final Menu menu, final MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.movie_list_menu, menu);
    }

    private MovieAdapter        adapter;
    private TheMovieDbInterface service;

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        switch(item.getItemId()){
            case R.id.menu_sortByPopularity:
                GetMovies("popularity.desc");
                return true;
            case R.id.menu_sortByRating:
                GetMovies("vote_average.desc");
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);

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

        service = new TheMovieDbClient()
                .getApiService();

        GetMovies("vote_count.desc");

        return view;
    }

    private void GetMovies(String sort) {
        Call<MovieResponse> call = service.listMovies(sort);

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
    }
}
