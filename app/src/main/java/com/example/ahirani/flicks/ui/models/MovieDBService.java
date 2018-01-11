package com.example.ahirani.flicks.ui.models;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MovieDBService {
    // TODO: Dynamically insert api_key
    @GET("movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed")
    Call<MovieResponse> getMovies();

    @GET("movie/{id}/videos?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed")
    Call<VideoResponse> getMovieVideos(@Path("id") int movieId);

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
