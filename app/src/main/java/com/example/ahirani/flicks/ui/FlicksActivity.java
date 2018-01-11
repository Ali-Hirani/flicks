package com.example.ahirani.flicks.ui;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.ahirani.flicks.R;
import com.example.ahirani.flicks.ui.models.MovieDBService;
import com.example.ahirani.flicks.ui.models.MovieResponse;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FlicksActivity extends AppCompatActivity {
    @BindView(R.id.my_recycler_view)
    RecyclerView recyclerView;
    private FlicksAdapter adapter;
    private boolean isLandscape = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flicks);
        ButterKnife.bind(this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        MovieDBService service = MovieDBService.retrofit.create(MovieDBService.class);
        final Call<MovieResponse> call = service.getMovies();

        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                MovieResponse movieResponse = response.body();
                if (movieResponse != null) {
                    adapter = new FlicksAdapter(movieResponse.getMovies(), isLandscape);
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        isLandscape = newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE;

        if (adapter != null) {
            recyclerView.setAdapter(adapter);
            adapter.orientationChanged(isLandscape);
        }

        Log.d("tag", "isLandscape: " + String.valueOf(isLandscape));
    }

}
