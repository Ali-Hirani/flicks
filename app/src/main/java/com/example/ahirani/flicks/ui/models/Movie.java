package com.example.ahirani.flicks.ui.models;

import com.google.gson.annotations.SerializedName;

public class Movie {
    @SerializedName("vote_count")
    int voteCount;
    int id;
    @SerializedName("video")
    boolean videoExists;
    @SerializedName("vote_average")
    double voteAverage;
    String title;
    double popularity;
    @SerializedName("poster_path")
    String posterPath;
    @SerializedName("original_language")
    String originalLangCode;
    @SerializedName("original_title")
    String originalTitle;
    @SerializedName("backdrop_path")
    String backdropPath;
    @SerializedName("adult")
    boolean isAdult;
    @SerializedName("overview")
    String overviewText;
    @SerializedName("release_date")
    String releaseDate;

    public int getVoteCount() {
        return voteCount;
    }

    public int getId() {
        return id;
    }

    public boolean isVideoExists() {
        return videoExists;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public String getTitle() {
        return title;
    }

    public double getPopularity() {
        return popularity;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getOriginalLangCode() {
        return originalLangCode;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public boolean isAdult() {
        return isAdult;
    }

    public String getOverviewText() {
        return overviewText;
    }

    public String getReleaseDate() {
        return releaseDate;
    }
}
