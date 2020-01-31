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
