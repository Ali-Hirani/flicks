package com.example.ahirani.flicks.ui.models;

import com.google.gson.annotations.SerializedName;

public class Video {

    String id;

    @SerializedName("iso_639_1")
    String iso639;

    @SerializedName("iso_3166_1")
    String iso3166;

    String key;
    String name;

    @SerializedName("site")
    String siteHost;

    @SerializedName("size")
    int videoReolution;

    String type;


    public String getId() {
        return id;
    }

    public String getIso639() {
        return iso639;
    }

    public String getIso3166() {
        return iso3166;
    }

    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public String getSiteHost() {
        return siteHost;
    }

    public int getVideoReolution() {
        return videoReolution;
    }

    public String getType() {
        return type;
    }
}
