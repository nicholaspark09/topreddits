package com.example.vn008xw.reddit.data.vo;

import com.google.gson.annotations.SerializedName;

public class RedditDataChild {

    @SerializedName("kind")
    private String kind;
    @SerializedName("data")
    private RedditPost post;

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public RedditPost getRedditPost() {
        return post;
    }

    public void setRedditPost(RedditPost post) {
        this.post = post;
    }
}