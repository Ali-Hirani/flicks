package com.example.ahirani.flicks.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ahirani.flicks.R;
import com.example.ahirani.flicks.ui.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

class FlicksAdapter extends RecyclerView.Adapter {
    private final String IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500";
    private List<Movie> movies;
    private boolean isLandscape;

    static class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.film_title_text)
        TextView filmText;

        @BindView(R.id.film_overview_text)
        TextView overviewText;

        @BindView(R.id.film_thumbnail)
        ImageView filmImage;

        IMyViewHolderClicks mListener;

        MovieViewHolder(View v, IMyViewHolderClicks listener) {
            super(v);
            ButterKnife.bind(this, v);

            mListener = listener;
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mListener.onListElementSelected(view, getAdapterPosition());
        }

        public interface IMyViewHolderClicks {
            void onListElementSelected(View caller, int position);
        }
    }

    FlicksAdapter(List<Movie> movies, boolean isLandscape) {
        this.movies = movies;
        this.isLandscape = isLandscape;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_item, parent, false);


        return new MovieViewHolder(v, new MovieViewHolder.IMyViewHolderClicks() {
            @Override
            public void onListElementSelected(View caller, int position) {
                Intent intent = new Intent(parent.getContext(), FilmDetailsActivity.class);

                // TODO: Key strings should be constants instead of hardcoded
                intent.putExtra("film_details_title_text", movies.get(position).getTitle());
                intent.putExtra("film_details_description_text", movies.get(position).getOverviewText());
                intent.putExtra("film_details_rating_bar", movies.get(position).getVoteAverage());
                intent.putExtra("film_details_release_date_text", movies.get(position).getReleaseDate());
                intent.putExtra("film_details_trailer_thumbnail", movies.get(position).getBackdropPath());
                intent.putExtra("film_details_id", movies.get(position).getId());

                parent.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MovieViewHolder movieViewHolder = (MovieViewHolder) holder;
        movieViewHolder.filmText.setText(movies.get(position).getTitle());
        movieViewHolder.overviewText.setText(movies.get(position).getOverviewText());

        Context context = ((MovieViewHolder) holder).filmImage.getContext();

        if (isLandscape) {
            Picasso.with(context).load(IMAGE_BASE_URL + movies.get(position).getBackdropPath()).into(movieViewHolder.filmImage);
        } else {
            Picasso.with(context).load(IMAGE_BASE_URL + movies.get(position).getPosterPath()).into(movieViewHolder.filmImage);
        }
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    void orientationChanged(boolean isLandscape) {
        this.isLandscape = isLandscape;
    }
}
