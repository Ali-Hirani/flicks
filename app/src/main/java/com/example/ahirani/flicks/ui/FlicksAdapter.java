package com.example.ahirani.flicks.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ahirani.flicks.R;
import com.example.ahirani.flicks.ui.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

class FlicksAdapter extends RecyclerView.Adapter {
    private static final String IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500";
    private List<Movie> movies;
    private boolean isLandscape;

    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        TextView filmText;
        TextView overviewText;
        ImageView filmImage;

        MovieViewHolder(View v) {
            super(v);
            filmText = v.findViewById(R.id.film_title_text);
            overviewText = v.findViewById(R.id.film_overview_text);
            filmImage = v.findViewById(R.id.film_thumbnail);
        }
    }

    FlicksAdapter(List<Movie> movies, boolean isLandscape) {
        this.movies = movies;
        this.isLandscape = isLandscape;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_item, parent, false);

        return new MovieViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MovieViewHolder movieViewHolder = (MovieViewHolder) holder;
        movieViewHolder.filmText.setText(movies.get(position).getTitle());
        movieViewHolder.overviewText.setText(movies.get(position).getOverviewText());

        Context context = ((MovieViewHolder) holder).filmImage.getContext();

        if (isLandscape) {
            Log.d("tag", "backdrop");
            Picasso.with(context).load(IMAGE_BASE_URL + movies.get(position).getBackdropPath()).into(movieViewHolder.filmImage);
        } else {
            Log.d("tag", "poster");
            Picasso.with(context).load(IMAGE_BASE_URL + movies.get(position).getPosterPath()).into(movieViewHolder.filmImage);
        }
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public void orientationChanged(boolean isLandscape) {
        this.isLandscape = isLandscape;
    }
}
