package com.example.vn008xw.reddit.data.vo;

import com.google.gson.annotations.SerializedName;

public class RedditResponse {

    @SerializedName("kind")
    private String kind;
    @SerializedName("data")
    private RedditData redditData;

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public RedditData getRedditData() {
        return redditData;
    }

    public void setRedditData(RedditData data) {
        this.redditData = data;
    }
}