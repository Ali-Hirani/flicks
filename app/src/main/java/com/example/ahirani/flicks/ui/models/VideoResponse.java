package com.example.ahirani.flicks.ui.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class VideoResponse {

    int id;
    @SerializedName("results")
    List<Video> videos;

    public List<Video> getVideos() {
        return videos;
    }

    public VideoResponse() {
        this.videos = new ArrayList<>();
    }
}
