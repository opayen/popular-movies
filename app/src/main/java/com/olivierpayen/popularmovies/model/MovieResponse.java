
package com.olivierpayen.popularmovies.model;

import java.util.ArrayList;
import java.util.List;

public class MovieResponse {

    public Integer page;
    public List<Movie> results = new ArrayList<Movie>();
    public Integer totalPages;
    public Integer totalResults;
}
