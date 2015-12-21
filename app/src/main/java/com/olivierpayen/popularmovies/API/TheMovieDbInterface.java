package com.olivierpayen.popularmovies.api;

import com.olivierpayen.popularmovies.model.MovieResponse;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

public interface TheMovieDbInterface {
    @GET("discover/movie")
    Call<MovieResponse> listMovies();
}
