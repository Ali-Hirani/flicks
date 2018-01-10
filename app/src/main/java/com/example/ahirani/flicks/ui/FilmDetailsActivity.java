package com.example.ahirani.flicks.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.ahirani.flicks.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FilmDetailsActivity extends AppCompatActivity {

    @BindView(R.id.film_details_title_text)
    TextView titleText;

    @BindView(R.id.film_details_description_text)
    TextView descriptionText;

    @BindView(R.id.film_details_rating_bar)
    RatingBar ratingBar;

    @BindView(R.id.film_details_release_date_text)
    TextView releaseDateText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_details);
        ButterKnife.bind(this);

        Intent intent = getIntent();

        titleText.setText(intent.getStringExtra("film_details_title_text"));
        descriptionText.setText(intent.getStringExtra("film_details_description_text"));
        releaseDateText.setText(String.format("Release Date: %s", intent.getStringExtra("film_details_release_date_text")));

        double rating = intent.getDoubleExtra("film_details_rating_bar", 0);
        ratingBar.setRating((float) rating);
    }
}
