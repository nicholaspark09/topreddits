package com.example.vn008xw.reddit.data.vo

import com.google.gson.annotations.SerializedName


data class Resolution(
    @SerializedName("url")
    var url: String? = null,
    @SerializedName("width")
    var width: Int? = null,
    @SerializedName("height")
    var height: Int? = null)