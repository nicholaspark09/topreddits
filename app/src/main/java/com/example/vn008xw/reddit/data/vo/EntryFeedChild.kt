package com.example.vn008xw.reddit.data.vo

import com.google.gson.annotations.SerializedName


data class EntryFeedChild(
    @SerializedName("kind")
    var kind: String? = null,
    @SerializedName("data")
    var entry: Entry? = null)