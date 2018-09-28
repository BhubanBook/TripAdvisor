package com.kazikhaledsaif.tripadvisor.POJO;

public class UserProfile {
    private  String userId;
    private  String profileId;
    private  String userFullName;
    private String userPhone;
    private String userAddress;



    public UserProfile(String profileId,String userId, String userFullName, String userPhone, String userAddress) {
        this.userId = userId;
        this.profileId = profileId;
        this.userFullName = userFullName;
        this.userPhone = userPhone;
        this.userAddress = userAddress;
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProfileId() {
        return profileId;
    }

    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }
}


