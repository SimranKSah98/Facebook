package com.example.facebook.pojo;

import java.io.Serializable;

public class Ads implements Serializable {
    private String adId;
    private String categoryName;
    private String tag;
    private String imageUrl;
    private String targetUrl;
    private String description;
    private String advertiserId;
    private String location;
    private String source;
    private String userId;

    public String getSource() {
        return source;
    }

    public String getUserId() {
        return userId;
    }

    public void setAdId(String adId) {
        this.adId = adId;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAdvertiserId(String advertiserId) {
        this.advertiserId = advertiserId;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Ads(String adId, String categoryName, String tag, String imageUrl, String targetUrl, String description, String advertiserId, String location) {
        this.adId = adId;
        this.categoryName = categoryName;
        this.tag = tag;
        this.imageUrl = imageUrl;
        this.targetUrl = targetUrl;
        this.description = description;
        this.advertiserId = advertiserId;
        this.location = location;
    }
    public String getAdId() {
        return adId;
    }
    public String getCategoryName() {
        return categoryName;
    }
    public String getTag() {
        return tag;
    }
    public String getImageUrl() {
        return imageUrl;
    }
    public String getTargetUrl() {
        return targetUrl;
    }
    public String getDescription() {
        return description;
    }
    public String getAdvertiserId() {
        return advertiserId;
    }
    public String getLocation() {
        return location;
    }
}
