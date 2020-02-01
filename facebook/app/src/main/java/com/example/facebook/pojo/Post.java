package com.example.facebook.pojo;

import java.io.Serializable;

public class Post implements Serializable {
    private Boolean isBusinessProfilePost;
    private String postDescription;
    private String postImageUrl;
    private String postVideoUrl;
    private String userId;
    private String postDate;
    private Integer counterOfLikes;
    private Integer counterOfDislilkes;
    private Integer counterOfEmojis;
    private Integer counterOfComments;

    public Boolean getBusinessProfilePost() {
        return isBusinessProfilePost;
    }

    public void setBusinessProfilePost(Boolean businessProfilePost) {
        isBusinessProfilePost = businessProfilePost;
    }

    public String getPostDescription() {
        return postDescription;
    }

    public void setPostDescription(String postDescription) {
        this.postDescription = postDescription;
    }

    public String getPostImageUrl() {
        return postImageUrl;
    }

    public void setPostImageUrl(String postImageUrl) {
        this.postImageUrl = postImageUrl;
    }

    public String getPostVideoUrl() {
        return postVideoUrl;
    }

    public void setPostVideoUrl(String postVideoUrl) {
        this.postVideoUrl = postVideoUrl;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPostDate() {
        return postDate;
    }

    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }

    public Integer getCounterOfLikes() {
        return counterOfLikes;
    }

    public void setCounterOfLikes(Integer counterOfLikes) {
        this.counterOfLikes = counterOfLikes;
    }

    public Integer getCounterOfDislilkes() {
        return counterOfDislilkes;
    }

    public void setCounterOfDislilkes(Integer counterOfDislilkes) {
        this.counterOfDislilkes = counterOfDislilkes;
    }

    public Integer getCounterOfEmojis() {
        return counterOfEmojis;
    }

    public void setCounterOfEmojis(Integer counterOfEmojis) {
        this.counterOfEmojis = counterOfEmojis;
    }

    public Integer getCounterOfComments() {
        return counterOfComments;
    }

    public void setCounterOfComments(Integer counterOfComments) {
        this.counterOfComments = counterOfComments;
    }
}
