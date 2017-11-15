package com.example.vn008xw.reddit.data.vo

import com.google.gson.annotations.SerializedName


data class EntryFeed(
    @SerializedName("modhash")
    var modhash: String? = null,
    @SerializedName("whitelist_status")
    var whitelistStatus: String? = null,
    @SerializedName("children")
    var children: List<EntryFeedChild>? = null)