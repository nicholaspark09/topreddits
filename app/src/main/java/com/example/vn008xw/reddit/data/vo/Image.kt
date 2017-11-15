package com.example.vn008xw.reddit.data.vo

import com.google.gson.annotations.SerializedName


data class Image(
    @SerializedName("source")
    var source: Source? = null,
    @SerializedName("resolutions")
    var resolutions: List<Resolution>? = null,
    @SerializedName("id")
    var id: String? = null)