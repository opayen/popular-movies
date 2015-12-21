
package com.olivierpayen.popularmovies.model;

import java.util.ArrayList;
import java.util.List;

public class Movie {
    public Boolean adult;
    public String  backdropPath;
    public List<Integer> genreIds = new ArrayList<Integer>();
    public Integer id;
    public String  originalLanguage;
    public String  originalTitle;
    public String  overview;
    public String  releaseDate;
    public String  posterPath;
    public Double  popularity;
    public String  title;
    public Boolean video;
    public Double  voteAverage;
    public Integer voteCount;
}
