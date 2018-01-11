package com.example.ahirani.flicks.ui.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class VideoResponse {
    private int id;
    @SerializedName("results")
    private List<Video> videos;

    public int getId() {
        return id;
    }

    public List<Video> getVideos() {
        return videos;
    }

    public VideoResponse() {
        this.videos = new ArrayList<>();
    }
}
