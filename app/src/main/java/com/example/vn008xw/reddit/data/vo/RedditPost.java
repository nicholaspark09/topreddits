package com.example.vn008xw.reddit.data.vo;

import com.google.gson.annotations.SerializedName;

public class RedditPost {

    @SerializedName("domain")
    private String domain;
    @SerializedName("banned_by")
    private Object bannedBy;
    @SerializedName("subreddit")
    private String subreddit;
    @SerializedName("selftext_html")
    private Object selftextHtml;
    @SerializedName("selftext")
    private String selftext;
    @SerializedName("likes")
    private Integer likes;
    @SerializedName("secure_media")
    private Object secureMedia;
    @SerializedName("link_flair_text")
    private Object linkFlairText;
    @SerializedName("id")
    private String id;
    @SerializedName("gilded")
    private Integer gilded;
    @SerializedName("clicked")
    private Boolean clicked;
    @SerializedName("report_reasons")
    private Object reportReasons;
    @SerializedName("author")
    private String author;
    @SerializedName("media")
    private Object media;
    @SerializedName("score")
    private Integer score;
    @SerializedName("approved_by")
    private Object approvedBy;
    @SerializedName("over_18")
    private Boolean over18;
    @SerializedName("hidden")
    private Boolean hidden;
    @SerializedName("thumbnail")
    private String thumbnail;
    @SerializedName("subreddit_id")
    private String subredditId;
    @SerializedName("link_flair_css_class")
    private Object linkFlairCssClass;
    @SerializedName("author_flair_css_class")
    private Object authorFlairCssClass;
    @SerializedName("downs")
    private Integer downs;
    @SerializedName("saved")
    private Boolean saved;
    @SerializedName("is_self")
    private Boolean isSelf;
    @SerializedName("name")
    private String name;
    @SerializedName("permalink")
    private String permalink;
    @SerializedName("stickied")
    private Boolean stickied;
    @SerializedName("created")
    private Long created;
    @SerializedName("url")
    private String url;
    @SerializedName("author_flair_text")
    private Object authorFlairText;
    @SerializedName("title")
    private String title;
    @SerializedName("created_utc")
    private Long createdUtc;
    @SerializedName("ups")
    private Integer ups;
    @SerializedName("num_comments")
    private Integer numComments;
    @SerializedName("visited")
    private Boolean visited;
    @SerializedName("num_reports")
    private Object numReports;

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public Object getBannedBy() {
        return bannedBy;
    }

    public void setBannedBy(Object bannedBy) {
        this.bannedBy = bannedBy;
    }

    public String getSubreddit() {
        return subreddit;
    }

    public void setSubreddit(String subreddit) {
        this.subreddit = subreddit;
    }

    public Object getSelftextHtml() {
        return selftextHtml;
    }

    public void setSelftextHtml(Object selftextHtml) {
        this.selftextHtml = selftextHtml;
    }

    public String getSelftext() {
        return selftext;
    }

    public void setSelftext(String selftext) {
        this.selftext = selftext;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Object getSecureMedia() {
        return secureMedia;
    }

    public void setSecureMedia(Object secureMedia) {
        this.secureMedia = secureMedia;
    }

    public Object getLinkFlairText() {
        return linkFlairText;
    }

    public void setLinkFlairText(Object linkFlairText) {
        this.linkFlairText = linkFlairText;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getGilded() {
        return gilded;
    }

    public void setGilded(Integer gilded) {
        this.gilded = gilded;
    }

    public Boolean getClicked() {
        return clicked;
    }

    public void setClicked(Boolean clicked) {
        this.clicked = clicked;
    }

    public Object getReportReasons() {
        return reportReasons;
    }

    public void setReportReasons(Object reportReasons) {
        this.reportReasons = reportReasons;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Object getMedia() {
        return media;
    }

    public void setMedia(Object media) {
        this.media = media;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Object getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(Object approvedBy) {
        this.approvedBy = approvedBy;
    }

    public Boolean getOver18() {
        return over18;
    }

    public void setOver18(Boolean over18) {
        this.over18 = over18;
    }

    public Boolean getHidden() {
        return hidden;
    }

    public void setHidden(Boolean hidden) {
        this.hidden = hidden;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getSubredditId() {
        return subredditId;
    }

    public void setSubredditId(String subredditId) {
        this.subredditId = subredditId;
    }

    public Object getLinkFlairCssClass() {
        return linkFlairCssClass;
    }

    public void setLinkFlairCssClass(Object linkFlairCssClass) {
        this.linkFlairCssClass = linkFlairCssClass;
    }

    public Object getAuthorFlairCssClass() {
        return authorFlairCssClass;
    }

    public void setAuthorFlairCssClass(Object authorFlairCssClass) {
        this.authorFlairCssClass = authorFlairCssClass;
    }

    public Integer getDowns() {
        return downs;
    }

    public void setDowns(Integer downs) {
        this.downs = downs;
    }

    public Boolean getSaved() {
        return saved;
    }

    public void setSaved(Boolean saved) {
        this.saved = saved;
    }

    public Boolean getIsSelf() {
        return isSelf;
    }

    public void setIsSelf(Boolean isSelf) {
        this.isSelf = isSelf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPermalink() {
        return permalink;
    }

    public void setPermalink(String permalink) {
        this.permalink = permalink;
    }

    public Boolean getStickied() {
        return stickied;
    }

    public void setStickied(Boolean stickied) {
        this.stickied = stickied;
    }

    public Long getCreated() {
        return created;
    }

    public void setCreated(Long created) {
        this.created = created;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Object getAuthorFlairText() {
        return authorFlairText;
    }

    public void setAuthorFlairText(Object authorFlairText) {
        this.authorFlairText = authorFlairText;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getCreatedUtc() {
        return createdUtc;
    }

    public void setCreatedUtc(Long createdUtc) {
        this.createdUtc = createdUtc;
    }

    public Integer getUps() {
        return ups;
    }

    public void setUps(Integer ups) {
        this.ups = ups;
    }

    public Integer getNumComments() {
        return numComments;
    }

    public void setNumComments(Integer numComments) {
        this.numComments = numComments;
    }

    public Boolean getVisited() {
        return visited;
    }

    public void setVisited(Boolean visited) {
        this.visited = visited;
    }

    public Object getNumReports() {
        return numReports;
    }

    public void setNumReports(Object numReports) {
        this.numReports = numReports;
    }
}