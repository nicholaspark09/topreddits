package com.example.vn008xw.reddit.data.vo

import com.google.gson.annotations.SerializedName


data class Entry(
    @SerializedName("domain")
    var domain: String? = null,
    @SerializedName("approved_at_utc")
    var approvedAtUtc: Any? = null,
    @SerializedName("banned_by")
    var bannedBy: Any? = null,
    @SerializedName("thumbnail_width")
    var thumbnailWidth: Int? = null,
    @SerializedName("subreddit")
    var subreddit: String? = null,
    @SerializedName("selftext_html")
    var selftextHtml: Any? = null,
    @SerializedName("selftext")
    var selftext: String? = null,
    @SerializedName("likes")
    var likes: Any? = null,
    @SerializedName("suggested_sort")
    var suggestedSort: Any? = null,
    @SerializedName("user_reports")
    var userReports: List<Any>? = null,
    @SerializedName("secure_media")
    var secureMedia: Any? = null,
    @SerializedName("is_reddit_media_domain")
    var isRedditMediaDomain: Boolean? = null,
    @SerializedName("link_flair_text")
    var linkFlairText: Any? = null,
    @SerializedName("id")
    var id: String? = null,
    @SerializedName("banned_at_utc")
    var bannedAtUtc: Any? = null,
    @SerializedName("view_count")
    var viewCount: Any? = null,
    @SerializedName("archived")
    var archived: Boolean? = null,
    @SerializedName("clicked")
    var clicked: Boolean? = null,
    @SerializedName("report_reasons")
    var reportReasons: Any? = null,
    @SerializedName("title")
    var title: String? = null,
    @SerializedName("num_crossposts")
    var numCrossposts: Int? = null,
    @SerializedName("saved")
    var saved: Boolean? = null,
    @SerializedName("mod_reports")
    var modReports: List<Any>? = null,
    @SerializedName("can_mod_post")
    var canModPost: Boolean? = null,
    @SerializedName("is_crosspostable")
    var isCrosspostable: Boolean? = null,
    @SerializedName("pinned")
    var pinned: Boolean? = null,
    @SerializedName("score")
    var score: Int? = null,
    @SerializedName("approved_by")
    var approvedBy: Any? = null,
    @SerializedName("over_18")
    var over18: Boolean? = null,
    @SerializedName("hidden")
    var hidden: Boolean? = null,
    @SerializedName("preview")
    var preview: Preview? = null,
    @SerializedName("thumbnail")
    var thumbnail: String? = null,
    @SerializedName("subreddit_id")
    var subredditId: String? = null,
    @SerializedName("edited")
    var edited: Boolean? = null,
    @SerializedName("link_flair_css_class")
    var linkFlairCssClass: Any? = null,
    @SerializedName("author_flair_css_class")
    var authorFlairCssClass: Any? = null,
    @SerializedName("contest_mode")
    var contestMode: Boolean? = null,
    @SerializedName("gilded")
    var gilded: Int? = null,
    @SerializedName("downs")
    var downs: Int? = null,
    @SerializedName("brand_safe")
    var brandSafe: Boolean? = null,
    @SerializedName("removal_reason")
    var removalReason: Any? = null,
    @SerializedName("post_hint")
    var postHint: String? = null,
    @SerializedName("author_flair_text")
    var authorFlairText: Any? = null,
    @SerializedName("stickied")
    var stickied: Boolean? = null,
    @SerializedName("can_gild")
    var canGild: Boolean? = null,
    @SerializedName("thumbnail_height")
    var thumbnailHeight: Int? = null,
    @SerializedName("parent_whitelist_status")
    var parentWhitelistStatus: String? = null,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("spoiler")
    var spoiler: Boolean? = null,
    @SerializedName("permalink")
    var permalink: String? = null,
    @SerializedName("subreddit_type")
    var subredditType: String? = null,
    @SerializedName("locked")
    var locked: Boolean? = null,
    @SerializedName("hide_score")
    var hideScore: Boolean? = null,
    @SerializedName("created")
    var created: Double? = null,
    @SerializedName("url")
    var url: String? = null,
    @SerializedName("whitelist_status")
    var whitelistStatus: String? = null,
    @SerializedName("quarantine")
    var quarantine: Boolean? = null,
    @SerializedName("author")
    var author: String? = null,
    @SerializedName("created_utc")
    var createdUtc: Double? = null,
    @SerializedName("subreddit_name_prefixed")
    var subredditNamePrefixed: String? = null,
    @SerializedName("ups")
    var ups: Int? = null,
    @SerializedName("media")
    var media: Any? = null,
    @SerializedName("num_comments")
    var numComments: Int? = null,
    @SerializedName("is_self")
    var isSelf: Boolean? = null,
    @SerializedName("visited")
    var visited: Boolean? = null,
    @SerializedName("num_reports")
    var numReports: Any? = null,
    @SerializedName("is_video")
    var isVideo: Boolean? = null,
    @SerializedName("distinguished")
    var distinguished: Any? = null)