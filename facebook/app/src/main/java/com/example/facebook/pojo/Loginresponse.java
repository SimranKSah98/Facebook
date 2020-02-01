package com.example.facebook.pojo;

import java.io.Serializable;

public class Loginresponse implements Serializable {
    private String accessToken;
    private String profile;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }
}
