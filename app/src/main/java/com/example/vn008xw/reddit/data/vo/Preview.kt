package com.example.vn008xw.reddit.data.vo

import com.google.gson.annotations.SerializedName


data class Preview(
    @SerializedName("images")
    var images: List<Image>? = null,
    @SerializedName("enabled")
    var enabled: Boolean? = null)