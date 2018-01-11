package com.example.ahirani.flicks.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.ahirani.flicks.R;
import com.example.ahirani.flicks.ui.models.MovieDBService;
import com.example.ahirani.flicks.ui.models.Video;
import com.example.ahirani.flicks.ui.models.VideoResponse;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FilmDetailsActivity extends AppCompatActivity {

    @BindView(R.id.film_details_title_text)
    TextView titleText;

    @BindView(R.id.film_details_description_text)
    TextView descriptionText;

    @BindView(R.id.film_details_rating_bar)
    RatingBar ratingBar;

    @BindView(R.id.film_details_release_date_text)
    TextView releaseDateText;

    @BindView(R.id.trailer_thumbnail)
    ImageView trailerThumbnail;

    String trailerUrl = "";

    // TODO: Extract to some static constants class
    private final String IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500";
    private final String YOUTUBE_BASE_URL = "https://www.youtube.com/watch?v=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_details);
        ButterKnife.bind(this);

        Intent intent = getIntent();

        titleText.setText(intent.getStringExtra("film_details_title_text"));
        descriptionText.setText(intent.getStringExtra("film_details_description_text"));
        releaseDateText.setText(
                String.format(
                        "Release Date: %s",
                        intent.getStringExtra("film_details_release_date_text")));

        double rating = intent.getDoubleExtra("film_details_rating_bar", 0);
        ratingBar.setRating((float) rating);

        Picasso.with(this).load(IMAGE_BASE_URL
                + intent.getStringExtra("film_details_trailer_thumbnail"))
                .into(trailerThumbnail);

        MovieDBService dbService = MovieDBService.retrofit.create(MovieDBService.class);
        final Call<VideoResponse> call = dbService.getMovieVideos(
                intent.getIntExtra("film_details_id", 0));

        call.enqueue(new Callback<VideoResponse>() {
            @Override
            public void onResponse(Call<VideoResponse> call, Response<VideoResponse> response) {
                List<Video> videoList = response.body().getVideos();

                for (int i = 0; i < videoList.size(); i++) {
                    if (videoList.get(i).getType().equals("Trailer") && trailerUrl.isEmpty()) {
                        trailerUrl = YOUTUBE_BASE_URL + videoList.get(i).getKey();
                    }
                }
            }

            @Override
            public void onFailure(Call<VideoResponse> call, Throwable t) {
                // TODO: Handle
            }
        });

        trailerThumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!trailerUrl.isEmpty()) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(trailerUrl)));
                }
            }
        });
    }
}
