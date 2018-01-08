package com.example.ahirani.flicks.ui.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class MovieResponse {

    @SerializedName("results")
    List<Movie> movies;

    public List<Movie> getMovies() {
        return movies;
    }

    public MovieResponse() {
        this.movies = new ArrayList<>();
    }
}
