
package com.olivierpayen.popularmovies.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Movie {
    public Boolean adult;
    @SerializedName("backdrop_path")
    public String  backdropPath;
    @SerializedName("genre_ids")
    public List<Integer> genreIds = new ArrayList<Integer>();
    public Integer id;
    public String  originalLanguage;
    @SerializedName("original_title")
    public String  originalTitle;
    public String  overview;
    @SerializedName("release_date")
    public String  releaseDate;
    @SerializedName("poster_path")
    public String  posterPath;
    public Double  popularity;
    public String  title;
    public Boolean video;
    @SerializedName("vote_average")
    public Double  voteAverage;
    @SerializedName("vote_count")
    public Integer voteCount;

    public Movie(String title) {
        this.title = title;
    }

    private static final String BASE_IMG_URL = "http://image.tmdb.org/t/p/";

    public String getPosterUrl() {
        if (posterPath == null) {
            return null;
        }
        else {
            return BASE_IMG_URL + "w185" + posterPath;
        }
    }
}
