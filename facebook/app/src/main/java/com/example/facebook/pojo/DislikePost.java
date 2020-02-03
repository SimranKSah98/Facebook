package com.example.facebook.pojo;

public class DislikePost {
    private String postId;
    private String postDescription;
    private String postImageUrl;
    private String postVideoUrl;
    private String userId;
    private int counterOfDislikes;
    private  String source;

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
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

    public int getCounterOfDislikes() {
        return counterOfDislikes;
    }

    public void setCounterOfDislikes(int counterOfDislikes) {
        this.counterOfDislikes = counterOfDislikes;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
