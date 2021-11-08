package com.qgj.juan_05.netwok.model;

import com.google.gson.annotations.SerializedName;

public class UpUserinfoModel {

    @Override
    public String toString() {
        return "UpUserinfoModel{" +
                "email='" + email + '\'' +
                ", idCard='" + idCard + '\'' +
                ", nickName='" + nickName + '\'' +
                ", phonenumber='" + phonenumber + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }

    public UpUserinfoModel() {

    }

    public UpUserinfoModel(String email, String idCard, String nickName, String phonenumber, String sex) {
        this.email = email;
        this.idCard = idCard;
        this.nickName = nickName;
        this.phonenumber = phonenumber;
        this.sex = sex;
    }

    @SerializedName("email")
    private String email;
    @SerializedName("idCard")
    private String idCard;
    @SerializedName("nickName")
    private String nickName;
    @SerializedName("phonenumber")
    private String phonenumber;
    @SerializedName("sex")
    private String sex;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
