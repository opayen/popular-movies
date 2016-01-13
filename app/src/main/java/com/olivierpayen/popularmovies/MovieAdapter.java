package com.olivierpayen.popularmovies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.olivierpayen.popularmovies.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MovieAdapter extends ArrayAdapter<Movie> {
    public MovieAdapter(Context context, ArrayList<Movie> users) {
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Movie movie = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.movie_item_template, parent, false);
        }

        String posterUrl = movie.getPosterUrl();
        if(posterUrl != null) {
            ImageView imageView = (ImageView) convertView.findViewById(R.id.poster_image_view);
            Picasso.with(getContext()).load(posterUrl).into(imageView);
        }

        return convertView;
    }
}