package com.clannad.dto;

import java.util.Date;

/**
 * Created by F_ck on 2016/11/16.
 */
public class UserInfo {
    private String userId;
    private Date birthDay;
    private Date dateCreate;
    private int gender;
    private Date lastUpdate;
    private String nickName;
    private String password;
    private String salt;
    private String sessionToken;

    public UserInfo(String userId, Date birthDay, Date dateCreate, int gender, Date lastUpdate, String nickName, String password, String salt, String sessionToken) {
        this.userId = userId;
        this.birthDay = birthDay;
        this.dateCreate = dateCreate;
        this.gender = gender;
        this.lastUpdate = lastUpdate;
        this.nickName = nickName;
        this.password = password;
        this.salt = salt;
        this.sessionToken = sessionToken;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getSessionToken() {
        return sessionToken;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }
}
