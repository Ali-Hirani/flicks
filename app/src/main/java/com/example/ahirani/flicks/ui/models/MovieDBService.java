package com.example.ahirani.flicks.ui.models;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface MovieDBService {
    @GET("movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed")
    Call<MovieResponse> getMovies();

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
