package com.example.vn008xw.reddit.data.vo;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class RedditData {

    @SerializedName("modhash")
    private String modhash;
    @SerializedName("children")
    private final List<RedditDataChild> children = new ArrayList<>();
    @SerializedName("after")
    private String after;

    public String getModhash() {
        return modhash;
    }

    public void setModhash(String modhash) {
        this.modhash = modhash;
    }

    public List<RedditDataChild> getChildren() {
        return children;
    }

    public void setChildren(List<RedditDataChild> children) {
        this.children.addAll(children);
    }

    public String getAfter() {
        return after;
    }

    public void setAfter(String after) {
        this.after = after;
    }
}