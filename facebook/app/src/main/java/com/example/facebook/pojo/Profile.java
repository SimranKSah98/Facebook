package com.example.facebook.pojo;

import java.io.Serializable;


public class Profile implements Serializable {


    private String userId;
    private String userFirstName;
    private String profilePicure;
    private String typeOfProfile;
    private String domainOfProfile;
    private String personalDescription;
    private String userEmailId;
    private String userCity;
    private String userGender;
    private String userDateOfBirth;

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProfilePicure() {
        return profilePicure;
    }

    public void setProfilePicure(String profilePicure) {
        this.profilePicure = profilePicure;
    }

    public String getTypeOfProfile() {
        return typeOfProfile;
    }

    public void setTypeOfProfile(String typeOfProfile) {
        this.typeOfProfile = typeOfProfile;
    }

    public String getDomainOfProfile() {
        return domainOfProfile;
    }

    public void setDomainOfProfile(String domainOfProfile) {
        this.domainOfProfile = domainOfProfile;
    }

    public String getPersonalDescription() {
        return personalDescription;
    }

    public void setPersonalDescription(String personalDescription) {
        this.personalDescription = personalDescription;
    }

    public String getUserEmailId() {
        return userEmailId;
    }

    public void setUserEmailId(String userEmailId) {
        this.userEmailId = userEmailId;
    }

    public String getUserCity() {
        return userCity;
    }

    public void setUserCity(String userCity) {
        this.userCity = userCity;
    }

    public String getUserGender() {
        return userGender;
    }

    public void setUserGender(String userGender) {
        this.userGender = userGender;
    }

    public String getUserDateOfBirth() {
        return userDateOfBirth;
    }

    public void setUserDateOfBirth(String userDateOfBirth) {
        this.userDateOfBirth = userDateOfBirth;
    }
}

