package com.example.ahirani.flicks.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.ahirani.flicks.R;
import com.example.ahirani.flicks.ui.models.MovieDBService;
import com.example.ahirani.flicks.ui.models.MovieResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FlicksActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flicks);

        MovieDBService service = MovieDBService.retrofit.create(MovieDBService.class);
        final Call<MovieResponse> call = service.getMovies();

        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                MovieResponse movieResponse = response.body();
                if (movieResponse != null) {
                    System.out.println(movieResponse.getMovies().get(0).getTitle());
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {

            }
        });

    }
}
